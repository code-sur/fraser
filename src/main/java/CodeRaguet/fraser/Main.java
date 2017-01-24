package CodeRaguet.fraser;


import CodeRaguet.fraser.db.DatabaseBookmark;
import CodeRaguet.fraser.gmail.GmailPostOffice;
import CodeRaguet.fraser.model.*;
import CodeRaguet.fraser.twitter.TwitterFrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterService;
import com.heroku.sdk.jdbc.DatabaseUrl;

import java.sql.Connection;

public class Main {

    public static void main(String... args) {
        PostOffice postOffice = getPostOffice();
        FrasesPublisher frasesPublisher = getFrasesPublisher();

        try (Connection connection = DatabaseUrl.extract().getConnection()) {
            BookOfMessages bookOfMessages = getBookOfMessages(postOffice, connection);
            new FraserApplication(frasesPublisher, bookOfMessages).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static BookOfMessages getBookOfMessages(PostOffice postOffice, Connection connection) {
        Bookmark bookmark = new DatabaseBookmark(connection);
        MessageFilter messageFilter = new MessageFilter();
        return new BookOfMessages(postOffice, bookmark, messageFilter);
    }

    private static FrasesPublisher getFrasesPublisher() {
        String consumerKey = ENV.TWITTER_CONSUMER_KEY.value();
        String consumerSecret = ENV.TWITTER_CONSUMER_SECRET.value();
        String accessToken = ENV.TWITTER_ACCESS_TOKEN.value();
        String accessTokenSecret = ENV.TWITTER_ACCESS_TOKEN_SECRET.value();
        TwitterService twitterService = new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return new TwitterFrasesPublisher(twitterService);
    }

    private static PostOffice getPostOffice() {
        String clientSecret = ENV.GMAIL_CLIENT_SECRET.value();
        String refreshToken = ENV.GMAIL_REFRESH_TOKEN.value();
        return new GmailPostOffice(clientSecret, refreshToken);
    }

}
