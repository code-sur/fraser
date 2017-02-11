package CodeRaguet.fraser.gmail;

import org.jsoup.Jsoup;

import java.util.Base64;

public class GmailMessageTranslator {

    private String parseHTML(String html) {
        return Jsoup.parse(html).text();
    }

    private String decodeURLBase64(String URLBase64encoded) {
        return new String(Base64.getUrlDecoder().decode(URLBase64encoded));
    }

    public CodeRaguet.fraser.model.Message translate(String HTMLData) {
        return new CodeRaguet.fraser.model.Message(parseHTML(decodeURLBase64(HTMLData)));
    }
}
