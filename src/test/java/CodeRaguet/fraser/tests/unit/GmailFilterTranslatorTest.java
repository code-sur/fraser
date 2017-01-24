package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailFilterTranslator;
import CodeRaguet.fraser.model.MessageFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static CodeRaguet.fraser.tests.tools.fixtures.Senders.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GmailFilterTranslatorTest {

    private static final String SOME_SUBJECT = "some subject";
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
        when(filter.allowedSenders()).thenReturn(singletonList(SENDER_1));

        assertThat(gmailFilterTranslator.translate(filter)).isEqualTo(String.format("{from:%s}", SENDER_1));
    }

    @Test
    public void translateThreeAllowedSenders() {
        List<String> allowedSenders = asList(SENDER_1, SENDER_2, SENDER_3);
        when(filter.allowedSenders()).thenReturn(allowedSenders);

        assertThat(gmailFilterTranslator.translate(filter))
                .isEqualTo(String.format("{from:%s from:%s from:%s}", SENDER_1, SENDER_2, SENDER_3));
    }

    @Test
    public void CompoundTranslatedSendersAndSubject() {
        when(filter.subject()).thenReturn(SOME_SUBJECT);
        when(filter.allowedSenders()).thenReturn(singletonList(SENDER_1));

        assertThat(gmailFilterTranslator.translate(filter))
                .isEqualTo(String.format("subject:%s{from:%s}", SOME_SUBJECT, SENDER_1));
    }

}
