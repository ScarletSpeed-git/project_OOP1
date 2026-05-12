package bg.tu_varna.sit.f24621689.exceptions;

/**
 * Custom runtime exception used to indicate errors related to planet operations.
 */
public class PlanetException extends RuntimeException {

    /**
     * Constructs a new PlanetException with the specified detailed error message.
     *
     * @param message The detailed message explaining the exact cause of the error.
     */
    public PlanetException(String message) {
        super(message);
    }
}
