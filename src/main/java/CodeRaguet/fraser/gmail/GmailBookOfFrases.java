package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.BookOfFrases;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
import com.google.api.services.gmail.model.Message;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GmailBookOfFrases implements BookOfFrases {

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
    public Frase next() {
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();
        Collections.reverse(messagesWithFrase);

        String messageText;
        Iterator<Message> messageIterator = messagesWithFrase.iterator();
        Frase bookmarkAt;
        try {
            bookmarkAt = bookmark.isAt();
            do {
                messageText = messageIterator.next().getSnippet();
            } while (messageText.equals(bookmarkAt.toString()));
        } catch (NoBookmarkException e) {
            messageText = messagesWithFrase.get(0).getSnippet();
        }

        Frase frase = new Frase(messageText);
        bookmark.setAt(frase);
        return frase;
    }

    @Override
    public Frase nextFraseAfter(Bookmark bookmark) {
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();
        Collections.reverse(messagesWithFrase);

        String fraseText;
        Iterator<Message> messageIterator = messagesWithFrase.iterator();
        Frase bookmarkAt;
        try {
            bookmarkAt = bookmark.isAt();
            do {
                fraseText = messageIterator.next().getSnippet();
            } while (fraseText.equals(bookmarkAt.toString()));
        } catch (NoBookmarkException e) {
            fraseText = messagesWithFrase.get(0).getSnippet();
        }

        Frase frase = new Frase(fraseText);
        bookmark.setAt(frase);
        return frase;
    }

}
