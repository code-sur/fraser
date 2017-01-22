package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.BookOfMessages;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;
import org.junit.Before;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.allMessagesAsList;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.secondMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookOfMessagesTest {

    private BookOfMessages bookOfmessages;
    private Bookmark bookmarkMock = mock(Bookmark.class);

    @Before
    public void setUpBookOfMessages() {
        GmailService gmailServiceMock = mock(GmailService.class);
        when(gmailServiceMock.messagesWithFrase()).thenReturn(allMessagesAsList());
        bookOfmessages = new BookOfMessages(gmailServiceMock, bookmarkMock);
    }

    @Test
    public void getNextFraseAfterBookmark() throws BookmarkException, NoBookmarkException {
        when(bookmarkMock.isOn()).thenReturn(secondMessage());

        assertThat(bookOfmessages.next()).isEqualTo(Messages.thirdMessage());
    }

}
