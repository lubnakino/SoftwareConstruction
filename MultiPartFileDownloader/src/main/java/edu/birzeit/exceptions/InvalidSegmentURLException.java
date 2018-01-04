package edu.birzeit.exceptions;

/**
 * @author Hadi-Awad
 *
 */
public class InvalidSegmentURLException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidSegmentURLException() {
        super();
    }

    public InvalidSegmentURLException(String message) {
        super(message);
    }

    public InvalidSegmentURLException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSegmentURLException(Throwable cause) {
        super(cause);
    }
}
