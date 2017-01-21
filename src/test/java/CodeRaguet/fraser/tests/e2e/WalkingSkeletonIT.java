package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import CodeRaguet.fraser.tests.tools.e2e.BookmarkHandler;
import CodeRaguet.fraser.tests.tools.e2e.FraserRunner;
import CodeRaguet.fraser.tests.tools.e2e.PublishedFrases;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.*;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.*;

public class WalkingSkeletonIT extends DatabaseTest {

    private final FraserRunner fraser = new FraserRunner(testENV);
    private BookmarkHandler bookmarkHandler;
    private final PublishedFrases publishedFrases = new PublishedFrases(testENV);

    @Before
    public void setUpBookmarkHandler() {
        bookmarkHandler = new BookmarkHandler(bookmark);
    }

    @Before
    public void setUpPublishedFrases() {
        publishedFrases.deleteFrases();
    }

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
    @Ignore
    public void supportLongMessages() {
        Message beforeLongMessage = secondMessage();
        bookmarkHandler.placeBookmarkOn(beforeLongMessage);

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(longMessage());
        publishedFrases.hasRecived(longFrase());
    }

}
