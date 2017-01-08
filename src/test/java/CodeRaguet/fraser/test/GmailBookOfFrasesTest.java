package CodeRaguet.fraser.test;

import CodeRaguet.fraser.BookOfFrases;
import CodeRaguet.fraser.Bookmark;
import CodeRaguet.fraser.Frase;
import CodeRaguet.fraser.gmail.GmailBookOfFrases;
import CodeRaguet.fraser.gmail.GmailService;
import com.google.api.services.gmail.model.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GmailBookOfFrasesTest {

    private GmailService gmailService = mock(GmailService.class);
    private BookOfFrases bookOfFrases = new GmailBookOfFrases(gmailService);

    @Before
    public void setUpGmailService() {
        Message firstFrase = new Message().setSnippet("First frase");
        Message lastFrase = new Message().setSnippet("Last frase");
        List<Message> messagesWithFrase = Arrays.asList(lastFrase, firstFrase);
        when(gmailService.messagesWithFrase()).thenReturn(messagesWithFrase);
    }

    @Test
    public void shoulGetFirstFrase() {
        assertThat(bookOfFrases.next()).isEqualTo(new Frase("First frase"));
    }

    @Test
    public void shouldGetNextFraseAfterBookmark() {
        Bookmark bookmark = mock(Bookmark.class);
        when(bookmark.isAt()).thenReturn(new Frase("First frase"));
        assertThat(bookOfFrases.nextFraseAfter(bookmark)).isEqualTo(new Frase("Last frase"));
    }

}
