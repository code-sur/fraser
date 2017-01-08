package CodeRaguet.fraser.integration;

import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class IntegrationTest {

    protected Properties testENV;

    @Before
    public void loadTestENV() throws IOException {
        testENV = new Properties();
        InputStream inStream = IntegrationTest.class.getResourceAsStream("testENV.properties");
        testENV.load(inStream);
        inStream.close();
    }

}
