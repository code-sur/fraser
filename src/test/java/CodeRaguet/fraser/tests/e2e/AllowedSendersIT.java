package CodeRaguet.fraser.tests.e2e;

import CodeRaguet.fraser.tests.tools.E2ETest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.nextFraseNotFromStranger;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.beforeMessageFromStranger;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.nextMessageNotFromStranger;

public class AllowedSendersIT extends E2ETest {

    @Test
    public void skipMessagesFromStrangers() {
        bookmarkHandler.placeBookmarkOn(beforeMessageFromStranger());

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(nextMessageNotFromStranger());
        publishedFrases.hasRecived(nextFraseNotFromStranger());
    }
}
