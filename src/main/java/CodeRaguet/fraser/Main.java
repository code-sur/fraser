package CodeRaguet.fraser;


import CodeRaguet.fraser.gmail.GmailBookOfFrases;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.BookOfFrases;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.FrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterFrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterService;
import com.heroku.sdk.jdbc.DatabaseUrl;

import java.sql.Connection;

public class Main {

    private final BookOfFrases bookOfFrases;
    private final FrasesPublisher frasesPublisher;
    private Bookmark bookmark;

    private Main(GmailService gmailService, TwitterService twitterService, Connection connection) {
        bookmark = new DatabaseBookmark(connection);
        bookOfFrases = new GmailBookOfFrases(gmailService, bookmark);
        frasesPublisher = new TwitterFrasesPublisher(twitterService);
    }

    public static void main(String... args) {
        String clientSecret = ENV.GMAIL_CLIENT_SECRET.value();
        String refreshToken = ENV.GMAIL_REFRESH_TOKEN.value();
        GmailService gmailService = new GmailService(clientSecret, refreshToken);

        String consumerKey = ENV.TWITTER_CONSUMER_KEY.value();
        String consumerSecret = ENV.TWITTER_CONSUMER_SECRET.value();
        String accessToken = ENV.TWITTER_ACCESS_TOKEN.value();
        String accessTokenSecret = ENV.TWITTER_ACCESS_TOKEN_SECRET.value();
        TwitterService twitterService = new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        try (Connection connection = DatabaseUrl.extract().getConnection()) {
            new Main(gmailService, twitterService, connection).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void run() {
        frasesPublisher.publish(bookOfFrases.next());
    }

}
