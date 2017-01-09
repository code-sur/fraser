package CodeRaguet.fraser.tests.e2e;


import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.tests.HerokuDBURLParser;
import CodeRaguet.fraser.tests.integration.ENVTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FraserIT extends ENVTest {

    private static final Frase FIRST_FRASE = new Frase("El infierno es el olvido");
    private static final Frase SECOND_FRASE = new Frase("No te llevas nada");
    private static Connection connection;
    private final FraserRunner fraser = new FraserRunner();
    private FraserPublicationsServer frasePublicationsServer;
    private PostgresBookmarkServer bookmarServer;

    @BeforeClass
    public static void createConnection() throws SQLException {
        String jdbcURL = HerokuDBURLParser.parse(testENV.getProperty(ENV.DATABASE_URL.name()));
        connection = DriverManager.getConnection(jdbcURL);
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

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
        bookmarServer = new PostgresBookmarkServer();
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
