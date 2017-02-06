package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.ENV;
import CodeRaguet.fraser.gmail.GmailFilterTranslator;
import CodeRaguet.fraser.gmail.GmailMessageTranslator;
import CodeRaguet.fraser.gmail.GmailPostOffice;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.model.PostOffice;
import CodeRaguet.fraser.tests.tools.ENVTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GmailPostOfficeIT extends ENVTest {

    private PostOffice postOffice;
    private MessageFilter messageFilter = new MessageFilter();
    private GmailFilterTranslator filterTranslator;

    @Before
    public void setUpGmailService() {
        String clientSecret = testENV.getProperty(ENV.GMAIL_CLIENT_SECRET.name());
        String refreshToken = testENV.getProperty(ENV.GMAIL_REFRESH_TOKEN.name());
        filterTranslator = mock(GmailFilterTranslator.class);
        GmailMessageTranslator gmailMessageTranslator = new GmailMessageTranslator();
        GmailService gmailService = new GmailService(refreshToken, clientSecret, filterTranslator);
        postOffice = new GmailPostOffice(filterTranslator, gmailMessageTranslator, gmailService);
    }

    @Test
    public void getMessagesFilteredBySubject() {
        when(filterTranslator.translate(messageFilter)).thenReturn("subject:f");

        List<Message> messages = postOffice.messagesFilteredBy(messageFilter);

        assertThat(messages).contains(firstMessage(), thirdMessage(), fifthMessage());
    }

    @Test
    public void getMessagesFilteredBySubjectAndSenders() {
        String gmail_query = "subject:f {from:ignacio.code@gmail.com from:fraser.quote@gmail.com}";
        when(filterTranslator.translate(messageFilter)).thenReturn(gmail_query);

        List<Message> messages = postOffice.messagesFilteredBy(messageFilter);

        assertThat(messages).contains(firstMessage(), thirdMessage(), fifthMessage())
                .doesNotContain(messageFromStranger());
    }

}
