package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.BookOfFrases;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.model.NoBookmarkException;
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

        Frase frase = new Frase(fraseText);
        bookmark.setAt(frase);
        return frase;
    }

}
