package CodeRaguet.fraser.gmail;

import java.util.Base64;

public class GmailMessageTranslator {

    private HTMLParser HTMLParser = new HTMLParser();

    private String decodeURLBase64(String URLBase64encoded) {
        return new String(Base64.getUrlDecoder().decode(URLBase64encoded));
    }

    public CodeRaguet.fraser.model.Message translate(String HTMLData) {
        return new CodeRaguet.fraser.model.Message(HTMLParser.parseHTML(decodeURLBase64(HTMLData)));
    }
}
