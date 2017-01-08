package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.Frase;
import CodeRaguet.fraser.twitter.TwitterFrasesPublisher;
import CodeRaguet.fraser.twitter.TwitterService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TwitterFrasesPublisherTest {

    @Test
    public void shouldPublishAFrase() {
        TwitterService twitterService = mock(TwitterService.class);
        Frase frase = new Frase("some frase");

        TwitterFrasesPublisher twitterFrasesPublisher = new TwitterFrasesPublisher(twitterService);
        twitterFrasesPublisher.publish(frase);

        verify(twitterService).tweet(frase.toString());
    }
}
