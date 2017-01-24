package CodeRaguet.fraser.gmail;

import CodeRaguet.fraser.model.MessageFilter;

import java.util.List;

public class GmailFilterTranslator {

    @Override
    public String toString() {
        return "subject:f";
    }

    public String translate(MessageFilter filter) {
        String subject = filter.subject() != null ? "subject:" + filter.subject() : "";
        String allowedSenders = translateAllowedSenders(filter.allowedSenders());
        return subject + allowedSenders;
    }

    private String translateAllowedSenders(List<String> senders) {
        String translatedSenders = "";
        for (String sender : senders) {
            translatedSenders += "from:" + sender + " ";
        }
        return senders.isEmpty() ? "" : String.format("{%s}", translatedSenders.trim());
    }
}
