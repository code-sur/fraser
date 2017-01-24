package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.MessageFilter;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Senders.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageFilterTest {

    private MessageFilter messageFilter = new MessageFilter();

    @Test
    public void whenNoAllowedSenders() {
        assertThat(messageFilter.allowedSenders()).isEmpty();
    }

    @Test
    public void configureOneAllowedSender() {
        messageFilter.configureAllowedSenders(SENDER_1);

        assertThat(messageFilter.allowedSenders()).isEqualTo(singletonList(SENDER_1));
    }

    @Test
    public void configureThreeAllowedSenders() {
        messageFilter.configureAllowedSenders(String.format("%s,%s,%s", SENDER_1, SENDER_2, SENDER_3));

        assertThat(messageFilter.allowedSenders()).isEqualTo(asList(SENDER_1, SENDER_2, SENDER_3));
    }
}
