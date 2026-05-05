package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.JediException;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

import java.util.HashMap;
import java.util.Map;

public class MostUsedSaberColorCommand implements Command {
    private Universe universe;

    public MostUsedSaberColorCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Error: Missing arguments.");
        }

        String planetName = args[1];
        Planet planet = universe.getPlanetByName(planetName);

        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }


        Map<String, Integer> colorCounts = new HashMap<>();


        if (args.length >= 3) {
            Rank targetRank;
            try {
                targetRank = Rank.valueOf(args[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new JediException("Error: Invalid rank format.");
            }

            for (Jedi jedi : planet.getPopulation()) {
                if (jedi.getRank() == targetRank) {

                    colorCounts.put(jedi.getSaberColor(), colorCounts.getOrDefault(jedi.getSaberColor(), 0) + 1);
                }
            }
        }

        else {

            java.util.List<String> grandMasterColors = new java.util.ArrayList<>();
            for (Jedi jedi : planet.getPopulation()) {
                if (jedi.getRank() == Rank.GRAND_MASTER) {
                    grandMasterColors.add(jedi.getSaberColor());
                }
            }

            if (grandMasterColors.isEmpty()) {
                return "There are no Grand Masters on " + planetName + ", so no colors qualify.";
            }

            for (Jedi jedi : planet.getPopulation()) {
                if (grandMasterColors.contains(jedi.getSaberColor())) {
                    colorCounts.put(jedi.getSaberColor(), colorCounts.getOrDefault(jedi.getSaberColor(), 0) + 1);
                }
            }
        }

        if (colorCounts.isEmpty()) {
            return "No lightsabers found matching the criteria on " + planetName + ".";
        }

        String mostUsedColor = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostUsedColor = entry.getKey();
            }
        }

        return "The most used saber color is " + mostUsedColor + " (used " + maxCount + " times).";
    }
}