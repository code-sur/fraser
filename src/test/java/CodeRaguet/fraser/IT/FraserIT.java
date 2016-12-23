package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserIT {

    private FraserRunner fraser = new FraserRunner();

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.setRefreshToken("1/ADyEQcVsjNnqQOUDYyBjBsI2PGk62XlTyNxsxdYaGwpzNMZI8oV8yjuxc6b5nTgQ");

        fraser.run();

        String lastLabel = "IMPORTANT";
        fraser.shows(lastLabel);
    }
}
