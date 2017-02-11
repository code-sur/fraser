package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailMessageTranslator;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.fixtures.GmailMessages;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.GmailMessages.FIRST_GMAIL_MESSAGE_HTML_DATA;
import static CodeRaguet.fraser.tests.tools.fixtures.GmailMessages.firstGmailMessage;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.firstMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class GmailMessageTranslatorTest {

    private GmailMessageTranslator gmailMessageTranslator = new GmailMessageTranslator();

    @Test
    public void translateMessage() {
        Message message = gmailMessageTranslator.translate(firstGmailMessage());

        assertThat(message).isEqualTo(firstMessage());
    }

    @Test
    public void gmailMessageHTMLData() {
        Message message = gmailMessageTranslator.translate(FIRST_GMAIL_MESSAGE_HTML_DATA);

        assertThat(message).isEqualTo(firstMessage());
    }
}
