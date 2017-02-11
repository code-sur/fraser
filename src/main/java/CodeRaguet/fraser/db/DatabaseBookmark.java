package CodeRaguet.fraser.db;

import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBookmark implements Bookmark {

    public static final String BOOKMARK_TABLE = "BOOKMARK";
    private final Connection connection;

    public DatabaseBookmark(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Message isOn() throws NoBookmarkException {
        ResultSet resultSet;
        String fraseText;
        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT %s from %s", "TEXT", BOOKMARK_TABLE));
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

        return new Message(fraseText);
    }

    @Override
    public void placeOn(Message message) throws BookmarkException {
        String update = String.format("UPDATE %s SET TEXT = '%s'", BOOKMARK_TABLE, message.getText());
        String insert = String.format("INSERT INTO %s (TEXT) VALUES ('%s')", BOOKMARK_TABLE, message.getText());
        String sql = bookmarkExists() ? update : insert;
        try {
            executeSQL(sql);
        } catch (SQLException e) {
            throw new BookmarkException(String.format("Can't place bookmark on message: %s", message.toString()), e);
        }
    }

    private boolean bookmarkExists() {
        boolean bookmarkExists = false;
        try {
            if (isOn() != null) {
                bookmarkExists = true;
            }
        } catch (NoBookmarkException ignored) {
        }
        return bookmarkExists;
    }

    private void executeSQL(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

}
