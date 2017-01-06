package CodeRaguet.fraser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterService {
    private Twitter twitter;

    public TwitterService() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDaemonEnabled(true)
                .setOAuthConsumerKey("dVzv41qr3agSl2oJ76TUDtXwW")
                .setOAuthConsumerSecret("QHqKfJqGLisVK9xDS2BYyeufIEN3qqT2uNskgIXn7ldWlTZmEe")
                .setOAuthAccessToken("817432819001655304-wWw8RaiEQ2IW8CrqKvpwJRzHsqILfho")
                .setOAuthAccessTokenSecret("iHewGbF13Ox67qzZGaV9Ki0NSHkCCuPMu1UaYD9qpX73a");
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
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
