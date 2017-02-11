package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.Message;

import java.util.Base64;

public class GmailMessageTranslator {

    private HTMLParser HTMLParser = new HTMLParser();

    private String decodeURLBase64(String URLBase64encoded) {
        return new String(Base64.getUrlDecoder().decode(URLBase64encoded));
    }

    public Message translate(String HTMLData) {
        return new Message(HTMLParser.parseHTML(decodeURLBase64(HTMLData)));
    }
}
