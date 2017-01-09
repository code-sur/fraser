package CodeRaguet.fraser;

import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;

import java.sql.*;

public class PostgresBookmark implements Bookmark {

    private static final String FRASE_TEXT_COLUMN = "FRASE";
    public static final String BOOKMARK_TABLE = "BOOKMARK";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/fraser";
    public static final String DB_USER = "fraser";
    public static final String DB_PASSWORD = "fraser";

    @Override
    public Frase isAt() throws NoBookmarkException {

        ResultSet resultSet;
        String fraseText;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT %s from %s", FRASE_TEXT_COLUMN, BOOKMARK_TABLE));
            if (resultSet.next()) {
                fraseText = resultSet.getString(FRASE_TEXT_COLUMN);
            } else {
                throw new NoBookmarkException();
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Frase(fraseText);
    }

    @Override public void setAt(Frase frase) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = String.format("UPDATE BOOKMARK SET FRASE = '%s'", frase);
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
