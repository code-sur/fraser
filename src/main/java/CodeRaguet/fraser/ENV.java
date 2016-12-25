package CodeRaguet.fraser;

public enum ENV {
    REFRESH_TOKEN("REFRESH_TOKEN"),
    CLIENT_SECRET("CLIENT_SECRET");

    private final String value;

    ENV(String variable) {
        value = System.getenv(variable);
    }

    public String value() {
        return value;
    }
}
