package CodeRaguet.fraser.tests.tools.e2e;

import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
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
