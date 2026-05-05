package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.data.UniverseXmlReader;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

import java.io.File;

public class OpenCommand implements Command {
    private Universe universe;

    public OpenCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 2) throw new FileException("Error: Missing file path. Usage: open <file_path>");

        String filePath = args[1];
        File file = new File(filePath);

        try {
            if (file.exists()) {
                universe.clearUniverse();

                UniverseXmlReader reader = new UniverseXmlReader();
                reader.read(universe, filePath);

                universe.setCurrentFilePath(filePath);
                return "Successfully opened " + file.getName();
            } else {
                file.createNewFile();
                universe.clearUniverse();
                universe.setCurrentFilePath(filePath);
                return "File did not exist. Created and opened empty file " + file.getName();
            }
        } catch (Exception e) {
            throw new FileException("Error reading the XML file: " + e.getMessage());
        }
    }
}