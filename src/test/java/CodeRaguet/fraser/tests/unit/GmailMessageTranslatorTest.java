package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailMessageTranslator;
import CodeRaguet.fraser.model.Message;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.GmailMessages.gmailMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class GmailMessageTranslatorTest {

    private GmailMessageTranslator messageTranslator = new GmailMessageTranslator();

    @Test
    public void translateSnippet() {
        Message message = messageTranslator.translate(gmailMessage());

        assertThat(message.getText()).isEqualTo(gmailMessage().getSnippet());
    }
}
