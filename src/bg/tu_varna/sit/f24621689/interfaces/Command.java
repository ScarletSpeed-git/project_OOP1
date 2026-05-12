package bg.tu_varna.sit.f24621689.interfaces;

/**
 * Functional interface for the Command Design Pattern.
 */
public interface Command {

    /**
     * Executes the specific logic associated with the implemented command.
     */
    String execute(String[] args);
}