package CodeRaguet.fraser.model;

import java.util.List;

public interface PostOffice {
    List<Message> messagesFilteredBy();

    List<Message> messagesFilteredBy(MessageFilter filter);
}
