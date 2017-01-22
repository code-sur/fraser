package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.BookOfFrases;
import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.BookmarkException;
import CodeRaguet.fraser.model.NoBookmarkException;
import org.junit.Before;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Frases.thirdFrase;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.allMessagesAsList;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.secondMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookOfMessagesTest {

    private BookOfFrases bookOfmessages;
    private Bookmark bookmarkMock = mock(Bookmark.class);

    @Before
    public void setUpBookOfMessages() {
        GmailService gmailServiceMock = mock(GmailService.class);
        when(gmailServiceMock.messagesWithFrase()).thenReturn(allMessagesAsList());
        bookOfmessages = new BookOfFrases(gmailServiceMock, bookmarkMock);
    }

    @Test
    public void getNextFraseAfterBookmark() throws BookmarkException, NoBookmarkException {
        when(bookmarkMock.isOn()).thenReturn(secondMessage());

        assertThat(bookOfmessages.next()).isEqualTo(thirdFrase());
    }

}
