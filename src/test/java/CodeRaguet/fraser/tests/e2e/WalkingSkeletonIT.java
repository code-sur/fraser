package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.FraserRunner;
import CodeRaguet.fraser.tests.tools.PublishedFrases;
import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import CodeRaguet.fraser.tests.tools.db.LastMessage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.*;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.*;

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
    public void runWithLastMessage() throws IOException, InterruptedException {
        lastMessage.setAt(firstMessage());

        fraser.run();

        lastMessage.shouldBeAt(secondMessage());
        publishedFrases.hasRecived(secondFrase());
    }

    @Test
    @Ignore
    public void runWithoutLastMessage() throws IOException, InterruptedException {
        //no last message

        fraser.run();

        lastMessage.shouldBeAt(firstMessage());
        publishedFrases.hasRecived(firstFrase());
    }

    @Test
    @Ignore
    public void supportLongMessages() {
        Message beforeLongMessage = secondMessage();
        lastMessage.setAt(beforeLongMessage);

        fraser.run();

        lastMessage.shouldBeAt(longMessage());
        publishedFrases.hasRecived(longFrase());
    }

}
