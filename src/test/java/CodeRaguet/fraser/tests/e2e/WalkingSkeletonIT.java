package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.tests.tools.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class WalkingSkeletonIT extends DatabaseTest {

    private static final Frase SECOND_FRASE = new Frase("No te llevas nada");
    private final FraserRunner fraser = new FraserRunner();
    private MessagesRead bookOfMessages = new PostgresServer(connection);
    private Publications publications = new TwitterServer(testENV);

    @Before
    public void setUpFraser() {
        fraser.with(testENV);
    }

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
        bookOfMessages.withBookmarkAt(Messages.first());

        fraser.run();

        bookOfMessages.bookmarkShouldBeAt(Messages.second());
        publications.hasRecived(Frases.second());
    }

}
