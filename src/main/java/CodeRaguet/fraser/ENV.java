package CodeRaguet.fraser;

public enum ENV {
    REFRESH_TOKEN, CLIENT_SECRET;

    private final String value;

    ENV() {
        this.value = System.getenv(this.name());
    }

    public String value() {
        return value;
    }

}
