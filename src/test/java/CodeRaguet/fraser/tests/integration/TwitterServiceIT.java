package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.twitter.TwitterService;
import org.junit.Before;
import org.junit.Test;
import twitter4j.TwitterException;

import static org.assertj.core.api.Assertions.*;

public class TwitterServiceIT extends ENVTest {

    private TwitterService twitterService;

    @Before
    public void setUpTwitterService() throws TwitterException {
        String consumerKey = testENV.getProperty(ENV.TWITTER_CONSUMER_KEY.name());
        String consumerSecret = testENV.getProperty(ENV.TWITTER_CONSUMER_SECRET.name());
        String accessToken = testENV.getProperty(ENV.TWITTER_ACCESS_TOKEN.name());
        String accessTokenSecret = testENV.getProperty(ENV.TWITTER_ACCESS_TOKEN_SECRET.name());
        twitterService = new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        twitterService.deleteTweets();
    }

    @Test
    public void shouldTweet() throws TwitterException {
        String someTweet = "Test tweet";

        twitterService.tweet(someTweet);

        assertThat(twitterService.tweets()).contains(someTweet);
    }
}
