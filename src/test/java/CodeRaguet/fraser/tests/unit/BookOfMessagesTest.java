package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.BookOfMessages;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.PostOffice;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;
import CodeRaguet.fraser.model.exceptions.NoMoreMessagesException;
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
        PostOffice postOffice = mock(GmailService.class);
        when(postOffice.messagesWithSubjectF()).thenReturn(allValidMessages());
        bookOfmessages = new BookOfMessages(postOffice, bookmark);
    }

    @Test
    public void getFirstMessageWhenNoBookmark() throws BookmarkException, NoMoreMessagesException {
        when(bookmark.isOn()).thenThrow(NoBookmarkException.class);

        assertThat(bookOfmessages.next()).isEqualTo(firstMessage());
    }

    @Test
    public void getNextFraseAfterBookmark() throws BookmarkException, NoMoreMessagesException {
        when(bookmark.isOn()).thenReturn(secondMessage());

        assertThat(bookOfmessages.next()).isEqualTo(thirdMessage());
    }

    @Test(expected = NoMoreMessagesException.class)
    public void failIfNoMoreMessages() throws BookmarkException, NoMoreMessagesException {
        when(bookmark.isOn()).thenReturn(lastMessage());

        bookOfmessages.next();
    }

}
