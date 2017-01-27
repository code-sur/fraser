package CodeRaguet.fraser.tests.tools.db;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.db.DatabaseBookmark;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.tests.tools.ENVTest;
import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

abstract public class DatabaseTest extends ENVTest {

    private static Connection connection;
    protected Bookmark bookmark;

    @BeforeClass
    public static void createConnection() throws SQLException {
        String jdbcURL = HerokuParserForDATABASE_URL.parse(testENV.getProperty(ENV.DATABASE_URL.name()));
        connection = DriverManager.getConnection(jdbcURL);

        Flyway flyway = new Flyway();
        flyway.setDataSource(jdbcURL, "fraser", "fraser");
        flyway.clean();
        flyway.migrate();
    }

    @Before
    public void createBookmark() {
        bookmark = new DatabaseBookmark(connection);
    }

    @Before
    public void clearTables() {
        String sql = String.format("TRUNCATE TABLE %s", DatabaseBookmark.BOOKMARK_TABLE);
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

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
