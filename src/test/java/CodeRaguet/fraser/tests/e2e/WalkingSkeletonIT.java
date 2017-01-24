package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.E2ETest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.*;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.*;

public class WalkingSkeletonIT extends E2ETest {

    @Test
    public void withPreviousBookmark() {
        bookmarkHandler.placeBookmarkOn(firstMessage());

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(secondMessage());
        publishedFrases.hasRecived(secondFrase());
    }

    @Test
    public void runWithoutPreviousBookmark() {
        //no bookmark

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(firstMessage());
        publishedFrases.hasRecived(firstFrase());
    }

    @Test
    public void supportLongMessages() {
        Message beforeLongMessage = secondMessage();
        bookmarkHandler.placeBookmarkOn(beforeLongMessage);

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(longMessage());
        publishedFrases.hasRecived(longFrase());
    }

}
