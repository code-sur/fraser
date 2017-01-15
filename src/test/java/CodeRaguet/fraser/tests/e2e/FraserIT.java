package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.gmail.GmailMessage;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.tests.tools.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FraserIT extends DatabaseTest {

    private static final Frase FIRST_FRASE = new Frase("El infierno es el olvido");
    private static final Frase SECOND_FRASE = new Frase("No te llevas nada");
    private final FraserRunner fraser = new FraserRunner();
    private TwitterServer publicationsServer;
    private PostgresBookmarkServer bookmarServer;
    private MessagesRead messagesRead = new PostgresServer(connection);
    private Publications publications = new TwitterServer(testENV);

    @Before
    public void setUpFraser() {
        fraser.with(testENV);
    }

    @Before
    public void setUpPublicationsServer() {
        publicationsServer = new TwitterServer(testENV);
        publicationsServer.deleteFrases();
    }

    @Before
    public void setUpBookmarkServer() throws SQLException {
        bookmarServer = new PostgresBookmarkServer(connection);
        bookmarServer.clearBookmark();
    }

    @Test
    public void shouldPublishFirstFrase() throws IOException, InterruptedException {
        fraser.run();

        publicationsServer.hasRecived(FIRST_FRASE);
    }

    @Test
    public void shouldPublishSecondFrase() throws SQLException, IOException, InterruptedException, NoBookmarkException {
        bookmarServer.bookmarkAt(FIRST_FRASE);

        fraser.run();

        bookmarServer.hasBookmarkAt(SECOND_FRASE);
        publicationsServer.hasRecived(SECOND_FRASE);
    }

    @Test
    public void walkingSkeleton() throws IOException, InterruptedException {
        Message firstMessage = new GmailMessage("El infierno es el olvido", "12/28/16");
        messagesRead.setLastAt(firstMessage);

        fraser.run();

        Message secondMessage = new GmailMessage("No te llevas nada", "12/30/16");
        messagesRead.lastMessageIs(secondMessage);
        publications.hasRecived(SECOND_FRASE);
    }

}
