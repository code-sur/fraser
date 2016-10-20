package CodeRaguet;


import org.junit.Test;

import java.io.IOException;

public class FraserEndToEndTest {

    private ApplicationRunner application = new ApplicationRunner();

    @Test public void findAFrase() throws IOException, InterruptedException {
        application.findFrase();
        String frase = "Desafiando a la luz, tuve claridad. (La Renga)";
        application.showsFrase(frase);
    }

}
