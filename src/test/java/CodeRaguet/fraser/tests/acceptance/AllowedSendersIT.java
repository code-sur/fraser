package CodeRaguet.fraser.tests.acceptance;

import CodeRaguet.fraser.tests.acceptance.tools.AcceptanceTest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.nextFraseNotFromStranger;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeMessageFromStranger;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.nextMessageNotFromStranger;

public class AllowedSendersIT extends AcceptanceTest {

    @Test
    public void skipMessagesFromStrangers() {
        bookmarkHandler.placeBookmarkOn(beforeMessageFromStranger());

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(nextMessageNotFromStranger());
        publishedFrases.hasRecived(nextFraseNotFromStranger());
    }
}
