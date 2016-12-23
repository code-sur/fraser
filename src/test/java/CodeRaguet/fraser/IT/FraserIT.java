package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserIT {

    private FraserRunner fraser = new FraserRunner();

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.run();

        String lastLabel = "IMPORTANT";
        fraser.shows(lastLabel);
    }
}
