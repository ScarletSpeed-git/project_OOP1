package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

/**
 * Command that closes the currently active XML file and clears the universe data from memory.
 * Usage: close
 */
public class CloseCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the CloseCommand.
     *
     * @param universe The universe instance to clear upon closing.
     */
    public CloseCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the close operation.
     * Verifies that a file is open, clears all planet and Jedi data from memory.
     *
     * @param args The command arguments (not actively used for this specific command).
     * @return A success message confirming which file was closed.
     * @throws FileException if there is no currently active file to close.
     */
    @Override
    public String execute(String[] args) {
        if (universe.getCurrentFilePath() == null) {
            throw new FileException("Error: No file is currently opened.");
        }

        String fileName = universe.getCurrentFilePath();
        universe.clearUniverse();

        return "Successfully closed " + fileName;
    }
}