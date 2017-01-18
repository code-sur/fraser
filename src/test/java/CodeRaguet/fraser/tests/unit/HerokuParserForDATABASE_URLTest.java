package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.tests.tools.db.HerokuParserForDATABASE_URL;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HerokuParserForDATABASE_URLTest {

    @Test
    public void shouldParseJdbcURL() {
        String DATABASE_URL = "postgres://fraser:fraser@localhost:5432/fraser";
        String JdbcURL = "jdbc:postgresql://localhost:5432/fraser?user=fraser&password=fraser";

        assertThat(HerokuParserForDATABASE_URL.parse(DATABASE_URL)).isEqualTo(JdbcURL);
    }
}
