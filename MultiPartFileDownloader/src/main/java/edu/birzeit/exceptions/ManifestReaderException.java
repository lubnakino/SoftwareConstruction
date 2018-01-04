package edu.birzeit.exceptions;

/**
 * @author Hadi-Awad
 *
 */
public class ManifestReaderException extends Exception {
    private static final long serialVersionUID = 1L;

    public ManifestReaderException() {
        super();
    }

    public ManifestReaderException(String message) {
        super(message);
    }

    public ManifestReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManifestReaderException(Throwable cause) {
        super(cause);
    }
}
