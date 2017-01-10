package CodeRaguet.fraser;

import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;

import java.sql.*;

public class PostgresBookmark implements Bookmark {

    public static final String FRASE_TEXT_COLUMN = "FRASE";
    public static final String BOOKMARK_TABLE = "BOOKMARK";
    private final Connection connection;

    public PostgresBookmark(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Frase isAt() throws NoBookmarkException {

        ResultSet resultSet;
        String fraseText;
        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT %s from %s", FRASE_TEXT_COLUMN, BOOKMARK_TABLE));
            if (resultSet.next()) {
                fraseText = resultSet.getString(FRASE_TEXT_COLUMN);
            } else {
                throw new NoBookmarkException();
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Frase(fraseText);
    }

    @Override
    public void setAt(Frase frase) {
        try {
            String sql = String.format("UPDATE BOOKMARK SET FRASE = '%s'", frase);
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
