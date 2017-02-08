package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.gmail.GmailFilterTranslator;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.tests.tools.ENVTest;
import CodeRaguet.fraser.tests.tools.fixtures.GmailMessages;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GmailServiceIT extends ENVTest {

    @Test
    public void getFirstMessageOfThread() {
        String refreshToken = testENV.getProperty(ENV.GMAIL_REFRESH_TOKEN.name());
        String clientSecret = testENV.getProperty(ENV.GMAIL_CLIENT_SECRET.name());
        GmailService gmailService = new GmailService(refreshToken, clientSecret, new GmailFilterTranslator());
        Thread thread = gmailService.getThreadsFilteredBy(new MessageFilter()).get(0);

        Message gmailMessage = gmailService.getFirstMessageOf(thread);

        assertThat(gmailMessage.getSnippet()).isEqualTo(GmailMessages.firstGmailMessage().getSnippet());
    }
}
