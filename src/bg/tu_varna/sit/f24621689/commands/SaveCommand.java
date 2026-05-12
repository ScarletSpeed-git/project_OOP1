package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.data.UniverseXmlWriter;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

/**
 * Command that saves the current universe data to the active XML file.
 * Usage: save
 */
public class SaveCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the SaveCommand.
     *
     * @param universe The universe instance to read data from.
     */
    public SaveCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        /**
         *  Ensure there is actually an active working file loaded in memory to save into
         */
        if (universe.getCurrentFilePath() == null) {
            throw new FileException("Error: No file is currently opened to save.");
        }

        try {
            /**
             *  Instantiate the XML writer utility
             */
            UniverseXmlWriter writer = new UniverseXmlWriter();

            /**
             *  Overwrite the currently tracked file with the latest state of the universe
             */
            writer.write(universe, universe.getCurrentFilePath());

            /**
             *  Return the success message to be printed to the console
             */
            return "Successfully saved " + universe.getCurrentFilePath();
        } catch (Exception e) {
            /**
             *  Catch any unexpected I/O or permissions errors and wrap them in a custom exception
             */
            throw new FileException("Error saving the XML file: " + e.getMessage());
        }
    }
}