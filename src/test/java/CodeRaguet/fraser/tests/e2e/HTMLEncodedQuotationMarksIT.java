package CodeRaguet.fraser.tests.e2e;

import CodeRaguet.fraser.tests.tools.e2e.E2ETest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.fraseWithQuotationMarksProperlyRendered;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeMessageWithHTMLQuotationMarks;

public class HTMLEncodedQuotationMarksIT extends E2ETest {

    @Test
    public void test() {
        bookmarkHandler.placeBookmarkOn(beforeMessageWithHTMLQuotationMarks());

        fraser.run();

        publishedFrases.hasRecived(fraseWithQuotationMarksProperlyRendered());
    }
}
