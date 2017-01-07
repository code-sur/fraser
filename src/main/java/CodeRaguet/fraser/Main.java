package CodeRaguet.fraser;


import CodeRaguet.fraser.gmail.GmailBookOfFrases;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.twitter.TwitterFrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterService;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    private final BookOfFrases bookOfFrases;
    private final FrasesPublisher frasesPublisher;

    private Main(GmailService gmailService, TwitterService twitterService) {
        bookOfFrases = new GmailBookOfFrases(gmailService);
        frasesPublisher = new TwitterFrasesPublisher(twitterService);
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        String clientSecret = ENV.GMAIL_CLIENT_SECRET.value();
        String refreshToken = ENV.GMAIL_REFRESH_TOKEN.value();
        GmailService gmailService = new GmailService(clientSecret, refreshToken);

        String consumerKey = ENV.TWITTER_CONSUMER_KEY.value();
        String consumerSecret = ENV.TWITTER_CONSUMER_SECRET.value();
        String accessToken = ENV.TWITTER_ACCESS_TOKEN.value();
        String accessTokenSecret = ENV.TWITTER_ACCESS_TOKEN_SECRET.value();
        TwitterService twitterService = new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        new Main(gmailService, twitterService).run();
    }

    private void run() throws IOException {
        frasesPublisher.publish(bookOfFrases.next());
    }

}
