package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.tests.tools.e2e.E2ETest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.firstFrase;
import static CodeRaguet.fraser.tests.tools.fixtures.Frases.secondFrase;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.firstMessage;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.secondMessage;

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

}
