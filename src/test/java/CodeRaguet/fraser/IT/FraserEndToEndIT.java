package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserEndToEndIT {

    private FraserRunner fraser = new FraserRunner();

    @Test
    public void getGmailLabels() throws IOException, InterruptedException {
        fraser.run();

        String lastLabel = "IMPORTANT";
        fraser.shows(lastLabel);
    }
}
