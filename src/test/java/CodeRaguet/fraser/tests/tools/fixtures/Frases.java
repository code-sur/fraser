package CodeRaguet.fraser.tests.tools.fixtures;

import CodeRaguet.fraser.model.Frase;

import static CodeRaguet.fraser.tests.tools.fixtures.Messages.*;

public class Frases {

    public static Frase firstFrase() {
        return new Frase(firstMessage().getText());
    }

    public static Frase secondFrase() {
        return new Frase(secondMessage().getText());
    }

    public static Frase longFrase() {
        return new Frase(longMessage().getText());
    }

    public static Frase thirdFrase() {
        return longFrase();
    }
}
