package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.data.UniverseXmlReader;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

import java.io.File;

/**
 * Command that opens a specified XML file and loads its data into the universe.
 * If the specified file does not exist, it creates a new empty file.
 * Usage: open <file_path>
 */
public class OpenCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the OpenCommand.
     *
     * @param universe The universe instance to clear and populate with new data.
     */
    public OpenCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the open operation.
     * Checks if the file exists; if so, it clears current data and reads the new data.
     * If not, it creates a new empty file. Updates the active file tracker in either case.
     *
     * @param args Array of arguments where args[1] is the target file path.
     * @return A success message confirming the file was opened or created.
     * @throws FileException if the file path is missing or if an error occurs during reading/creation.
     */
    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user actually provided a file path
         */
        if (args.length < 2) {
            throw new FileException("Error: Missing file path. Usage: open <file_path>");
        }

        /**
         *  Extract the file path from the arguments and create a File object
         */
        String filePath = args[1];
        File file = new File(filePath);

        try {
            /**
             *  Check if the requested file already exists on the system
             */
            if (file.exists()) {
                /**
                 *  Wipe the current memory clean so old data doesn't mix with the newly loaded data
                 */
                universe.clearUniverse();

                /**
                 *  Instantiate the XML reader and load the saved planets and Jedi into memory
                 */
                UniverseXmlReader reader = new UniverseXmlReader();
                reader.read(universe, filePath);

                /**
                 *  Update the tracker so the program knows which file is currently active
                 */
                universe.setCurrentFilePath(filePath);
                return "Successfully opened " + file.getName();
            } else {
                /**
                 *  If the file doesn't exist, safely create a brand new empty file
                 */
                file.createNewFile();

                /**
                 *  Ensure memory is cleared so the user is working with a fresh state
                 */
                universe.clearUniverse();

                /**
                 *  Track this newly created file as the active working file
                 */
                universe.setCurrentFilePath(filePath);
                return "File did not exist. Created and opened empty file " + file.getName();
            }
        } catch (Exception e) {
            /**
             *  Catch any unreadable data, permission errors, or I/O issues and throw a clean exception
             */
            throw new FileException("Error reading the XML file: " + e.getMessage());
        }
    }
}