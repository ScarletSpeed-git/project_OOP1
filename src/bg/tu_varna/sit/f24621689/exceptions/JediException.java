package bg.tu_varna.sit.f24621689.exceptions;

/**
 * Custom runtime exception used to indicate errors related to jedi operations.
 */
public class JediException extends RuntimeException {

    /**
     * Constructs a new JediException with the specified detailed error message.
     *
     * @param message The detailed message explaining the exact cause of the error.
     */
    public JediException(String message) {
        super(message);
    }
}
