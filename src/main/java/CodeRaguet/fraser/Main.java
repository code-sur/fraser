package CodeRaguet.fraser;


import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        String clientSecret = ENV.CLIENT_SECRET.value();
        String refreshToken = ENV.REFRESH_TOKEN.value();
        GmailService gmailService = new GmailService(clientSecret, refreshToken);
        List<Message> messages = gmailService.messagesWithFrase();
        System.out.println(messages.get(messages.size() -1).getSnippet());
    }

}
