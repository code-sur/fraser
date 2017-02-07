package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailMessageTranslator;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.firstFrase;
import static CodeRaguet.fraser.tests.tools.fixtures.GmailMessages.firstGmailMessage;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.firstMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class GmailMessageTranslatorTest {

    private GmailMessageTranslator messageTranslator = new GmailMessageTranslator();

    @Test
    public void translateMessage() {
        Message message = messageTranslator.translate(firstGmailMessage());

        assertThat(message).isEqualTo(firstMessage());
    }
}
