package CodeRaguet.fraser.IT;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.GmailService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.assertj.core.api.Assertions.assertThat;

public class GmailServiceIT extends IntegrationTest {

    private String refreshToken;
    private String clientSecret;

    @Before
    public void loadGmailSecrets() {
        refreshToken = testENV.getProperty(ENV.REFRESH_TOKEN.name());
        clientSecret = testENV.getProperty(ENV.CLIENT_SECRET.name());
    }

    @Test
    public void shouldGetLastLabel() throws GeneralSecurityException, IOException {
        GmailService gmailService = new GmailService(clientSecret, refreshToken);

        assertThat(gmailService.getLastLabel()).isEqualTo(LAST_LABEL);
    }

    @Test
    public void shouldGetThreadsWithFrase() throws GeneralSecurityException, IOException {
        GmailService gmailService = new GmailService(clientSecret, refreshToken);

        assertThat(gmailService.threadsWithFrase().size()).isEqualTo(1);
    }
}
