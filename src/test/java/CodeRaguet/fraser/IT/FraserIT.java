package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserIT extends IntegrationTest {

    private final FraserRunner fraser = new FraserRunner();

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.with(testENV);

        fraser.run();

        fraser.shows("No te llevas nada" + "\n" + "El infierno es el olvido" + "\n");
    }

}
