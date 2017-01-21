package CodeRaguet.fraser.db;

import CodeRaguet.fraser.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBookmark implements Bookmark {

    public static final String LAST_MESSAGE_TABLE = "LAST_MESSAGE";
    private final Connection connection;

    public DatabaseBookmark(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Frase isOn() throws NoBookmarkException {

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
    public void placeOn(Message message) throws BookmarkException {
        try {
            String sql = String.format("UPDATE LAST_MESSAGE SET TEXT = '%s'", message.getText());
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new BookmarkException(String.format("Can't place bookmark on message: %s", message.toString()), e);
        }
    }

}
