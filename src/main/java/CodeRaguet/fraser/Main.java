package CodeRaguet.fraser;


import CodeRaguet.fraser.gmail.GmailService;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        String clientSecret = ENV.GMAIL_CLIENT_SECRET.value();
        String refreshToken = ENV.GMAIL_REFRESH_TOKEN.value();
        GmailService gmailService = new GmailService(clientSecret, refreshToken);
        List<Message> messages = gmailService.messagesWithFrase();
        System.out.println(messages.get(messages.size() -1).getSnippet());
    }

}
