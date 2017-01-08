package CodeRaguet.fraser.tests.e2e;

import CodeRaguet.fraser.model.Frase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BookmarkServer {

    public BookmarkServer(Properties testENV) throws SQLException {
    }

    public void bookmarkAt(Frase firstFrase) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fraser", "fraser", "fraser");
        String sql = "INSERT INTO BOOKMARK (FRASE, FECHA) VALUES ('El infierno es el olvido', DATE '2016-12-28')";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
        connection.close();
    }

    public void clearBookmark() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fraser", "fraser", "fraser");
        String sql = "TRUNCATE TABLE bookmark";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
        connection.close();
    }
}
