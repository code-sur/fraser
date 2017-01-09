package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.tests.tools.HerokuDBURLParser;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HerokuDBURLParserTest {

    @Test
    public void shouldParseJdbcURL() {
        String DATABASE_URL = "postgres://fraser:fraser@localhost:5432/fraser";
        String JdbcURL = "jdbc:postgresql://localhost:5432/fraser?user=fraser&password=fraser";

        assertThat(HerokuDBURLParser.parse(DATABASE_URL)).isEqualTo(JdbcURL);
    }
}
