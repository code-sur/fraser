package CodeRaguet.fraser.gmail;


import CodeRaguet.fraser.model.MessageFilter;
import CodeRaguet.fraser.model.PostOffice;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GmailPostOffice implements PostOffice {

    private final GmailService service;
    private GmailFilterTranslator filterTranslator;
    private GmailMessageTranslator messageTranslator;

    public GmailPostOffice(GmailFilterTranslator filterTranslator, GmailMessageTranslator messageTranslator, GmailService service) {
        this.service = service;
        this.filterTranslator = filterTranslator;
        this.messageTranslator = messageTranslator;
    }

    @Override
    public List<CodeRaguet.fraser.model.Message> messagesFilteredBy(MessageFilter filter) {
        List<Thread> threads = getThreadsFilteredBy(filter);
        List<CodeRaguet.fraser.model.Message> fraserMessages = new ArrayList<>();
        threads.forEach(thread -> fraserMessages.add(translateMessageToFraserMessage(service.getFirstMessageOf(thread))));
        Collections.reverse(fraserMessages);
        return fraserMessages;
    }

    private CodeRaguet.fraser.model.Message translateMessageToFraserMessage(Message gmailMessage) {
        return messageTranslator.translate(gmailMessage);
    }

    private List<Thread> getThreadsFilteredBy(MessageFilter filter) {
        List<Thread> threads = new ArrayList<>();
        ListThreadsResponse response;
        String pageToken = null;
        do {
            try {
                response = getResponse(pageToken, filter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            threads.addAll(response.getThreads());
            pageToken = response.getNextPageToken();
        } while (response.getNextPageToken() != null);
        return threads;
    }

    private ListThreadsResponse getResponse(String pageToken, MessageFilter filter) throws IOException {
        Long threadsMaxResults = 100L;
        return service.getService().users().threads().list(GmailService.getUserId())
                .setMaxResults(threadsMaxResults)
                .setQ(filterTranslator.translate(filter))
                .setPageToken(pageToken)
                .execute();
    }

}
