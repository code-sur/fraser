package CodeRaguet.fraser.model;

import java.util.ArrayList;
import java.util.List;

public class MessageFilter {
    public String subject() {
        return "f";
    }

    public List<String> allowedSenders() {
        return new ArrayList<>();
    }
}
