package CodeRaguet.fraser.IT;

import CodeRaguet.fraser.TwitterService;
import org.junit.Test;
import twitter4j.TwitterException;

import static org.assertj.core.api.Assertions.*;

public class TwitterServiceIT {

    @Test
    public void test() throws TwitterException {
        TwitterService twitterService = new TwitterService();

        String someTweet = "Test tweet";
        twitterService.tweet(someTweet);

        assertThat(twitterService.tweets()).contains(someTweet);
    }
}
