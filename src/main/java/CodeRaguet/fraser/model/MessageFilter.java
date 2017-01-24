package CodeRaguet.fraser.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessageFilter {
    private List<String> senders = Collections.emptyList();

    public String subject() {
        return "f";
    }

    public List<String> allowedSenders() {
        return senders;
    }

    public void configureAllowedSenders(String sendersConfig) {
        String[] senders = sendersConfig.split(",");
        this.senders = Arrays.asList(senders);
    }
}
