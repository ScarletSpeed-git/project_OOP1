package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.data.UniverseXmlWriter;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

public class SaveAsCommand implements Command {
    private Universe universe;

    public SaveAsCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 3 || !args[1].equalsIgnoreCase("as")) {
            throw new FileException("Error: Usage: save as <file_path>");
        }
        if (universe.getCurrentFilePath() == null) {
            throw new FileException("Error: No file is currently opened.");
        }

        String newFilePath = args[2];

        try {
            UniverseXmlWriter writer = new UniverseXmlWriter();
            writer.write(universe, newFilePath);
            universe.setCurrentFilePath(newFilePath);
            return "Successfully saved as " + newFilePath;
        } catch (Exception e) {
            throw new FileException("Error saving the XML file: " + e.getMessage());
        }
    }
}