package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.data.UniverseXmlWriter;
import bg.tu_varna.sit.f24621689.exceptions.FileException;
import bg.tu_varna.sit.f24621689.interfaces.Command;

public class SaveCommand implements Command {
    private Universe universe;

    public SaveCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (universe.getCurrentFilePath() == null) {
            throw new FileException("Error: No file is currently opened to save.");
        }

        try {
            UniverseXmlWriter writer = new UniverseXmlWriter();
            writer.write(universe, universe.getCurrentFilePath());
            return "Successfully saved " + universe.getCurrentFilePath();
        } catch (Exception e) {
            throw new FileException("Error saving the XML file: " + e.getMessage());
        }
    }
}