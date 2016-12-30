package CodeRaguet.fraser;


import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        String clientSecret = ENV.CLIENT_SECRET.value();
        String refreshToken = ENV.REFRESH_TOKEN.value();
        GmailService gmailService = new GmailService(clientSecret, refreshToken);
        gmailService.messagesWithFrase().forEach(message -> System.out.println(message.getSnippet()));
    }

}
