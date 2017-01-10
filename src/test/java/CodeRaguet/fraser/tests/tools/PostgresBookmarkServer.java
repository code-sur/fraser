package CodeRaguet.fraser.tests.tools;

import CodeRaguet.fraser.PostgresBookmark;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static CodeRaguet.fraser.PostgresBookmark.BOOKMARK_TABLE;
import static org.assertj.core.api.Assertions.assertThat;

public class PostgresBookmarkServer {

    private final Connection connection;
    private Bookmark bookmark;

    public PostgresBookmarkServer(Connection connection) {
        this.connection = connection;
        bookmark = new PostgresBookmark(connection);
    }

    public void bookmarkAt(Frase frase) throws SQLException {
        String sql = String.format("INSERT INTO %s (FRASE) VALUES ('%s')", BOOKMARK_TABLE, frase);
        executeSQLStatement(sql);
    }

    public void clearBookmark() {
        String sql = String.format("TRUNCATE TABLE %s", BOOKMARK_TABLE);
        executeSQLStatement(sql);
    }

    public void hasBookmarkAt(Frase frase) throws NoBookmarkException {
        assertThat(bookmark.isAt()).isEqualTo(frase);
    }

    public void executeSQLStatement(String sql) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
