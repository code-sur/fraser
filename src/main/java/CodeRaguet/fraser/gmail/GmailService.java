package CodeRaguet.fraser.gmail;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

class GmailService {

    private static final String USER_ID = "me";
    private final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private HttpTransport HTTP_TRANSPORT;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private DataStoreFactory DATA_STORE_FACTORY;
    private String clientSecret;
    private Gmail service;

    void setDATA_STORE_FACTORY(DataStoreFactory DATA_STORE_FACTORY) {
        this.DATA_STORE_FACTORY = DATA_STORE_FACTORY;
    }

    void setHTTP_TRANSPORT(HttpTransport HTTP_TRANSPORT) {
        this.HTTP_TRANSPORT = HTTP_TRANSPORT;
    }

    static String getUserId() {
        return USER_ID;
    }

    void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Gmail getService() {
        return service;
    }

    public void setService(Gmail service) {
        this.service = service;
    }

    Gmail authorizeAndBuildService() {
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
}
