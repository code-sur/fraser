package CodeRaguet.fraser.IT;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.GmailService;
import com.google.api.services.gmail.model.Thread;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GmailServiceIT extends IntegrationTest {

    private String refreshToken;
    private String clientSecret;
    private GmailService gmailService;

    @Before
    public void setUpGmailService() throws GeneralSecurityException, IOException {
        refreshToken = testENV.getProperty(ENV.REFRESH_TOKEN.name());
        clientSecret = testENV.getProperty(ENV.CLIENT_SECRET.name());
        gmailService = new GmailService(clientSecret, refreshToken);
    }

    @Test
    public void shouldGetLastLabel() throws GeneralSecurityException, IOException {
        String lastLabel = gmailService.getLastLabel();
        assertThat(lastLabel).isEqualTo(LAST_LABEL);
    }

    @Test
    public void shouldGetThreadsWithFrase() throws GeneralSecurityException, IOException {
        List<Thread> threadsWithFrase = gmailService.threadsWithFrase();
        assertThat(threadsWithFrase.size()).isEqualTo(1);
    }
}
