package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserIT extends IntegrationTest {

    public static final String FIRST_FRASE = "El infierno es el olvido";
    private final FraserRunner fraser = new FraserRunner();

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.with(testENV);

        fraser.run();

        fraser.posterShows(FIRST_FRASE);
    }

}
