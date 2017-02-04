package CodeRaguet.fraser.gmail;


import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.model.PostOffice;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GmailPostOffice implements PostOffice {

    private final List<String> SCOPES;
    private final GmailService gmailService;
    private HttpTransport HTTP_TRANSPORT;
    private final JsonFactory JSON_FACTORY;
    private DataStoreFactory DATA_STORE_FACTORY;
    private String clientSecret;
    private Gmail service;
    private GmailFilterTranslator filterTranslator;
    private GmailMessageTranslator gmailMessageTranslator;

    public GmailPostOffice(String clientSecret, String refreshToken, GmailFilterTranslator filterTranslator, GmailMessageTranslator gmailMessageTranslator) {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        DATA_STORE_FACTORY = new ENVDataStoreFactory(refreshToken);
        SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
        JSON_FACTORY = JacksonFactory.getDefaultInstance();
        this.clientSecret = clientSecret;
        service = authorizeAndBuildService();
        this.filterTranslator = filterTranslator;
        this.gmailMessageTranslator = gmailMessageTranslator;
        this.gmailService = new GmailService();
    }

    private Gmail authorizeAndBuildService() {
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getGmailAuthorizationCode())
                .setApplicationName("Gmail API Java Quickstart")
                .build();
    }

    private Credential getGmailAuthorizationCode() {
        try {
            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow =
                    new GoogleAuthorizationCodeFlow.Builder(
                            HTTP_TRANSPORT, JSON_FACTORY, loadClientSecrets(), SCOPES)
                            .setDataStoreFactory(DATA_STORE_FACTORY)
                            .setAccessType("offline")
                            .build();
            return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (IOException e) {
            throw new RuntimeException("Can't getGmailAuthorizationCode", e);
        }
    }

    private GoogleClientSecrets loadClientSecrets() throws IOException {
        InputStream in = new ByteArrayInputStream(clientSecret.getBytes(StandardCharsets.UTF_8));
        return GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
    }

    @Override
    public List<CodeRaguet.fraser.model.Message> messagesFilteredBy(MessageFilter filter) {
        List<Thread> threads = getThreadsFilteredBy(filter);
        List<CodeRaguet.fraser.model.Message> fraserMessages = new ArrayList<>();
        threads.forEach(thread -> fraserMessages.add(translateMessageToFraserMessage(getFirstMessageOf(thread))));
        Collections.reverse(fraserMessages);
        return fraserMessages;
    }

    private CodeRaguet.fraser.model.Message translateMessageToFraserMessage(Message gmailMessage) {
        return gmailMessageTranslator.translate(gmailMessage);
    }

    private List<Thread> getThreadsFilteredBy(MessageFilter filter) {
        List<Thread> threads = new ArrayList<>();
        ListThreadsResponse response;
        String pageToken = null;
        do {
            try {
                response = getResponse(pageToken, filter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            threads.addAll(response.getThreads());
            pageToken = response.getNextPageToken();
        } while (response.getNextPageToken() != null);
        return threads;
    }

    private ListThreadsResponse getResponse(String pageToken, MessageFilter filter) throws IOException {
        Long threadsMaxResults = 100L;
        return service.users().threads().list(GmailService.getUserId())
                .setMaxResults(threadsMaxResults)
                .setQ(filterTranslator.translate(filter))
                .setPageToken(pageToken)
                .execute();
    }

    private Message getFirstMessageOf(Thread thread) {
        try {
            return service.users().threads().get(GmailService.getUserId(), thread.getId()).execute().getMessages().get(0);
        } catch (IOException e) {
            throw new GmailServiceException("Can't select message", e);
        }
    }

}
