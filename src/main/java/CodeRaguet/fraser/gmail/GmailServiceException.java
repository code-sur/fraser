package CodeRaguet.fraser.gmail;

class GmailServiceException extends RuntimeException {
    GmailServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
