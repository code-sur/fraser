package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.BookOfMessages;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;
import CodeRaguet.fraser.model.exceptions.NoMoreMessagesException;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;
import org.junit.Before;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookOfMessagesTest {

    private BookOfMessages bookOfmessages;
    private Bookmark bookmark = mock(Bookmark.class);

    @Before
    public void setUpBookOfMessages() {
        GmailService gmailServiceMock = mock(GmailService.class);
        when(gmailServiceMock.messagesWithFrase()).thenReturn(allMessagesAsList());
        bookOfmessages = new BookOfMessages(gmailServiceMock, bookmark);
    }

    @Test
    public void getFirstMessageWhenNoBookmark() throws NoBookmarkException, BookmarkException, NoMoreMessagesException {
        when(bookmark.isOn()).thenThrow(NoBookmarkException.class);

        assertThat(bookOfmessages.next()).isEqualTo(firstMessage());
    }

    @Test
    public void getNextFraseAfterBookmark() throws BookmarkException, NoBookmarkException, NoMoreMessagesException {
        when(bookmark.isOn()).thenReturn(secondMessage());

        assertThat(bookOfmessages.next()).isEqualTo(thirdMessage());
    }

    @Test(expected = NoMoreMessagesException.class)
    public void failIfNoMoreMessages() throws NoBookmarkException, BookmarkException, NoMoreMessagesException {
        when(bookmark.isOn()).thenReturn(Messages.lastMessage());

        bookOfmessages.next();
    }

}
