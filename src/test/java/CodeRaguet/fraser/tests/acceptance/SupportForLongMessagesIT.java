package CodeRaguet.fraser.tests.acceptance;

import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.e2e.E2ETest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.longFrase;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.longMessage;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.secondMessage;

public class SupportForLongMessagesIT extends E2ETest {

    @Test
    public void supportLongMessages() {
        Message beforeLongMessage = secondMessage();
        bookmarkHandler.placeBookmarkOn(beforeLongMessage);

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(longMessage());
        publishedFrases.hasRecived(longFrase());
    }

}
