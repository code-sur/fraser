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

    public static Frase nextFraseNotFromStranger() {
        return new Frase(nextMessageNotFromStranger().getText());
    }

    public static Frase someFrase() {
        return new Frase(someMessage().getText());
    }

    public static Frase fraseWithQuotationMarksProperlyRendered() {
        return new Frase("\"Frase with quotation marks\"");
    }

    public static Frase fraseInQuotationMarks() {
        return new Frase("Frase in quotation marks");
    }
}
