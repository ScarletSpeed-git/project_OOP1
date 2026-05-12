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

/**
 * Command that calculates the most frequently used lightsaber color on a specified planet.
 * It can filter by a specific Jedi rank, or default to counting only the colors
 * utilized by Grand Masters.
 * Usage: most_used_saber_color <planet_name> [jedi_rank]
 */
public class MostUsedSaberColorCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the MostUsedSaberColorCommand.
     *
     * @param universe The universe instance used to search for planets and Jedi.
     */
    public MostUsedSaberColorCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the calculation to find the most popular lightsaber color.
     * Filters by rank if one is provided; otherwise, filters by colors used by Grand Masters.
     *
     * @param args Array of arguments where args[1] is the planet name and args[2] is an optional rank.
     * @return A formatted string displaying the most used color and its count.
     * @throws IllegalArgumentException if the planet name argument is missing.
     * @throws PlanetException if the specified planet does not exist.
     * @throws JediException if the optionally provided rank format is invalid.
     */
    @Override
    public String execute(String[] args) {
        /**
         * Ensure the user provided at least the required planet name argument
         */
        if (args.length < 2) {
            throw new IllegalArgumentException("Error: Missing arguments.");
        }

        String planetName = args[1];
        Planet planet = universe.getPlanetByName(planetName);

        /**
         *  Verify the planet actually exists in the universe
         */
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        /**
         *  Map to keep track of how many times each saber color appears
         */
        Map<String, Integer> colorCounts = new HashMap<>();

        /**
         *  If a third argument is provided, the user wants to filter by a specific rank
         */
        if (args.length >= 3) {
            Rank targetRank;

            /**
             *  Safely parse the requested rank
             */
            try {
                targetRank = Rank.valueOf(args[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new JediException("Error: Invalid rank format.");
            }

            /**
             *  Count the colors only for Jedi holding that exact rank
             */
            for (Jedi jedi : planet.getPopulation()) {
                if (jedi.getRank() == targetRank) {
                    colorCounts.put(jedi.getSaberColor(), colorCounts.getOrDefault(jedi.getSaberColor(), 0) + 1);
                }
            }
        }
        /**
         *  If no rank is provided, default to the Grand Master color tracking logic
         */
        else {
            /**
             *  First, collect all unique saber colors used by Grand Masters on this planet
             */
            java.util.List<String> grandMasterColors = new java.util.ArrayList<>();
            for (Jedi jedi : planet.getPopulation()) {
                if (jedi.getRank() == Rank.GRAND_MASTER) {
                    grandMasterColors.add(jedi.getSaberColor());
                }
            }

            /**
             *  If there are no Grand Masters, safely exit early
             */
            if (grandMasterColors.isEmpty()) {
                return "There are no Grand Masters on " + planetName + ", so no colors qualify.";
            }

            /**
             *  Count the colors for all Jedi on the planet, but ONLY if that color is used by a Grand Master
             */
            for (Jedi jedi : planet.getPopulation()) {
                if (grandMasterColors.contains(jedi.getSaberColor())) {
                    colorCounts.put(jedi.getSaberColor(), colorCounts.getOrDefault(jedi.getSaberColor(), 0) + 1);
                }
            }
        }

        /**
         *  If no lightsabers matched the criteria, inform the user
         */
        if (colorCounts.isEmpty()) {
            return "No lightsabers found matching the criteria on " + planetName + ".";
        }

        String mostUsedColor = "";
        int maxCount = 0;

        /**
         *  Iterate through the frequency map to find the color with the highest count
         */
        for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostUsedColor = entry.getKey();
            }
        }

        /**
         *  Return the final result to the console
         */
        return "The most used saber color is " + mostUsedColor + " (used " + maxCount + " times).";
    }
}