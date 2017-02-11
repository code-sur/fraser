package CodeRaguet.fraser.gmail;

import org.jsoup.Jsoup;

public class HTMLParser {

    public String parseHTML(String html) {
        return Jsoup.parse(html).text();
    }
}
