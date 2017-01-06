package CodeRaguet.fraser.gmail;

import java.io.IOException;

class GmailServiceException extends RuntimeException {
    GmailServiceException(String s, IOException e) {
        super(s, e);
    }
}
