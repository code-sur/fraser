package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.FraserRunner;
import CodeRaguet.fraser.tests.tools.PublishedFrases;
import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import CodeRaguet.fraser.tests.tools.db.LastMessage;
import CodeRaguet.fraser.tests.tools.fixtures.Frases;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class WalkingSkeletonIT extends DatabaseTest {

    private final FraserRunner fraser = new FraserRunner(testENV);
    private final LastMessage lastMessage = new LastMessage(connection);
    private final PublishedFrases publishedFrases = new PublishedFrases(testENV);

    @Before
    public void setUpPublishedFrases() {
        publishedFrases.deleteFrases();
    }

    @Before
    public void clearLastMessage() throws SQLException {
        lastMessage.clear();
    }

    @Test
    public void walkingSkeleton() throws IOException, InterruptedException {
        lastMessage.setAt(Messages.first());

        fraser.run();

        lastMessage.shouldBeAt(Messages.second());
        publishedFrases.hasRecived(Frases.second());
    }

    @Test
    public void supportLongMessages() throws IOException, InterruptedException {
        Message beforeLongMessage = Messages.second();
        lastMessage.setAt(beforeLongMessage);

        fraser.run();

        lastMessage.shouldBeAt(Messages.longMessage());
        publishedFrases.hasRecived(Frases.longFrase());
    }

}
