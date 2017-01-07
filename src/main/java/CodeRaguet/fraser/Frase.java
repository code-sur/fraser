package CodeRaguet.fraser;

public class Frase {
    private final String frase;

    public Frase(String frase) {
        this.frase = frase;
    }

    @Override
    public String toString() {
        return frase;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frase frase1 = (Frase) o;

        return frase.equals(frase1.frase);

    }

    @Override public int hashCode() {
        return frase.hashCode();
    }
}
