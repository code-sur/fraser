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

    private static Message fifthMessage() {
        return new Message("Frase with html entities: &quot;ñandú!!&quot;");
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

    public static List<Message> messagesSubjectF() {
        return Arrays.asList(firstMessage(), secondMessage(), thirdMessage(), messageFromStranger(), fourthMessage(), fifthMessage());
    }

    private static Message messageFromStranger() {
        return new Message("Frase from stranger.");
    }

    public static Message someMessage() {
        return firstMessage();
    }

    public static List<Message> messagesSubjectFFromAllowedSenders() {
        return Arrays.asList(firstMessage(), secondMessage(), thirdMessage(), fourthMessage(), fifthMessage());
    }
}
