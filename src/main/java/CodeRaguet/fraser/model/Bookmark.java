package CodeRaguet.fraser.model;

public interface Bookmark {
    Frase isOn() throws NoBookmarkException;

    void placeOn(Message frase) throws BookmarkException;
}
