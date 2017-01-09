package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.PostgresBookmark;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static CodeRaguet.fraser.PostgresBookmark.BOOKMARK_TABLE;
import static CodeRaguet.fraser.PostgresBookmark.FRASE_TEXT_COLUMN;
import static org.assertj.core.api.Assertions.assertThat;

public class PostgresBookmarkIT {

    private static Connection connection;

    @BeforeClass
    public static void createConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fraser", "fraser", "fraser");
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Before
    public void clearBookmark() throws SQLException {
        String sql = String.format("TRUNCATE TABLE %s", BOOKMARK_TABLE);
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    @Test
    public void shouldGetCurrentFrase() throws SQLException, NoBookmarkException {
        String sql = String.format("INSERT INTO %s (%s) VALUES ('First frase')", BOOKMARK_TABLE, FRASE_TEXT_COLUMN);
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();

        Bookmark bookmark = new PostgresBookmark();

        assertThat(bookmark.isAt()).isEqualTo(new Frase("First frase"));
    }

    @Test(expected = NoBookmarkException.class)
    public void shouldFailIfNoBookmark() throws NoBookmarkException {
        Bookmark bookmark = new PostgresBookmark();
        bookmark.isAt();
    }

    @Test
    public void shouldUpdateBookmarkAtFrase() throws NoBookmarkException, SQLException {
        String sql = String.format("INSERT INTO %s (%s) VALUES ('First frase')", BOOKMARK_TABLE, FRASE_TEXT_COLUMN);
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();

        Bookmark bookmark = new PostgresBookmark();

        bookmark.setAt(new Frase("some frase"));

        assertThat(bookmark.isAt()).isEqualTo(new Frase("some frase"));
    }


}
