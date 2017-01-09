package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.PostgresBookmark;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.tests.tools.DatabaseTest;
import CodeRaguet.fraser.tests.tools.PostgresBookmarkServer;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static CodeRaguet.fraser.PostgresBookmark.BOOKMARK_TABLE;
import static CodeRaguet.fraser.PostgresBookmark.FRASE_TEXT_COLUMN;
import static org.assertj.core.api.Assertions.assertThat;

public class PostgresBookmarkIT extends DatabaseTest {

    private Bookmark bookmark = new PostgresBookmark(connection);
    private PostgresBookmarkServer bookmarkServer = new PostgresBookmarkServer(connection);

    @Before
    public void clearBookmark() throws SQLException {
        bookmarkServer.clearBookmark();
    }

    @Test
    public void shouldGetCurrentFrase() throws SQLException, NoBookmarkException {
        String sql = String.format("INSERT INTO %s (%s) VALUES ('First frase')", BOOKMARK_TABLE, FRASE_TEXT_COLUMN);
        bookmarkServer.executeSQLStatement(sql);

        assertThat(bookmark.isAt()).isEqualTo(new Frase("First frase"));
    }

    @Test(expected = NoBookmarkException.class)
    public void shouldFailIfNoBookmark() throws NoBookmarkException {
        bookmark.isAt();
    }

    @Test
    public void shouldUpdateBookmarkAtFrase() throws NoBookmarkException, SQLException {
        String sql = String.format("INSERT INTO %s (%s) VALUES ('First frase')", BOOKMARK_TABLE, FRASE_TEXT_COLUMN);
        bookmarkServer.executeSQLStatement(sql);

        bookmark.setAt(new Frase("some frase"));

        assertThat(bookmark.isAt()).isEqualTo(new Frase("some frase"));
    }


}
