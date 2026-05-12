package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.data.UniverseXmlWriter;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

/**
 * Command that saves the current universe data to a newly specified XML file
 * and updates the active file path.
 * Usage: save as <file_path>
 */
public class SaveAsCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the SaveAsCommand.
     *
     * @param universe The universe instance to read data from.
     */
    public SaveAsCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user provided the correct "save as <file_path>" syntax
         */
        if (args.length < 3 || !args[1].equalsIgnoreCase("as")) {
            throw new FileException("Error: Usage: save as <file_path>");
        }


        /**
         *  Extract the new target file path from the user's command
         */
        String newFilePath = args[2];

        try {
            /**
             *  Instantiate the XML writer and save the current universe state to the new file
             */
            UniverseXmlWriter writer = new UniverseXmlWriter();
            writer.write(universe, newFilePath);

            /**
             *  Update the universe to track this newly created file as the active working file
             */
            universe.setCurrentFilePath(newFilePath);

            /**
             *  Return the success message to be printed to the console
             */
            return "Successfully saved as " + newFilePath;
        } catch (Exception e) {
            throw new FileException("Error saving the XML file: " + e.getMessage());
        }
    }
}