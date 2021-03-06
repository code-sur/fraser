package CodeRaguet.fraser.model;

import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;
import CodeRaguet.fraser.model.exceptions.NoMoreMessagesException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BookOfMessages {

    private Bookmark bookmark;
    private PostOffice postOffice;
    private Message messageAtBookmark;
    private MessageFilter messageFilter;

    public BookOfMessages(PostOffice postOffice, Bookmark bookmark, MessageFilter messageFilter) {
        this.postOffice = postOffice;
        this.bookmark = bookmark;
        this.messageFilter = messageFilter;
    }

    public Message next() throws BookmarkException, NoMoreMessagesException {
        Iterator<Message> messagesWithFrase = postOffice.messagesFilteredBy(messageFilter).iterator();
        Message message = getMessageAfterBookmark(messagesWithFrase);
        bookmark.placeOn(message);
        return message;
    }

    private Message getMessageAfterBookmark(Iterator<Message> messages) throws NoMoreMessagesException {
        Message message = messages.next();
        try {
            if (bookmarkIsPlacedOn(message)) {
                message = messages.next();
            } else {
                message = getMessageAfterBookmark(messages);
            }
        } catch (NoBookmarkException ignored) {
        } catch (NoSuchElementException e) {
            throw new NoMoreMessagesException(String.format("No more messages. Last message is %s", message), e);
        }
        return message;
    }

    private boolean bookmarkIsPlacedOn(Message message) throws NoBookmarkException {
        if (messageAtBookmark == null) {
            messageAtBookmark = bookmark.isOn();
        }
        return messageAtBookmark.equals(message);
    }


}
