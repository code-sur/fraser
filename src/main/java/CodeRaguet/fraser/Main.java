package CodeRaguet.fraser;


import CodeRaguet.fraser.db.DatabaseBookmark;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.BookOfMessages;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Fraser;
import CodeRaguet.fraser.model.FrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterFrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterService;
import com.heroku.sdk.jdbc.DatabaseUrl;

import java.sql.Connection;

public class Main {

    public static void main(String... args) {
        GmailService gmailService = getGmailService();
        FrasesPublisher frasesPublisher = getFrasesPublisher();

        try (Connection connection = DatabaseUrl.extract().getConnection()) {
            BookOfMessages bookOfMessages = getBookOfMessages(gmailService, connection);
            new Fraser(frasesPublisher, bookOfMessages).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static BookOfMessages getBookOfMessages(GmailService gmailService, Connection connection) {
        Bookmark bookmark = new DatabaseBookmark(connection);
        return new BookOfMessages(gmailService, bookmark);
    }

    private static FrasesPublisher getFrasesPublisher() {
        String consumerKey = ENV.TWITTER_CONSUMER_KEY.value();
        String consumerSecret = ENV.TWITTER_CONSUMER_SECRET.value();
        String accessToken = ENV.TWITTER_ACCESS_TOKEN.value();
        String accessTokenSecret = ENV.TWITTER_ACCESS_TOKEN_SECRET.value();
        TwitterService twitterService = new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return new TwitterFrasesPublisher(twitterService);
    }

    private static GmailService getGmailService() {
        String clientSecret = ENV.GMAIL_CLIENT_SECRET.value();
        String refreshToken = ENV.GMAIL_REFRESH_TOKEN.value();
        return new GmailService(clientSecret, refreshToken);
    }

}
