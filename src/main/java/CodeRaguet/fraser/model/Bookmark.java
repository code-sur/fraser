package CodeRaguet.fraser.model;

public interface Bookmark {
    Frase isAt() throws NoBookmarkException;

    void setAt(Message frase);
}
