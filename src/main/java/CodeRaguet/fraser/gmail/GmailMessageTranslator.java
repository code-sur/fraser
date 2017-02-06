package CodeRaguet.fraser.gmail;

import com.google.api.services.gmail.model.Message;

public class GmailMessageTranslator {

    public CodeRaguet.fraser.model.Message translate(Message gmailMessage) {
        return new CodeRaguet.fraser.model.Message(gmailMessage.getSnippet());
    }
}
