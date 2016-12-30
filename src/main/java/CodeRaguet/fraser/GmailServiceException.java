package CodeRaguet.fraser;

import java.io.IOException;

class GmailServiceException extends RuntimeException {
    GmailServiceException(String s, IOException e) {
        super(s, e);
    }
}
