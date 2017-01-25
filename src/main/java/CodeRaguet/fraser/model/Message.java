package CodeRaguet.fraser.model;

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
}
