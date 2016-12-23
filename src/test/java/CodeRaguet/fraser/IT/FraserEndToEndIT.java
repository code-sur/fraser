package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserEndToEndIT {

    private ApplicationRunner application = new ApplicationRunner();

    @Test
    public void getGmailLabels() throws IOException, InterruptedException {
        application.run();

        String lastLabel = "IMPORTANT";
        application.shows(lastLabel);
    }
}
