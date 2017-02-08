package CodeRaguet.fraser.gmail;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import org.jsoup.Jsoup;

import java.util.Base64;

public class GmailMessageTranslator {

    public CodeRaguet.fraser.model.Message translate(Message message) {
        return new CodeRaguet.fraser.model.Message(parseHTML(decodeURLBase64(getHTMLMessage(message))));
    }

    private String parseHTML(String html) {
        return Jsoup.parse(html).text();
    }

    private String getHTMLMessage(Message message) {
        return getHTMLMessagePart(message).getBody().getData();
    }

    private String decodeURLBase64(String URLBase64encoded) {
        return new String(Base64.getUrlDecoder().decode(URLBase64encoded));
    }

    private MessagePart getHTMLMessagePart(Message message) {
        return message.getPayload().getParts().get(1);
    }


}
