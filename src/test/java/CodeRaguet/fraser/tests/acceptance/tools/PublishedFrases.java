package CodeRaguet.fraser.tests.acceptance.tools;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.model.Frase;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class PublishedFrases {

    private final Twitter twitter;

    PublishedFrases(Properties env) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder
                .setDebugEnabled(true)
                .setOAuthConsumerKey(env.getProperty(ENV.TWITTER_CONSUMER_KEY.name()))
                .setOAuthConsumerSecret(env.getProperty(ENV.TWITTER_CONSUMER_SECRET.name()))
                .setOAuthAccessToken(env.getProperty(ENV.TWITTER_ACCESS_TOKEN.name()))
                .setOAuthAccessTokenSecret(env.getProperty(ENV.TWITTER_ACCESS_TOKEN_SECRET.name()));
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }

    public void hasRecived(Frase frase) {
        List<String> tweets = new ArrayList<>();
        try {
            twitter.getHomeTimeline().forEach(status -> tweets.add(status.getText()));
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }
        assertThat(tweets).contains(frase.toString());
    }

    void deleteFrases() {
        try {
            twitter.getUserTimeline().forEach(status -> {
                try {
                    twitter.destroyStatus(status.getId());
                } catch (TwitterException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }
    }
}
