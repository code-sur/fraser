package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.BookOfMessages;
import CodeRaguet.fraser.FraserApplication;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.FrasesPublisher;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoMoreMessagesException;
import CodeRaguet.fraser.tests.tools.fixtures.Frases;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FraserApplicationTest {

    private FraserApplication fraserApplication;
    private BookOfMessages bookOfMessages;
    private FrasesPublisher frasesPublisher;

    @Before
    public void setUpFraser() throws NoMoreMessagesException, BookmarkException {
        bookOfMessages = mock(BookOfMessages.class);
        when(bookOfMessages.next()).thenReturn(Messages.someMessage());

        frasesPublisher = mock(FrasesPublisher.class);

        fraserApplication = new FraserApplication(frasesPublisher, bookOfMessages);
    }

    @Test
    public void getNextMessage() throws NoMoreMessagesException, BookmarkException {
        fraserApplication.run();

        verify(bookOfMessages).next();
    }

    @Test
    public void publishFraseInMessage() throws BookmarkException, NoMoreMessagesException {
        fraserApplication.run();

        verify(frasesPublisher).publish(Frases.someFrase());
    }

    @Test
    public void extractFraseInQuotationMarks() throws BookmarkException, NoMoreMessagesException {
        String textInQuotationMarks = "text in quotation marks";
        Message messageWithFraseToExtract = new Message(String.format("\"%s\" text to discard", textInQuotationMarks));
        when(bookOfMessages.next()).thenReturn(messageWithFraseToExtract);

        fraserApplication.run();

        verify(frasesPublisher).publish(new Frase(textInQuotationMarks));
    }

    @Test
    public void extractFirstlineAsFrase() throws NoMoreMessagesException, BookmarkException {
        String fraseText = "Text of frase";
        Message messageWithFirstLineAsFrase = new Message(String.format("%s\n\nAnd text to discard", fraseText));
        when(bookOfMessages.next()).thenReturn(messageWithFirstLineAsFrase);

        fraserApplication.run();

        verify(frasesPublisher).publish(new Frase(fraseText));
    }
}
