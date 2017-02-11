package CodeRaguet.fraser.gmail;


import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.model.PostOffice;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.Thread;

import java.util.ArrayList;
import java.util.List;

public class GmailPostOffice implements PostOffice {

    private final GmailService service;
    private GmailMessageTranslator gmailMessageTranslator;

    public GmailPostOffice(GmailMessageTranslator gmailMessageTranslator, GmailService service) {
        this.service = service;
        this.gmailMessageTranslator = gmailMessageTranslator;
    }

    @Override
    public List<CodeRaguet.fraser.model.Message> messagesFilteredBy(MessageFilter filter) {
        List<Thread> threads = service.getThreadsFilteredBy(filter);
        List<CodeRaguet.fraser.model.Message> fraserMessages = new ArrayList<>();
        threads.forEach(thread -> fraserMessages.add(translateMessageToFraserMessage(service.getFirstMessageOf(thread))));
        return fraserMessages;
    }

    private CodeRaguet.fraser.model.Message translateMessageToFraserMessage(Message gmailMessage) {
        return gmailMessageTranslator.translate(pickHTMLDataFrom(gmailMessage));
    }

    private String pickHTMLDataFrom(Message message) {
        return pickHTMLMessagePartFrom(message).getBody().getData();
    }

    private MessagePart pickHTMLMessagePartFrom(Message message) {
        return message.getPayload().getParts().get(1);
    }

}
