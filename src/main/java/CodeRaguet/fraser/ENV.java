package CodeRaguet.fraser;

public enum ENV {
    GMAIL_REFRESH_TOKEN, GMAIL_CLIENT_SECRET,
    TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, TWITTER_ACCESS_TOKEN, TWITTER_ACCESS_TOKEN_SECRET,
    DATABASE_URL;

    private final String value;

    ENV() {
        this.value = System.getenv(this.name());
    }

    public String value() {
        return value;
    }

}
