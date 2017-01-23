package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.BookOfMessages;
import CodeRaguet.fraser.model.Fraser;
import CodeRaguet.fraser.model.FrasesPublisher;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoMoreMessagesException;
import CodeRaguet.fraser.tests.tools.fixtures.Frases;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FraserTest {

    private Fraser fraser;
    private BookOfMessages bookOfMessages;
    private FrasesPublisher frasesPublisher;

    @Before
    public void setUpFraser() throws NoMoreMessagesException, BookmarkException {
        bookOfMessages = mock(BookOfMessages.class);
        when(bookOfMessages.next()).thenReturn(Messages.someMessage());

        frasesPublisher = mock(FrasesPublisher.class);

        fraser = new Fraser(frasesPublisher, bookOfMessages);
    }

    @Test
    public void getNextMessage() throws NoMoreMessagesException, BookmarkException {
        fraser.run();

        verify(bookOfMessages).next();
    }

    @Test
    public void publishFraseInMessage() throws BookmarkException, NoMoreMessagesException {
        fraser.run();

        verify(frasesPublisher).publish(Frases.someFrase());
    }
}
