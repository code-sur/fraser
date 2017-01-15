package CodeRaguet.fraser.tests.tools;

import CodeRaguet.fraser.model.Message;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
