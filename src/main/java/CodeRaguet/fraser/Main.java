package CodeRaguet.fraser;


import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        GmailService gmailService = new GmailService();
        gmailService.setClientSecret(ENV.CLIENT_SECRET.value());
        System.out.println(gmailService.getLastLabel());
    }

}
