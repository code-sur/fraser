package CodeRaguet.fraser.tests.e2e;

import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.tests.tools.e2e.E2ETest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.fraseInQuotationMarks;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeMessageWithQuotationMarks;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeMessageWithHTMLQuotationMarks;

public class ExtractFraseIT extends E2ETest {

    @Test
    public void test() {
        bookmarkHandler.placeBookmarkOn(beforeMessageWithHTMLQuotationMarks());

        fraser.run();

        publishedFrases.hasRecived(new Frase("Frase with quotation marks"));
    }

    @Test
    public void test1() {
        bookmarkHandler.placeBookmarkOn(beforeMessageWithQuotationMarks());

        fraser.run();

        publishedFrases.hasRecived(fraseInQuotationMarks());
    }
}
