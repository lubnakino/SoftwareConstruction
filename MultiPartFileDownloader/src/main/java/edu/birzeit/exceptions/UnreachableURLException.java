package edu.birzeit.exceptions;

/**
 * @author Hadi-Awad
 *
 */
public class UnreachableURLException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnreachableURLException() {
        super();
    }

    public UnreachableURLException(String message) {
        super(message);
    }

    public UnreachableURLException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnreachableURLException(Throwable cause) {
        super(cause);
    }
}
