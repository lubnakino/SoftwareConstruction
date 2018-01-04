package edu.birzeit.exceptions;

/**
 * @author Hadi-Awad
 *
 */
public class InvalidManifestURLException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidManifestURLException() {
        super();
    }

    public InvalidManifestURLException(String message) {
        super(message);
    }

    public InvalidManifestURLException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidManifestURLException(Throwable cause) {
        super(cause);
    }
}
