package CodeRaguet.fraser.e2e;


import CodeRaguet.fraser.Frase;
import CodeRaguet.fraser.integration.IntegrationTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FraserIT extends IntegrationTest {

    private static final Frase FIRST_FRASE = new Frase("El infierno es el olvido");
    private static final String SECOND_FRASE = "No te llevas nada";
    private final FraserRunner fraser = new FraserRunner();
    private FraserPublicationsServer frasePublicationsServer;
    private BookmarkServer bookmarServer;

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
        bookmarServer = new BookmarkServer(testENV);
        bookmarServer.clearBookmark();
    }

    @Test
    public void shouldPublishFirstFrase() throws IOException, InterruptedException {
        fraser.run();

        frasePublicationsServer.hasRecived(FIRST_FRASE);
    }

    @Test
    public void shouldPublishSecondFrase() throws SQLException, IOException, InterruptedException {
        bookmarServer.bookmarkAt(FIRST_FRASE);

        fraser.run();

        frasePublicationsServer.hasRecived(new Frase(SECOND_FRASE));
    }

}
