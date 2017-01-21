package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.*;
import com.google.api.services.gmail.model.Message;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GmailBookOfFrases implements BookOfMessages {

    private Bookmark bookmark;
    private GmailService gmailService;

    public GmailBookOfFrases(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    public GmailBookOfFrases(GmailService gmailService, Bookmark bookmark) {
        this(gmailService);
        this.bookmark = bookmark;
    }

    @Override
    public Frase next() throws BookmarkException {
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();
        Collections.reverse(messagesWithFrase);

        String messageText;
        Iterator<Message> messageIterator = messagesWithFrase.iterator();
        CodeRaguet.fraser.model.Message bookmarkAt;
        try {
            bookmarkAt = bookmark.isOn();
            do {
                messageText = messageIterator.next().getSnippet();
            } while (messageText.equals(bookmarkAt.toString()));
        } catch (NoBookmarkException e) {
            messageText = messagesWithFrase.get(0).getSnippet();
        }

        CodeRaguet.fraser.model.Message message = new CodeRaguet.fraser.model.Message(messageText);
        bookmark.placeOn(message);

        return new Frase(messageText);
    }

}
