package CodeRaguet.fraser.tests.integration;

import CodeRaguet.fraser.model.exceptions.BookmarkException;
import CodeRaguet.fraser.model.exceptions.NoBookmarkException;
import CodeRaguet.fraser.tests.tools.db.DatabaseTest;
import org.junit.Test;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.firstMessage;
import static CodeRaguet.fraser.tests.tools.fixtures.Messages.longMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseBookmarkIT extends DatabaseTest {

    @Test
    public void shouldNotFailWithoutBookmark() throws BookmarkException {
        bookmark.placeOn(firstMessage());

        assertThat(bookmark.isOn()).isEqualTo(firstMessage());
    }

    @Test
    public void placeOnLongMessage() throws BookmarkException {
        bookmark.placeOn(longMessage());

        assertThat(bookmark.isOn()).isEqualTo(longMessage());
    }

    @Test(expected = NoBookmarkException.class)
    public void placedOnNoMessage() throws NoBookmarkException {
        bookmark.isOn();
    }
}
