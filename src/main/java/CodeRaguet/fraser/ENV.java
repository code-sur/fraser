package CodeRaguet.fraser;

public enum ENV {
    REFRESH_TOKEN, CLIENT_SECRET,
    TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, TWITTER_ACCESS_TOKEN, TWITTER_ACCESS_TOKEN_SECRET;

    private final String value;

    ENV() {
        this.value = System.getenv(this.name());
    }

    public String value() {
        return value;
    }

}
