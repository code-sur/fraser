package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.BookOfFrases;
import CodeRaguet.fraser.Bookmark;
import CodeRaguet.fraser.Frase;
import CodeRaguet.fraser.NoBookmarkException;
import com.google.api.services.gmail.model.Message;

import java.util.Iterator;
import java.util.List;

public class GmailBookOfFrases implements BookOfFrases {

    private GmailService gmailService;

    public GmailBookOfFrases(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @Override
    public Frase next() {
        List<Message> frases = gmailService.messagesWithFrase();

        return new Frase(frases.get(frases.size() - 1).getSnippet());
    }

    @Override
    public Frase nextFraseAfter(Bookmark bookmark) {
        List<Message> messagesWithFrase = gmailService.messagesWithFrase();

        String fraseText;
        Iterator<Message> messageIterator = messagesWithFrase.iterator();
        Frase bookmarkAt;
        try {
            bookmarkAt = bookmark.isAt();
            do {
                fraseText = messageIterator.next().getSnippet();
            } while (fraseText.equals(bookmarkAt.toString()));
        } catch (NoBookmarkException e) {
            fraseText = messagesWithFrase.get(messagesWithFrase.size() - 1).getSnippet();
        }

        return new Frase(fraseText);
    }

}
