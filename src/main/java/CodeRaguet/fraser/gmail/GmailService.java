package CodeRaguet.fraser.gmail;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.GmailScopes;

import java.util.Collections;
import java.util.List;

class GmailService {

    private static final String USER_ID = "me";
    private final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private HttpTransport HTTP_TRANSPORT;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    JsonFactory getJSON_FACTORY() {
        return JSON_FACTORY;
    }

    HttpTransport getHTTP_TRANSPORT() {
        return HTTP_TRANSPORT;
    }

    void setHTTP_TRANSPORT(HttpTransport HTTP_TRANSPORT) {
        this.HTTP_TRANSPORT = HTTP_TRANSPORT;
    }

    List<String> getSCOPES() {
        return SCOPES;
    }

    static String getUserId() {
        return USER_ID;
    }

}
