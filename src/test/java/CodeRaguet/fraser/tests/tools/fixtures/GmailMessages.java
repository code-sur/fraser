package CodeRaguet.fraser.tests.tools.fixtures;

import com.google.api.services.gmail.model.Message;

public class GmailMessages {

    public static Message gmailMessage() {
        Message gmailMessage = new Message();
        gmailMessage.setSnippet("gmail message snippet");
        return gmailMessage;
    }
}
