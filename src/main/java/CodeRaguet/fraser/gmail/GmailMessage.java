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
}
