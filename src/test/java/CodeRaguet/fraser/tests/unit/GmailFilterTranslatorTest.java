package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailFilterTranslator;
import CodeRaguet.fraser.model.MessageFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GmailFilterTranslatorTest {

    private static final String SENDER_1 = "sender1@any.com";
    private static final String SENDER_2 = "sender2@any.com";
    private static final String SENDER_3 = "sender3@any.com";
    private MessageFilter filter = mock(MessageFilter.class);
    private GmailFilterTranslator gmailFilterTranslator;

    @Before
    public void setUpGmailFilterTranslator() {
        gmailFilterTranslator = new GmailFilterTranslator();
    }

    @Test
    public void translateEmptyFilter() {
        assertThat(gmailFilterTranslator.translate(filter)).isEqualTo("");
    }

    @Test
    public void translateSubject() {
        when(filter.subject()).thenReturn("f");

        assertThat(gmailFilterTranslator.translate(filter)).isEqualTo("subject:f");
    }

    @Test
    public void translateOneAllowedSenders() {
        when(filter.allowedSenders()).thenReturn(Collections.singletonList(SENDER_1));

        assertThat(gmailFilterTranslator.translate(filter)).isEqualTo(String.format("{from:%s}", SENDER_1));
    }

    @Test
    public void translateThreeAllowedSenders() {
        List<String> allowedSenders = asList(SENDER_1, SENDER_2, SENDER_3);
        when(filter.allowedSenders()).thenReturn(allowedSenders);

        assertThat(gmailFilterTranslator.translate(filter))
                .isEqualTo(String.format("{from:%s from:%s from:%s}", SENDER_1, SENDER_2, SENDER_3));
    }

}
