package CodeRaguet.fraser.IT;


import CodeRaguet.fraser.Frase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FraserIT extends IntegrationTest {

    private static final Frase FIRST_FRASE = new Frase("El infierno es el olvido");
    private final FraserRunner fraser = new FraserRunner();
    private FraserPublicationsServer frasePublicationsServer;

    @Before
    public void setUpFraser() {
        fraser.with(testENV);
    }

    @Before
    public void setUpPublicationsServer() {
        frasePublicationsServer = new FraserPublicationsServer(testENV);
        frasePublicationsServer.deleteFrases();
    }

    @Test
    public void shouldPublishFirstFrase() throws IOException, InterruptedException {
        fraser.run();

        frasePublicationsServer.hasRecived(FIRST_FRASE);
    }

}
