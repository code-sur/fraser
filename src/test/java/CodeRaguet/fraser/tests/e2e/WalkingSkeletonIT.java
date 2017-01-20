package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.tests.tools.FraserRunner;
import CodeRaguet.fraser.tests.tools.MessagesRead;
import CodeRaguet.fraser.tests.tools.Publications;
import CodeRaguet.fraser.tests.tools.TwitterServer;
import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import CodeRaguet.fraser.tests.tools.db.PostgresBookmarkServer;
import CodeRaguet.fraser.tests.tools.db.PostgresServer;
import CodeRaguet.fraser.tests.tools.fixtures.Frases;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class WalkingSkeletonIT extends DatabaseTest {

    private final FraserRunner fraser = new FraserRunner(testENV);
    private final MessagesRead lastMessage = new PostgresServer(connection);
    private final Publications publications = new TwitterServer(testENV);

    @Before
    public void setUpPublicationsServer() {
        TwitterServer publicationsServer = new TwitterServer(testENV);
        publicationsServer.deleteFrases();
    }

    @Before
    public void setUpBookmarkServer() throws SQLException {
        PostgresBookmarkServer bookmarServer = new PostgresBookmarkServer(connection);
        bookmarServer.clearBookmark();
    }

    @Test
    public void walkingSkeleton() throws IOException, InterruptedException {
        lastMessage.setAt(Messages.first());

        fraser.run();

        lastMessage.bookmarkShouldBeAt(Messages.second());
        publications.hasRecived(Frases.second());
    }

}
