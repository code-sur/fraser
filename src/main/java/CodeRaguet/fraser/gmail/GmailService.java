package CodeRaguet.fraser.gmail;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.gmail.GmailScopes;

import java.util.Collections;
import java.util.List;

class GmailService {

    private static final String USER_ID = "me";
    private final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private HttpTransport HTTP_TRANSPORT;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private DataStoreFactory DATA_STORE_FACTORY;

    DataStoreFactory getDATA_STORE_FACTORY() {
        return DATA_STORE_FACTORY;
    }

    void setDATA_STORE_FACTORY(DataStoreFactory DATA_STORE_FACTORY) {
        this.DATA_STORE_FACTORY = DATA_STORE_FACTORY;
    }

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
