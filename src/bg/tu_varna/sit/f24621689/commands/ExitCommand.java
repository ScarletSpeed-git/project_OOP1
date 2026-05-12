package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.interfaces.Command;

/**
 * Command that handles the termination of the application.
 * It provides the closing message right before the main REPL loop shuts down.
 * Usage: exit
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit operation.
     *
     * @param args The command arguments (not actively used for this command).
     * @return A farewell message indicating the program is shutting down.
     */
    @Override
    public String execute(String[] args) {
        return "Exiting the program...";
    }
}