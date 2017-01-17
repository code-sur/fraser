package CodeRaguet.fraser.tests.tools;

import CodeRaguet.fraser.PostgresBookmark;
import CodeRaguet.fraser.gmail.GmailMessage;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.NoBookmarkException;

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
    public void setLastAt(Message message) {
        String sql = String.format("INSERT INTO %s (TEXT, DATE) VALUES ('%s', '%s')", LAST_MESSAGE_TABLE, message.getText(), message.getDate());
        executeSQLStatement(sql);

    }

    @Override
    public void lastMessageIs(Message message) {
        ResultSet resultSet;
        String messageText;
        String messageDate;
        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT TEXT, DATE from %s", "LAST_MESSAGE"));
            resultSet.next();
            messageText = resultSet.getString("TEXT");
            messageDate = resultSet.getString("DATE");

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Message savedMessage = new GmailMessage(messageText, messageDate);
        assertThat(message).isEqualTo(savedMessage);
    }

    public void executeSQLStatement(String sql) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
