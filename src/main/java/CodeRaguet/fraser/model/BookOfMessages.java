package CodeRaguet.fraser.model;

import CodeRaguet.fraser.gmail.GmailService;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;

import java.util.Iterator;

public class BookOfMessages {

    private Bookmark bookmark;
    private GmailService gmailService;

    public BookOfMessages(GmailService gmailService, Bookmark bookmark) {
        this.gmailService = gmailService;
        this.bookmark = bookmark;
    }

    public Message next() throws BookmarkException {
        Iterator<Message> messagesWithFrase = gmailService.messagesWithFrase().iterator();
        Message message = getMessageAfterBookmark(messagesWithFrase);
        bookmark.placeOn(message);
        return message;
    }

    private Message getMessageAfterBookmark(Iterator<Message> messages) {
        Message message = messages.next();
        try {
            if (bookmark.isOn().equals(message)) {
                message = messages.next();
            } else {
                message = getMessageAfterBookmark(messages);
            }
        } catch (NoBookmarkException ignored) {
        }
        return message;
    }


}
