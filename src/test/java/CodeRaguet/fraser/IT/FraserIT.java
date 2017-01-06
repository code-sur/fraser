package CodeRaguet.fraser.IT;


import CodeRaguet.fraser.Frase;
import org.junit.Test;

import java.io.IOException;

public class FraserIT extends IntegrationTest {

    private static final Frase FIRST_FRASE = new Frase("El infierno es el olvido");
    private final FraserRunner fraser = new FraserRunner();

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.with(testENV);

        fraser.run();

        fraser.posterShows(FIRST_FRASE);
    }

}
