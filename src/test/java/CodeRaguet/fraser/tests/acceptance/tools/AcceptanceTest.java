package CodeRaguet.fraser.tests.acceptance.tools;

import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import org.junit.Before;

abstract public class AcceptanceTest extends DatabaseTest {
    protected final FraserRunner fraser = new FraserRunner(testENV);
    protected final PublishedFrases publishedFrases = new PublishedFrases(testENV);
    protected BookmarkHandler bookmarkHandler;

    @Before
    public void setUpBookmarkHandler() {
        bookmarkHandler = new BookmarkHandler(bookmark);
    }

    @Before
    public void setUpPublishedFrases() {
        publishedFrases.deleteFrases();
    }
}
