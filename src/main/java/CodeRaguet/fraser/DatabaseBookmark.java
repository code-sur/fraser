package CodeRaguet.fraser;

import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.NoBookmarkException;

import java.sql.*;

public class DatabaseBookmark implements Bookmark {

    public static final String BOOKMARK_TABLE = "BOOKMARK";
    private final Connection connection;

    public DatabaseBookmark(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Frase isAt() throws NoBookmarkException {

        ResultSet resultSet;
        String fraseText;
        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT %s from %s", "TEXT", "LAST_MESSAGE"));
            if (resultSet.next()) {
                fraseText = resultSet.getString("TEXT");
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
    public void setAt(Message message) {
        try {
            String sql = String.format("UPDATE LAST_MESSAGE SET TEXT = '%s'", message.getText());
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
