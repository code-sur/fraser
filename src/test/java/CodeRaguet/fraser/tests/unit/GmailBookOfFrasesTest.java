package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.BookOfFrases;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.gmail.GmailBookOfFrases;
import CodeRaguet.fraser.gmail.GmailService;
import com.google.api.services.gmail.model.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
    public void shouldGetNextFraseAfterBookmark() throws NoBookmarkException {
        Bookmark bookmark = mock(Bookmark.class);
        when(bookmark.isAt()).thenReturn(new Frase("First frase"));
        assertThat(bookOfFrases.nextFraseAfter(bookmark)).isEqualTo(new Frase("Last frase"));
    }

    @Test
    public void shouldGetFirstFraseIfNoBookmark() throws NoBookmarkException {
        Bookmark bookmark = mock(Bookmark.class);
        when(bookmark.isAt()).thenThrow(NoBookmarkException.class);
        assertThat(bookOfFrases.nextFraseAfter(bookmark)).isEqualTo(new Frase("First frase"));
    }

    @Test
    public void shouldSetBookmarkAtNextFrase() throws NoBookmarkException {
        Bookmark bookmark = mock(Bookmark.class);
        when(bookmark.isAt()).thenReturn(new Frase("First frase"));

        bookOfFrases.nextFraseAfter(bookmark);

        verify(bookmark).setAt(new Frase("Last frase"));
    }

}
