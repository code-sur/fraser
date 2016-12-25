package CodeRaguet.fraser.IT;


import CodeRaguet.fraser.ENV;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FraserIT {

    private final FraserRunner fraser = new FraserRunner();
    private final Properties gmailProperties = new Properties();

    @Before
    public void loadGmailProperties() throws IOException {
        InputStream inStream = this.getClass().getResourceAsStream("gmail.properties");
        gmailProperties.load(inStream);
        inStream.close();
    }

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.setRefreshToken(gmailProperties.getProperty(ENV.REFRESH_TOKEN.toString()));
        fraser.setClientSecret(gmailProperties.getProperty(ENV.CLIENT_SECRET.toString()));

        fraser.run();

        String lastLabel = "IMPORTANT";
        fraser.shows(lastLabel);
    }

}
