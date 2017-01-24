package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.MessageFilter;

public class GmailFilterTranslator {

    @Override
    public String toString() {
        return "subject:f";
    }

    public String translate(MessageFilter messageFilter) {
        return "subject:" + messageFilter.subject();
    }
}
