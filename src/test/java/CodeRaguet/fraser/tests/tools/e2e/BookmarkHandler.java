package CodeRaguet.fraser.tests.tools.e2e;

import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.Message;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BookmarkHandler {

    private final Bookmark bookmark;

    public BookmarkHandler(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public void placeBookmarkOn(Message message) {
        try {
            bookmark.placeOn(message);
        } catch (BookmarkException e) {
            throw new RuntimeException(e);
        }
    }

    public void bookmarkShouldBeOn(Message message) {
        try {
            assertThat(bookmark.isOn()).isEqualTo(message);
        } catch (NoBookmarkException e) {
            fail("There is no bookmark.");
        }
    }

}
