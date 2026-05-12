package bg.tu_varna.sit.f24621689.exceptions;

/**
 * Custom runtime exception used to indicate errors related to file operations.
 */
public class FileException extends RuntimeException {

    /**
     * Constructs a new FileException with the specified detailed error message.
     *
     * @param message The detailed message explaining the exact cause of the error.
     */
    public FileException(String message) {
        super(message);
    }
}