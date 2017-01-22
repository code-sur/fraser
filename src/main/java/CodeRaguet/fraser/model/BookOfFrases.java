package CodeRaguet.fraser.model;

import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;

import java.util.Iterator;
import java.util.List;

public class BookOfFrases {

    private Bookmark bookmark;
    private GmailService gmailService;

    public BookOfFrases(GmailService gmailService, Bookmark bookmark) {
        this.gmailService = gmailService;
        this.bookmark = bookmark;
    }

    public Frase next() throws BookmarkException {
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();

        Message message;
        Iterator<Message> messageIterator = messagesWithFrase.iterator();
        Message bookmarkAt;
        try {
            bookmarkAt = bookmark.isOn();
            do {
                message = messageIterator.next();
            } while (!message.equals(bookmarkAt));
            message = messageIterator.next();
        } catch (NoBookmarkException e) {
            message = messagesWithFrase.get(0);
        }
        bookmark.placeOn(message);

        return new Frase(message.getText());
    }

}
