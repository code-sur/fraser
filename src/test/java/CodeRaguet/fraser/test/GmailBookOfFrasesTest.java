package CodeRaguet.fraser.test;

import CodeRaguet.fraser.BookOfFrases;
import CodeRaguet.fraser.Frase;
import CodeRaguet.fraser.gmail.GmailBookOfFrases;
import CodeRaguet.fraser.gmail.GmailService;
import com.google.api.services.gmail.model.Message;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GmailBookOfFrasesTest {

    private GmailService gmailService = mock(GmailService.class);
    private BookOfFrases bookOfFrases = new GmailBookOfFrases(gmailService);

    @Before
    public void setUpGmailService() throws IOException {
        Message firstFrase = new Message().setSnippet("First frase");
        Message lastFrase = new Message().setSnippet("Last frase");
        List<Message> messagesWithFrase = Arrays.asList(lastFrase, firstFrase);
        when(gmailService.messagesWithFrase()).thenReturn(messagesWithFrase);
    }

    @Test
    public void shoulGetFirstFrase() {
        assertThat(bookOfFrases.next()).isEqualTo(new Frase("First frase"));
    }
}
