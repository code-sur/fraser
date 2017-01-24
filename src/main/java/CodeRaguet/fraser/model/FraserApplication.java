package CodeRaguet.fraser.model;

import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoMoreMessagesException;

public class FraserApplication {

    private final BookOfMessages bookOfMessages;
    private final FrasesPublisher frasesPublisher;

    public FraserApplication(FrasesPublisher frasesPublisher, BookOfMessages bookOfMessages) {
        this.bookOfMessages = bookOfMessages;
        this.frasesPublisher = frasesPublisher;
    }

    public void run() throws NoMoreMessagesException, BookmarkException {
        frasesPublisher.publish(new Frase(bookOfMessages.next().getText()));
    }
}
