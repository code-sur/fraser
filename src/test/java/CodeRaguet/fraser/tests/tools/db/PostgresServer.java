package CodeRaguet.fraser.tests.tools.db;

import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.MessagesRead;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class PostgresServer implements MessagesRead {

    private static final String LAST_MESSAGE_TABLE = "LAST_MESSAGE";
    private final Connection connection;

    public PostgresServer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void setAt(Message message) {
        String sql = String.format("INSERT INTO %s (TEXT) VALUES ('%s')", LAST_MESSAGE_TABLE, message.getText());
        executeSQLStatement(sql);

    }

    @Override
    public void bookmarkShouldBeAt(Message message) {
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
        assertThat(message).isEqualTo(savedMessage);
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
