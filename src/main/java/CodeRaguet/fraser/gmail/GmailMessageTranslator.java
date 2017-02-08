package CodeRaguet.fraser.gmail;

import com.google.api.services.gmail.model.Message;
import org.jsoup.Jsoup;

import java.util.Base64;

public class GmailMessageTranslator {

    public CodeRaguet.fraser.model.Message translate(Message gmailMessage) {
        String htmlMessage = new String(Base64.getUrlDecoder().decode(gmailMessage.getPayload().getParts().get(1).getBody().getData()));
        return new CodeRaguet.fraser.model.Message(Jsoup.parse(htmlMessage).text());
    }
}
