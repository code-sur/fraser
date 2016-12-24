package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FraserIT {

    private FraserRunner fraser = new FraserRunner();

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        Properties gmailProperties = new Properties();

        InputStream inStream = this.getClass().getResourceAsStream("gmail.properties");
        gmailProperties.load(inStream);
        inStream.close();

        fraser.setRefreshToken(gmailProperties.getProperty("REFRESH_TOKEN"));
        fraser.setClientSecret(gmailProperties.getProperty("CLIENT_SECRET"));

        fraser.run();

        String lastLabel = "IMPORTANT";
        fraser.shows(lastLabel);
    }
}
