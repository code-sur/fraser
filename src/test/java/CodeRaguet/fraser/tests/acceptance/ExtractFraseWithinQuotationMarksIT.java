package CodeRaguet.fraser.tests.acceptance;

import CodeRaguet.fraser.tests.acceptance.tools.AcceptanceTest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.fraseWithinQuotationMarks;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeFraseWithinHTMLQuotationMarks;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeMessageWithFraseAndTextToDiscard;

public class ExtractFraseWithinQuotationMarksIT extends AcceptanceTest {

    @Test
    public void extractFraseWithinHTMLQuotationMarks() {
        bookmarkHandler.placeBookmarkOn(beforeFraseWithinHTMLQuotationMarks());

        fraser.run();

        publishedFrases.hasRecived(fraseWithinQuotationMarks());
    }

    @Test
    public void extractFraseWithinQuotationMarksAndDiscardRemainingText() {
        bookmarkHandler.placeBookmarkOn(beforeMessageWithFraseAndTextToDiscard());

        fraser.run();

        publishedFrases.hasRecived(fraseWithinQuotationMarks());
    }
}
