package CodeRaguet.fraser.IT;

import CodeRaguet.fraser.TwitterService;
import org.junit.Before;
import org.junit.Test;
import twitter4j.TwitterException;

import static org.assertj.core.api.Assertions.*;

public class TwitterServiceIT {

    private TwitterService twitterService;

    @Before
    public void setUpTwitterService() throws TwitterException {
        String consumerKey = "dVzv41qr3agSl2oJ76TUDtXwW";
        String consumerSecret = "QHqKfJqGLisVK9xDS2BYyeufIEN3qqT2uNskgIXn7ldWlTZmEe";
        String accessToken = "817432819001655304-wWw8RaiEQ2IW8CrqKvpwJRzHsqILfho";
        String accessTokenSecret = "iHewGbF13Ox67qzZGaV9Ki0NSHkCCuPMu1UaYD9qpX73a";
        twitterService = new TwitterService(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        twitterService.deleteTweets();
    }

    @Test
    public void test() throws TwitterException {
        String someTweet = "Test tweet";

        twitterService.tweet(someTweet);

        assertThat(twitterService.tweets()).contains(someTweet);
    }
}
