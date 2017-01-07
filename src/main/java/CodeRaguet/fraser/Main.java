package CodeRaguet.fraser;


import CodeRaguet.fraser.gmail.GmailService;
import com.google.api.services.gmail.model.Message;
import twitter4j.TwitterException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException, GeneralSecurityException, TwitterException {
        String clientSecret = ENV.GMAIL_CLIENT_SECRET.value();
        String refreshToken = ENV.GMAIL_REFRESH_TOKEN.value();
        GmailService gmailService = new GmailService(clientSecret, refreshToken);
        List<Message> messages = gmailService.messagesWithFrase();

        String consumerKey = ENV.TWITTER_CONSUMER_KEY.value();
        String consumerSecret = ENV.TWITTER_CONSUMER_SECRET.value();
        String accessToken = ENV.TWITTER_ACCESS_TOKEN.value();
        String accessTokenSecret = ENV.TWITTER_ACCESS_TOKEN_SECRET.value();
        TwitterService twitterService = new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        twitterService.tweet(messages.get(messages.size() - 1).getSnippet());
    }

}
