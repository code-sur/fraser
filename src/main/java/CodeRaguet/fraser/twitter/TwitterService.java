package CodeRaguet.fraser.twitter;

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

    public void tweet(String tweet) {
        try {
            twitter.updateStatus(tweet);
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> tweets() throws TwitterException {
        List<String> tweets = new ArrayList<>();
        twitter.getHomeTimeline().forEach(status -> tweets.add(status.getText()));
        return tweets;
    }

    public void deleteTweets() throws TwitterException {
        twitter.getUserTimeline().forEach(status -> {
            try {
                twitter.destroyStatus(status.getId());
            } catch (TwitterException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
