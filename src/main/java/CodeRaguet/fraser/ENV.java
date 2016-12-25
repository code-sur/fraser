package CodeRaguet.fraser;

public enum ENV {
    REFRESH_TOKEN("REFRESH_TOKEN"),
    CLIENT_SECRET("CLIENT_SECRET");

    private final String value;
    private final String variable;

    ENV(String variable) {
        this.variable = variable;
        this.value = System.getenv(variable);
    }

    public String variable() {
        return variable;
    }

    public String value() {
        return value;
    }
}
