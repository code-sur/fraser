package CodeRaguet.fraser.model;

public interface Bookmark {

    Message isOn() throws NoBookmarkException;

    void placeOn(Message message) throws BookmarkException;
}
