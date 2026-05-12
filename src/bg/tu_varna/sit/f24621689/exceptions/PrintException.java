package bg.tu_varna.sit.f24621689.exceptions;

/**
 * Custom runtime exception used to indicate errors related to print operations.
 */
public class PrintException extends RuntimeException {

    /**
     * Constructs a new PrintException with the specified detailed error message.
     *
     * @param message The detailed message explaining the exact cause of the error.
     */
    public PrintException(String message) {
        super(message);
    }
}
