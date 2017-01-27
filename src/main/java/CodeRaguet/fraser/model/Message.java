package CodeRaguet.fraser.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {

    private String text;

    public Message(String text) {
        this.text = text.replace("&quot;", "\"");
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return text != null ? text.equals(message.text) : message.text == null;

    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return text;
    }

    public Frase extractFrase() {
        String fraseText;
        if (getText().contains("\"")) {
            String regex = ".*\"(.+)\".*";
            Matcher matcher = Pattern.compile(regex).matcher(getText());
            matcher.find();
            fraseText = matcher.group(1);
        } else {
            fraseText = getText();
        }

        return new Frase(fraseText);
    }
}
