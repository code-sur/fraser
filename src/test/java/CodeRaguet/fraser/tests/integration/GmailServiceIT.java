package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.ENVTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.messagesSubjectF;
import static org.assertj.core.api.Assertions.assertThat;

public class GmailServiceIT extends ENVTest {

    private GmailService gmailService;

    @Before
    public void setUpGmailService() {
        String clientSecret = testENV.getProperty(ENV.GMAIL_CLIENT_SECRET.name());
        String refreshToken = testENV.getProperty(ENV.GMAIL_REFRESH_TOKEN.name());
        gmailService = new GmailService(clientSecret, refreshToken);
    }

    @Test
    public void getMessagesWithSubjectF() {
        List<Message> messages = gmailService.messagesWithSubjectF();

        assertThat(messages).isEqualTo(messagesSubjectF());
    }

}
