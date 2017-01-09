package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.tests.tools.DatabaseTest;
import CodeRaguet.fraser.tests.tools.PostgresBookmarkServer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FraserIT extends DatabaseTest {

    private static final Frase FIRST_FRASE = new Frase("El infierno es el olvido");
    private static final Frase SECOND_FRASE = new Frase("No te llevas nada");
    private final FraserRunner fraser = new FraserRunner();
    private FraserPublicationsServer frasePublicationsServer;
    private PostgresBookmarkServer bookmarServer;

    @Before
    public void setUpFraser() {
        fraser.with(testENV);
    }

    @Before
    public void setUpPublicationsServer() {
        frasePublicationsServer = new FraserPublicationsServer(testENV);
        frasePublicationsServer.deleteFrases();
    }

    @Before
    public void setUpBookmarkServer() throws SQLException {
        bookmarServer = new PostgresBookmarkServer(connection);
        bookmarServer.clearBookmark();
    }

    @Test
    public void shouldPublishFirstFrase() throws IOException, InterruptedException {
        fraser.run();

        frasePublicationsServer.hasRecived(FIRST_FRASE);
    }

    @Test
    public void shouldPublishSecondFrase() throws SQLException, IOException, InterruptedException, NoBookmarkException {
        bookmarServer.bookmarkAt(FIRST_FRASE);

        fraser.run();

        bookmarServer.hasBookmarkAt(SECOND_FRASE);
        frasePublicationsServer.hasRecived(SECOND_FRASE);
    }

}
