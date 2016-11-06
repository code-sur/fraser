package CodeRaguet;


import org.junit.Test;

import java.io.IOException;

public class FraserEndToEndTest {

    private static final String LAST_LABEL = "Aire de los Andes";

    private ApplicationRunner application = new ApplicationRunner();

    @Test
    public void getGmailLabels() throws IOException, InterruptedException {
        application.getLabels();
        application.showsLabelsContainig(LAST_LABEL);
    }
}
