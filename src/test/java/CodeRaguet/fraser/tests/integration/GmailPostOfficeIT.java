package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.gmail.GmailPostOffice;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.model.PostOffice;
import CodeRaguet.fraser.tests.tools.ENVTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.messagesSubjectF;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class GmailPostOfficeIT extends ENVTest {

    private PostOffice postOffice;

    @Before
    public void setUpGmailService() {
        String clientSecret = testENV.getProperty(ENV.GMAIL_CLIENT_SECRET.name());
        String refreshToken = testENV.getProperty(ENV.GMAIL_REFRESH_TOKEN.name());
        postOffice = new GmailPostOffice(clientSecret, refreshToken);
    }

    @Test
    public void getMessagesWithSubjectF() {
        MessageFilter messageFilter = mock(MessageFilter.class);
        List<Message> messages = postOffice.messagesFilteredBy(messageFilter);

        assertThat(messages).isEqualTo(messagesSubjectF());
    }

}
