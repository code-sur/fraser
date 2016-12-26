package CodeRaguet.fraser.IT;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FraserIT {

    private final FraserRunner fraser = new FraserRunner();
    private final Properties testENV = new Properties();

    @Before
    public void loadTestENV() throws IOException {
        InputStream inStream = this.getClass().getResourceAsStream("testENV.properties");
        testENV.load(inStream);
        inStream.close();
    }

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.with(testENV);

        fraser.run();

        String lastLabel = "IMPORTANT\n";
        fraser.shows(lastLabel);
    }

}
