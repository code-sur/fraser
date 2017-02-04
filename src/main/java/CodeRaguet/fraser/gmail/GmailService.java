package CodeRaguet.fraser.gmail;

import com.google.api.services.gmail.GmailScopes;

import java.util.Collections;
import java.util.List;

class GmailService {

    private static final String USER_ID = "me";
    private final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);

    List<String> getSCOPES() {
        return SCOPES;
    }

    static String getUserId() {
        return USER_ID;
    }

}
