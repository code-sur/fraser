package CodeRaguet.fraser.tests.e2e;

import CodeRaguet.fraser.PostgresBookmark;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class BookmarkServer {

    private Bookmark bookmark;

    BookmarkServer(Properties testENV) throws SQLException {
        bookmark = new PostgresBookmark();
    }

    void bookmarkAt(Frase firstFrase) throws SQLException {
        String sql = "INSERT INTO BOOKMARK (FRASE, FECHA) VALUES ('El infierno es el olvido', DATE '2016-12-28')";
        executeSQLStatement(sql);
    }

    void clearBookmark() throws SQLException {
        String sql = "TRUNCATE TABLE bookmark";
        executeSQLStatement(sql);
    }

    void hasBookmarkAt(Frase frase) throws NoBookmarkException {
        assertThat(bookmark.isAt()).isEqualTo(frase);
    }

    private void executeSQLStatement(String sql) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fraser", "fraser", "fraser");
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
