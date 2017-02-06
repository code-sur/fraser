package CodeRaguet.fraser.gmail;


import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.model.PostOffice;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GmailPostOffice implements PostOffice {

    private final GmailService service;
    private GmailMessageTranslator messageTranslator;

    public GmailPostOffice(GmailMessageTranslator messageTranslator, GmailService service) {
        this.service = service;
        this.messageTranslator = messageTranslator;
    }

    @Override
    public List<CodeRaguet.fraser.model.Message> messagesFilteredBy(MessageFilter filter) {
        List<Thread> threads = service.getThreadsFilteredBy(filter);
        List<CodeRaguet.fraser.model.Message> fraserMessages = new ArrayList<>();
        threads.forEach(thread -> fraserMessages.add(translateMessageToFraserMessage(service.getFirstMessageOf(thread))));
        Collections.reverse(fraserMessages);
        return fraserMessages;
    }

    private CodeRaguet.fraser.model.Message translateMessageToFraserMessage(Message gmailMessage) {
        return messageTranslator.translate(gmailMessage);
    }

}
