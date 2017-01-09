package CodeRaguet.fraser.tests.e2e;

import CodeRaguet.fraser.PostgresBookmark;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static CodeRaguet.fraser.PostgresBookmark.*;
import static org.assertj.core.api.Assertions.assertThat;

class PostgresBookmarkServer {

    private final Connection connection;
    private Bookmark bookmark;

    PostgresBookmarkServer(Connection connection) throws SQLException {
        this.connection = connection;
        bookmark = new PostgresBookmark();
    }

    void bookmarkAt(Frase frase) throws SQLException {
        String sql = String.format("INSERT INTO %s (FRASE) VALUES ('%s')", BOOKMARK_TABLE, frase);
        executeSQLStatement(sql);
    }

    void clearBookmark() throws SQLException {
        String sql = String.format("TRUNCATE TABLE %s", BOOKMARK_TABLE);
        executeSQLStatement(sql);
    }

    void hasBookmarkAt(Frase frase) throws NoBookmarkException {
        assertThat(bookmark.isAt()).isEqualTo(frase);
    }

    private void executeSQLStatement(String sql) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
