package CodeRaguet.fraser.tests.e2e;

import CodeRaguet.fraser.tests.tools.e2e.E2ETest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.fraseInQuotationMarks;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeMessageWithFraseInQuotationMarks;

public class ExtractFraseIT extends E2ETest {

    @Test
    public void test() {
        bookmarkHandler.placeBookmarkOn(beforeMessageWithFraseInQuotationMarks());

        fraser.run();

        publishedFrases.hasRecived(fraseInQuotationMarks());
    }
}
