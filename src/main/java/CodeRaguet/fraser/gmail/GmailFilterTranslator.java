package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.MessageFilter;

public class GmailFilterTranslator {

    @Override
    public String toString() {
        return "subject:f";
    }

    public String translate(MessageFilter filter) {
        String subject = filter.subject() != null ? "subject:" + filter.subject() : "";
        String allowedSenders = "";
        for(String allowedSender : filter.allowedSenders()) {
            allowedSenders = "from:" + allowedSender + " ";
        }
        return subject + allowedSenders;
    }
}
