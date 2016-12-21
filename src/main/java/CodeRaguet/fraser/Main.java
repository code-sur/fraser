package CodeRaguet.fraser;


import java.io.IOException;

public class Main {

    public static void main(String... args) throws IOException {
        GmailService gmailService = new GmailService();
        System.out.println(gmailService.getLastLabel());
    }

}
