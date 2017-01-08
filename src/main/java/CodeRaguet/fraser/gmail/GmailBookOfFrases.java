package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.BookOfFrases;
import CodeRaguet.fraser.Bookmark;
import CodeRaguet.fraser.Frase;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class GmailBookOfFrases implements BookOfFrases {

    private GmailService gmailService;

    public GmailBookOfFrases(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @Override
    public Frase next() {
        List<Message> frases;
        try {
            frases = gmailService.messagesWithFrase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Frase frase = new Frase(frases.get(frases.size() - 1).getSnippet());
        return frase;
    }

    @Override
    public Frase nextAfter(Bookmark bookmark) {
        List<Message> messagesWithFrase;
        try {
            messagesWithFrase = gmailService.messagesWithFrase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fraseText;
        Iterator<Message> messageIterator = messagesWithFrase.iterator();
        do {
            fraseText = messageIterator.next().getSnippet();
        } while (fraseText.equals(bookmark.isAt().toString()));

        Frase frase = new Frase(fraseText);
        return frase;
    }

}
