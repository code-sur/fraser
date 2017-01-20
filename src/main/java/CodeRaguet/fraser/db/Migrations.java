package CodeRaguet.fraser.db;

import com.heroku.sdk.jdbc.DatabaseUrl;
import org.flywaydb.core.Flyway;

public class Migrations {

    public static void main(String[] args) throws Exception {
        Flyway flyway = new Flyway();

        DatabaseUrl dataBaseUrl = DatabaseUrl.extract();
        String jdbcUrl = dataBaseUrl.jdbcUrl();
        String user = dataBaseUrl.username();
        String password = dataBaseUrl.password();
        flyway.setDataSource(jdbcUrl, user, password);

        flyway.migrate();
    }

}
