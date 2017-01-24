package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailFilterTranslator;
import CodeRaguet.fraser.model.MessageFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GmailFilterTranslatorTest {

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
        when(filter.allowedSenders()).thenReturn(Collections.singletonList("allowed.sender@any.com"));

        assertThat(gmailFilterTranslator.translate(filter)).contains("from:allowed.sender@any.com");
    }

}
