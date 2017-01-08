package CodeRaguet.fraser.twitter;

import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.FrasesPublisher;

public class TwitterFrasesPublisher implements FrasesPublisher {

    private final TwitterService twitterService;

    public TwitterFrasesPublisher(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @Override public void publish(Frase frase) {
        twitterService.tweet(frase.toString());
    }
}
