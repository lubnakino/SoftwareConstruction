package edu.birzeit.exceptions;

/**
 * @author Hadi-Awad
 *
 */
public class ManiFestParserException extends Exception {
    private static final long serialVersionUID = 1L;

    public ManiFestParserException() {
        super();
    }

    public ManiFestParserException(String message) {
        super(message);
    }

    public ManiFestParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManiFestParserException(Throwable cause) {
        super(cause);
    }

}
