package CodeRaguet.fraser.IT;

import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class IntegrationTest {

    static final String LAST_LABEL = "IMPORTANT";
    Properties testENV;

    @Before
    public void loadTestENV() throws IOException {
        testENV = new Properties();
        InputStream inStream = this.getClass().getResourceAsStream("testENV.properties");
        testENV.load(inStream);
        inStream.close();
    }

}
