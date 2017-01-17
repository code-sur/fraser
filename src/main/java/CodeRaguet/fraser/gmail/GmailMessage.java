package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.Message;

public class GmailMessage implements Message {

    private final String text;
    private final String date;

    public GmailMessage(String text, String date) {
        this.text = text;
        this.date = date;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "GmailMessage{" +
                "text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GmailMessage that = (GmailMessage) o;

        return text != null ? text.equals(that.text) : that.text == null;

    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
