package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.*;

import java.util.Iterator;
import java.util.List;

public class GmailBookOfFrases implements BookOfMessages {

    private Bookmark bookmark;
    private GmailService gmailService;

    public GmailBookOfFrases(GmailService gmailService, Bookmark bookmark) {
        this.gmailService = gmailService;
        this.bookmark = bookmark;
    }

    @Override
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
