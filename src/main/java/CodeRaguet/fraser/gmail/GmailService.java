package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.MessageFilter;
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

public class GmailService {

    private static final String USER_ID = "me";
    private final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private HttpTransport httpTransport;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private DataStoreFactory dataStoreFactory;
    private String clientSecret;
    private Gmail service;
    private GmailFilterTranslator filterTranslator;

    public GmailService(String refreshToken, String clientSecret, GmailFilterTranslator filterTranslator) {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        dataStoreFactory = new ENVDataStoreFactory(refreshToken);
        this.clientSecret = clientSecret;
        this.service = authorizeAndBuildService();
        this.filterTranslator = filterTranslator;
    }

    private Gmail authorizeAndBuildService() {
        return new Gmail.Builder(httpTransport, JSON_FACTORY, getGmailAuthorizationCode())
                .setApplicationName("Gmail API Java Quickstart")
                .build();
    }

    private Credential getGmailAuthorizationCode() {
        try {
            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow =
                    new GoogleAuthorizationCodeFlow.Builder(
                            httpTransport, JSON_FACTORY, loadClientSecrets(), SCOPES)
                            .setDataStoreFactory(dataStoreFactory)
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

    public Message getFirstMessageOf(Thread thread) {
        try {
            return service.users().threads().get(USER_ID, thread.getId()).execute().getMessages().get(0);
        } catch (Exception e) {
            throw new GmailServiceException("Can't select message", e);
        }
    }

     public List<Thread> getThreadsFilteredBy(MessageFilter filter) {
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
        Collections.reverse(threads);
        return threads;
    }

    private ListThreadsResponse getResponse(String pageToken, MessageFilter filter) throws IOException {
        Long threadsMaxResults = 100L;
        return service.users().threads().list(USER_ID)
                .setMaxResults(threadsMaxResults)
                .setQ(filterTranslator.translate(filter))
                .setPageToken(pageToken)
                .execute();
    }
}
