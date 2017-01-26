package CodeRaguet.fraser.tests.tools.fixtures;

import CodeRaguet.fraser.model.Message;

import java.util.Arrays;
import java.util.List;

public class Messages {

    public static Message firstMessage() {
        return new Message("El infierno es el olvido");
    }

    public static Message secondMessage() {
        return new Message("No te llevas nada");
    }

    public static Message thirdMessage() {
        return longMessage();
    }

    private static Message fourthMessage() {
        return new Message("Fourth frase.");
    }

    public static Message fifthMessage() {
        return new Message("&quot;Frase with quotation marks&quot;");
    }

    public static Message longMessage() {
        return new Message("This is a 140 characters long frase. Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah... 140 characters!");
    }

    public static List<Message> allValidMessages() {
        return Arrays.asList(firstMessage(), secondMessage(), thirdMessage(), fourthMessage());
    }

    public static Message lastMessage() {
        return fourthMessage();
    }

    public static Message beforeMessageFromStranger() {
        return thirdMessage();
    }

    public static Message nextMessageNotFromStranger() {
        return fourthMessage();
    }

    public static Message messageFromStranger() {
        return new Message("Frase from stranger.");
    }

    public static Message someMessage() {
        return firstMessage();
    }

    public static Message beforeMessageWithHTMLQuotationMarks() {
        return fourthMessage();
    }

    public static Message beforeMessageWithQuotationMarks() {
        return fifthMessage();
    }
}
