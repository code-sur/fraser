package CodeRaguet.fraser.tests.tools.fixtures;

import CodeRaguet.fraser.model.Message;

public class Messages {

    public static Message firstMessage() {
        return new Message("El infierno es el olvido");
    }

    public static Message secondMessage() {
        return new Message("No te llevas nada");
    }

    public static Message longMessage() {
        return new Message("This is a 140 characters long frase. Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah... 140 characters!");
    }
}
