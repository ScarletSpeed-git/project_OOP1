package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

public class CloseCommand implements Command {
    private Universe universe;

    public CloseCommand(Universe universe) {
        this.universe = universe;
    }

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