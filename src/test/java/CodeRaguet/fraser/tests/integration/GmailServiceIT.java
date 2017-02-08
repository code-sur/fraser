package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.gmail.GmailFilterTranslator;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.tests.tools.ENVTest;
import CodeRaguet.fraser.tests.tools.fixtures.GmailMessages;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GmailServiceIT extends ENVTest {

    private GmailService gmailService;

    @Before
    public void setUpGmailService() {
        String refreshToken = testENV.getProperty(ENV.GMAIL_REFRESH_TOKEN.name());
        String clientSecret = testENV.getProperty(ENV.GMAIL_CLIENT_SECRET.name());
        gmailService = new GmailService(refreshToken, clientSecret, new GmailFilterTranslator());
    }

    @Test
    public void getFirstMessageOfThreadWithSnippet() {
        Thread thread = gmailService.getThreadsFilteredBy(new MessageFilter()).get(0);

        Message gmailMessage = gmailService.getFirstMessageOf(thread);

        assertThat(gmailMessage.getSnippet()).isEqualTo(GmailMessages.firstGmailMessage().getSnippet());
    }

    @Test
    public void getFirstMessageOfThreadWithPayload() {
        Thread thread = gmailService.getThreadsFilteredBy(new MessageFilter()).get(0);

        Message gmailMessage = gmailService.getFirstMessageOf(thread);

        assertThat(gmailMessage.getPayload()).isEqualTo(GmailMessages.firstGmailMessage().getPayload());
    }
}
