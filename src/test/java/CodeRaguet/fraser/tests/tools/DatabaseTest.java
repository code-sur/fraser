package CodeRaguet.fraser.tests.tools;

import CodeRaguet.fraser.ENV;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class DatabaseTest extends ENVTest {

    protected static Connection connection;

    @BeforeClass
    public static void createConnection() throws SQLException {
        String jdbcURL = HerokuDBURLParser.parse(testENV.getProperty(ENV.DATABASE_URL.name()));
        connection = DriverManager.getConnection(jdbcURL);
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
