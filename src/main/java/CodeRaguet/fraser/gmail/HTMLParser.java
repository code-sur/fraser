package CodeRaguet.fraser.gmail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HTMLParser {

    public String parse(String html) {
        Document document = Jsoup.parse(html);
        Elements divs = document.select("div");
        List<String> result = new ArrayList<>();
        divs.forEach(div -> result.add(getText(div)));
        return String.join("\n", result);
    }

    private String getText(Element div) {
        String text;
        try {
            text = div.textNodes().get(0).text();

        } catch (IndexOutOfBoundsException e) {
            text = div.text();
        }
        return text;
    }

}
