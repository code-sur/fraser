package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.PostgresBookmark;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.tests.tools.DatabaseTest;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static CodeRaguet.fraser.PostgresBookmark.BOOKMARK_TABLE;
import static CodeRaguet.fraser.PostgresBookmark.FRASE_TEXT_COLUMN;
import static org.assertj.core.api.Assertions.assertThat;

public class PostgresBookmarkIT extends DatabaseTest {

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
