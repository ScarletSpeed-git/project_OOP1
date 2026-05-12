package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PrintException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import java.util.ArrayList;
import java.util.List;

/**
 * Command that handles all printing operations for the universe.
 * It supports three distinct modes:
 * 1. print <planet_name> (Prints all Jedi on a planet, sorted by rank then name)
 * 2. print <jedi_name> (Prints detailed information about a specific Jedi)
 * 3. print <planet_name> + <planet_name> (Prints a combined, alphabetically sorted list of Jedi from two planets)
 */
public class PrintCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the PrintCommand.
     *
     * @param universe The universe instance to read data from.
     */
    public PrintCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the appropriate print logic based on the provided arguments.
     *
     * @param args The user input array detailing what to print.
     * @return A formatted string containing the requested information.
     * @throws IllegalArgumentException if no target is provided.
     * @throws PrintException if the requested planets or Jedi cannot be found.
     */
    @Override
    public String execute(String[] args) {
        /**
         *  Ensure the user provided at least one target to print
         */
        if (args.length < 2) {
            throw new IllegalArgumentException("Error: Missing arguments.");
        }

        /**
         *  print <planet1> + <planet2>
         */
        if (args.length == 4 && args[2].equals("+")) {
            Planet p1 = universe.getPlanetByName(args[1]);
            Planet p2 = universe.getPlanetByName(args[3]);

            if (p1 == null || p2 == null) {
                throw new PrintException("Error: One or both planets do not exist.");
            }

            /**
             *  Combine the populations of both planets into a single list
             */
            List<Jedi> combinedJedi = new ArrayList<>();
            combinedJedi.addAll(p1.getPopulation());
            combinedJedi.addAll(p2.getPopulation());

            /**
             *  Sort the combined list alphabetically by the Jedi's name
             */
            combinedJedi.sort((j1, j2) -> j1.getName().compareToIgnoreCase(j2.getName()));

            /**
             *  Build the final output string
             */
            StringBuilder sb = new StringBuilder("Combined Jedi of " + p1.getName() + " and " + p2.getName() + ":\n");
            for (Jedi j : combinedJedi) {
                sb.append("- ").append(j.getName()).append(" (").append(j.getRank()).append(")\n");
            }
            return sb.toString().trim();
        }

        String targetName = args[1];

        /**
         *  print <planet_name>
         */
        Planet planet = universe.getPlanetByName(targetName);
        if (planet != null) {
            /**
             *  Create a copy of the population list so we don't accidentally modify the actual data
             */
            List<Jedi> sortedJedi = new ArrayList<>(planet.getPopulation());

            /**
             *  Sort first by Rank (lowest to highest based on Enum order), then alphabetically by name
             */
            sortedJedi.sort((j1, j2) -> {
                int rankCompare = Integer.compare(j1.getRank().ordinal(), j2.getRank().ordinal());
                if (rankCompare != 0) return rankCompare; // If ranks are different, sort by rank
                return j1.getName().compareToIgnoreCase(j2.getName()); // If ranks are the same, sort by name
            });

            /**
             *  Build the detailed planet population output string
             */
            StringBuilder sb = new StringBuilder("Planet: " + planet.getName() + "\nJedi Population:\n");
            for (Jedi j : sortedJedi) {
                sb.append("- ").append(j.getName())
                        .append(" [Rank: ").append(j.getRank())
                        .append(", Age: ").append(j.getAge())
                        .append(", Saber: ").append(j.getSaberColor())
                        .append(", Strength: ").append(j.getStrength()).append("]\n");
            }
            return sb.toString().trim();
        }

        /**
         * print <jedi_name>
         * If the target wasn't a planet, search all planets to see if it is a Jedi's name
         */

        for (Planet p : universe.getPlanets()) {
            for (Jedi j : p.getPopulation()) {
                if (j.getName().equalsIgnoreCase(targetName)) {
                    /**
                     *  Return the specific Jedi's statistics and their current location
                     */
                    return "Jedi Info:\nName: " + j.getName() +
                            "\nRank: " + j.getRank() +
                            "\nAge: " + j.getAge() +
                            "\nSaber Color: " + j.getSaberColor() +
                            "\nStrength: " + j.getStrength() +
                            "\nCurrent Planet: " + p.getName();
                }
            }
        }

        /**
         *  If the code reaches this point, the target was neither a valid planet nor a valid Jedi
         */
        throw new PrintException("Error: Could not find a planet or Jedi named '" + targetName + "'.");
    }
}