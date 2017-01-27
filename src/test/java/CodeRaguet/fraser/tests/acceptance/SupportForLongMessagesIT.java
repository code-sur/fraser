package CodeRaguet.fraser.tests.acceptance;

import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.acceptance.tools.AcceptanceTest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.longFrase;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.longMessage;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.secondMessage;

public class SupportForLongMessagesIT extends AcceptanceTest {

    @Test
    public void supportLongMessages() {
        Message beforeLongMessage = secondMessage();
        bookmarkHandler.placeBookmarkOn(beforeLongMessage);

        fraser.run();

        bookmarkHandler.bookmarkShouldBeOn(longMessage());
        publishedFrases.hasRecived(longFrase());
    }

}
