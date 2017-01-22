package CodeRaguet.fraser.model;

import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;

public interface Bookmark {

    Message isOn() throws NoBookmarkException;

    void placeOn(Message message) throws BookmarkException;
}
