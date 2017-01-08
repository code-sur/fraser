package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.gmail.GmailService;
import com.google.api.services.gmail.model.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GmailServiceIT extends IntegrationTest {

    private GmailService gmailService;

    @Before
    public void setUpGmailService() {
        String refreshToken = testENV.getProperty(ENV.GMAIL_REFRESH_TOKEN.name());
        String clientSecret = testENV.getProperty(ENV.GMAIL_CLIENT_SECRET.name());
        gmailService = new GmailService(clientSecret, refreshToken);
    }

    @Test
    public void shouldGetMessagesWithFrase() {
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();
        assertThat(messagesWithFrase)
                .hasSize(2)
                .extracting(Message::getSnippet)
                .contains("El infierno es el olvido", "No te llevas nada");
    }

    @Test
    public void shouldGetAllMessagesWithFrase() {
        gmailService.setThreadsMaxResults(1L);
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();
        assertThat(messagesWithFrase)
                .hasSize(2)
                .extracting(Message::getSnippet)
                .contains("El infierno es el olvido", "No te llevas nada");
    }
}
