package CodeRaguet.fraser.tests.integration;

import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ENVTest {

    protected static Properties testENV;

    @BeforeClass
    public static void loadTestENV() throws IOException {
        testENV = new Properties();
        InputStream inStream = ENVTest.class.getResourceAsStream("testENV.properties");
        testENV.load(inStream);
        inStream.close();
    }

}
