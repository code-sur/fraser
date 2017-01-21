package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.FraserRunner;
import CodeRaguet.fraser.tests.tools.PublishedFrases;
import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import CodeRaguet.fraser.tests.tools.BookmarkHandler;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.*;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.*;

public class WalkingSkeletonIT extends DatabaseTest {

    private final FraserRunner fraser = new FraserRunner(testENV);
    private final BookmarkHandler bookmarkHandler = new BookmarkHandler(connection);
    private final PublishedFrases publishedFrases = new PublishedFrases(testENV);

    @Before
    public void setUpPublishedFrases() {
        publishedFrases.deleteFrases();
    }

    @Before
    public void clearLastMessage() throws SQLException {
        bookmarkHandler.clearBookmark();
    }

    @Test
    public void runWithLastMessage() throws IOException, InterruptedException {
        bookmarkHandler.placeBookmarkOn(firstMessage());

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(secondMessage());
        publishedFrases.hasRecived(secondFrase());
    }

    @Test
    @Ignore
    public void runWithoutLastMessage() throws IOException, InterruptedException {
        //no last message

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
