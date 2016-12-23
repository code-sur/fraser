package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserEndToEndIT {

    private ApplicationRunner fraser = new ApplicationRunner();

    @Test
    public void getGmailLabels() throws IOException, InterruptedException {
        fraser.run();

        String lastLabel = "IMPORTANT";
        fraser.shows(lastLabel);
    }
}
