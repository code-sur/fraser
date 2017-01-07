package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.BookOfFrases;
import CodeRaguet.fraser.Frase;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
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

}
