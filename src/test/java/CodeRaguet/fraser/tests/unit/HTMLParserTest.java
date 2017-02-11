package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.gmail.HTMLParser;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HTMLParserTest {

    private CodeRaguet.fraser.gmail.HTMLParser HTMLParser = new HTMLParser();

    @Test
    public void parseMultilineMessage() {
        String html = "<div dir=\"ltr\">First line as frase<br><div><br></div><div>And text to discard<br></div></div>";
        assertThat(HTMLParser.parse(html)).isEqualTo("First line as frase\n\nAnd text to discard");
    }

    @Test
    public void parseSimpleMessage() {
        String html = "<div dir=\"ltr\"><span style=\"color:rgb(33,33,33);font-family:verdana,sans-serif;font-size:13.3333px\">El infierno es el olvido</span><br></div>\n";
        assertThat(HTMLParser.parse(html)).isEqualTo("El infierno es el olvido");
    }
}
