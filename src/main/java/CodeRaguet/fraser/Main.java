package CodeRaguet.fraser;


import CodeRaguet.fraser.db.DatabaseBookmark;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.BookOfMessages;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.FrasesPublisher;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoMoreMessagesException;
import CodeRaguet.fraser.twitter.TwitterFrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterService;
import com.heroku.sdk.jdbc.DatabaseUrl;

import java.sql.Connection;

public class Main {

    private final BookOfMessages bookOfMessages;
    private final FrasesPublisher frasesPublisher;

    private Main(GmailService gmailService, TwitterService twitterService, Connection connection) {
        Bookmark bookmark = new DatabaseBookmark(connection);
        bookOfMessages = new BookOfMessages(gmailService, bookmark);
        frasesPublisher = new TwitterFrasesPublisher(twitterService);
    }

    public static void main(String... args) {
        GmailService gmailService = getGmailService();
        TwitterService twitterService = getTwitterService();

        try (Connection connection = DatabaseUrl.extract().getConnection()) {
            new Main(gmailService, twitterService, connection).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static TwitterService getTwitterService() {
        String consumerKey = ENV.TWITTER_CONSUMER_KEY.value();
        String consumerSecret = ENV.TWITTER_CONSUMER_SECRET.value();
        String accessToken = ENV.TWITTER_ACCESS_TOKEN.value();
        String accessTokenSecret = ENV.TWITTER_ACCESS_TOKEN_SECRET.value();
        return new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    private static GmailService getGmailService() {
        String clientSecret = ENV.GMAIL_CLIENT_SECRET.value();
        String refreshToken = ENV.GMAIL_REFRESH_TOKEN.value();
        return new GmailService(clientSecret, refreshToken);
    }

    private void run() throws BookmarkException, NoMoreMessagesException {
        frasesPublisher.publish(new Frase(bookOfMessages.next().getText()));
    }

}
