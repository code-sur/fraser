package CodeRaguet.fraser.tests.tools;

import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import CodeRaguet.fraser.tests.tools.e2e.BookmarkHandler;
import CodeRaguet.fraser.tests.tools.e2e.FraserRunner;
import CodeRaguet.fraser.tests.tools.e2e.PublishedFrases;
import org.junit.Before;

public class E2ETest extends DatabaseTest {
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
