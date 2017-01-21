package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.db.DatabaseBookmark;
import CodeRaguet.fraser.model.Bookmark;
import CodeRaguet.fraser.model.BookmarkException;
import CodeRaguet.fraser.model.NoBookmarkException;
import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import org.junit.Ignore;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.longMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseBookmarkIT extends DatabaseTest {

    @Test
    @Ignore
    public void placeOnLongMessage() throws BookmarkException, NoBookmarkException {
        Bookmark bookmark = new DatabaseBookmark(connection);

        bookmark.placeOn(longMessage());

        assertThat(bookmark.isOn()).isEqualTo(longMessage());
    }
}
