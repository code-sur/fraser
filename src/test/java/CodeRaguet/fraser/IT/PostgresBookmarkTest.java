package CodeRaguet.fraser.IT;

import CodeRaguet.fraser.Bookmark;
import CodeRaguet.fraser.Frase;
import CodeRaguet.fraser.PostgresBookmark;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class PostgresBookmarkTest {

    @Before
    public void clearBookmark() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fraser", "fraser", "fraser");
        String sql = "TRUNCATE TABLE bookmark";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldGetCurrentFrase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fraser", "fraser", "fraser");
        String sql = "INSERT INTO BOOKMARK (FRASE, FECHA) VALUES ('First frase', DATE '2016-12-28')";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
        connection.close();

        Bookmark bookmark = new PostgresBookmark();

        assertThat(bookmark.isAt()).isEqualTo(new Frase("First frase"));
    }

}
