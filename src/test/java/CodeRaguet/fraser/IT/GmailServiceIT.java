package CodeRaguet.fraser.IT;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.GmailService;
import com.google.api.services.gmail.model.Message;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GmailServiceIT extends IntegrationTest {

    private GmailService gmailService;

    @Before
    public void setUpGmailService() throws GeneralSecurityException, IOException {
        String refreshToken = testENV.getProperty(ENV.REFRESH_TOKEN.name());
        String clientSecret = testENV.getProperty(ENV.CLIENT_SECRET.name());
        gmailService = new GmailService(clientSecret, refreshToken);
    }

    @Test
    public void shouldGetMessagesWithFrase() throws GeneralSecurityException, IOException {
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();
        assertThat(messagesWithFrase)
                .hasSize(2)
                .extracting(Message::getSnippet)
                .contains("El infierno es el olvido", "No te llevas nada");
    }

    @Test
    public void shouldGetAllMessagesWithFrase() throws IOException {
        gmailService.setThreadsMaxResults(1L);
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();
        assertThat(messagesWithFrase)
                .hasSize(2)
                .extracting(Message::getSnippet)
                .contains("El infierno es el olvido", "No te llevas nada");
    }
}
