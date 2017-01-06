package CodeRaguet.fraser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterService {
    private Twitter twitter;

    public TwitterService(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder
                .setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        this.twitter = twitterFactory.getInstance();
    }

    public void tweet(String tweet) throws TwitterException {
        twitter.updateStatus(tweet);
    }

    public List<String> tweets() throws TwitterException {
        List<String> tweets = new ArrayList<>();
        twitter.getHomeTimeline().forEach(status -> tweets.add(status.getText()));
        return tweets;
    }
}
