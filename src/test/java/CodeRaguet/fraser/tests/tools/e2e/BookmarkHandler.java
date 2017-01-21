package CodeRaguet.fraser.tests.tools.e2e;

import CodeRaguet.fraser.model.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkHandler {

    private static final String LAST_MESSAGE_TABLE = "LAST_MESSAGE";
    private final Connection connection;

    public BookmarkHandler(Connection connection) {
        this.connection = connection;
    }

    public void placeBookmarkOn(Message message) {
        String sql = String.format("INSERT INTO %s (TEXT) VALUES ('%s')", LAST_MESSAGE_TABLE, message.getText());
        executeSQLStatement(sql);
    }

    public void bookmarkShouldBeOn(Message message) {
        ResultSet resultSet;
        String messageText;
        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT TEXT from %s", "LAST_MESSAGE"));
            resultSet.next();
            messageText = resultSet.getString("TEXT");

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Message savedMessage = new Message(messageText);
        assertThat(savedMessage).isEqualTo(message);
    }

    public void clearBookmark() {
        String sql = String.format("TRUNCATE TABLE %s", LAST_MESSAGE_TABLE);
        executeSQLStatement(sql);
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
