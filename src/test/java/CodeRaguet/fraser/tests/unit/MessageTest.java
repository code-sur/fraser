package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.Message;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageTest {

    @Test
    public void replaceHTMLquotationMarks() {
        Message message = new Message("message with &quot;quotation marks&quot;");

        assertThat(message).isEqualTo(new Message("message with \"quotation marks\""));
    }
}
