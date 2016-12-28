package CodeRaguet.fraser;


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
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Thread;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GmailService {

    private final List<String> SCOPES;
    private HttpTransport HTTP_TRANSPORT;
    private final JsonFactory JSON_FACTORY;
    private DataStoreFactory DATA_STORE_FACTORY;
    private String clientSecret;

    public GmailService(String clientSecret, String refreshToken) throws GeneralSecurityException, IOException {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        DATA_STORE_FACTORY = new ENVDataStoreFactory(refreshToken);
        SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
        JSON_FACTORY = JacksonFactory.getDefaultInstance();
        this.clientSecret = clientSecret;
    }

    public String getLastLabel() throws IOException {
        // Build a new authorized API client service.
        Gmail service = getGmailService();

        // Print the labels in the user's account.
        String user = "me";
        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        String label = null;
        if (labels.size() == 0) {
            System.out.println("No labels found.");
        } else {
            label = labels.get(labels.size() - 1).getName();
        }
        return label;
    }

    private Gmail getGmailService() throws IOException {
        Credential credential = authorize();
        String APPLICATION_NAME = "Gmail API Java Quickstart";
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private Credential authorize() throws IOException {
        GoogleClientSecrets clientSecrets = loadClientSecrets();

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        return new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
    }

    private GoogleClientSecrets loadClientSecrets() throws IOException {
        InputStream in = new ByteArrayInputStream(clientSecret.getBytes(StandardCharsets.UTF_8));
        return GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
    }

    public List<Thread> threadsWithFrase() throws IOException {
        Gmail service = getGmailService();
        return service.users().threads().list("me").setQ("subject:f").execute().getThreads();
    }
}
