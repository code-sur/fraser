package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.MessageFilter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageFilterTest {

    @Test
    public void whenNoAllowedSenders() {
        MessageFilter messageFilter = new MessageFilter();
        assertThat(messageFilter.allowedSenders()).isEmpty();
    }
}
