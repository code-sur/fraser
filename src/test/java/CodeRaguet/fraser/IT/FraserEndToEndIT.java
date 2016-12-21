package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserEndToEndIT {

    private static final String LAST_LABEL = "IMPORTANT";

    private ApplicationRunner application = new ApplicationRunner();

    @Test
    public void getGmailLabels() throws IOException, InterruptedException {
        application.getLabels();
        application.showsLabelsContainig(LAST_LABEL);
    }
}
