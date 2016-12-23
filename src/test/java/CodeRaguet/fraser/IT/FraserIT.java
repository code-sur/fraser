package CodeRaguet.fraser.IT;


import org.junit.Test;

import java.io.IOException;

public class FraserIT {

    private FraserRunner fraser = new FraserRunner();

    @Test
    public void fraserRun() throws IOException, InterruptedException {
        fraser.setRefreshToken("1/ADyEQcVsjNnqQOUDYyBjBsI2PGk62XlTyNxsxdYaGwpzNMZI8oV8yjuxc6b5nTgQ");
        fraser.setClientSecret("{\"installed\":{\"client_id\":\"379299895047-2lj1s61cu20qf4dnr5os33nsvft3uumb.apps.googleusercontent.com\",\"project_id\":\"weighty-psyche-153000\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://accounts.google.com/o/oauth2/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"gpS2Fvi62D0zvUeOPufeQUB7\",\"redirect_uris\":[\"urn:ietf:wg:oauth:2.0:oob\",\"http://localhost\"]}}");

        fraser.run();

        String lastLabel = "IMPORTANT";
        fraser.shows(lastLabel);
    }
}
