package CodeRaguet.fraser.tests.tools.fixtures;

import CodeRaguet.fraser.model.Frase;
import CodeRaguet.fraser.tests.tools.fixtures.Messages;

public class Frases {
    public static Frase second() {
        return new Frase(Messages.second().getText());
    }

    public static Frase longFrase() {
        return new Frase(Messages.longMessage().getText());
    }
}
