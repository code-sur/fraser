package CodeRaguet.fraser.tests.tools;

import CodeRaguet.fraser.model.Message;

public interface MessagesRead {

    void withBookmarkAt(Message message);

    void bookmarkShouldBeAt(Message message);

}
