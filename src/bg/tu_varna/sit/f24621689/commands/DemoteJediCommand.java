package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.JediException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

/**
 * Command that demotes a Jedi to the previous rank and reduces their strength.
 * Usage: demote_jedi <jedi_name> <multiplier>
 */
public class DemoteJediCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the DemoteJediCommand.
     *
     * @param universe The universe instance to search and modify data in.
     */
    public DemoteJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        /**
         * Check if the user provided all required arguments
         */
        if (args.length < 3) {
            throw new IllegalArgumentException("Error: Missing arguments. Usage: demote_jedi <jedi_name> <multiplier>");
        }

        String jediName = args[1];
        double multiplier;

        /**
         * Safely parse the multiplier argument into a decimal number.
         */
        try {
            multiplier = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            throw new JediException("Error: Multiplier must be a number.");
        }

        /**
         * Ensure the multiplier is a valid positive value
         */
        if (multiplier <= 0) {
            throw new JediException("Error: Multiplier must be a positive number.");
        }

        /**
         * Search through all planets to find the specific Jedi by name
         */
        Jedi targetJedi = null;
        for (Planet planet : universe.getPlanets()) {
            for (Jedi jedi : planet.getPopulation()) {
                if (jedi.getName().equalsIgnoreCase(jediName)) {
                    targetJedi = jedi;
                    break;
                }
            }
            if (targetJedi != null) break; // Stop searching once the Jedi is found
        }

        /**
         * If the loop finished and the Jedi wasn't found, throw an error
         */
        if (targetJedi == null) {
            throw new JediException("Error: Jedi " + jediName + " not found.");
        }

        /**
         *  Prevent demoting a Youngling, as it is the lowest possible rank
         */
        if (targetJedi.getRank() == Rank.YOUNGLING) {
            throw new JediException("Error: " + jediName + " is already a YOUNGLING and cannot be demoted.");
        }

        /**
         *  Calculate and set the new, lower rank
         */
        Rank[] allRanks = Rank.values();
        Rank previousRank = allRanks[targetJedi.getRank().ordinal() - 1]; // Move down one index in the enum
        targetJedi.setRank(previousRank);

        /**
         *  Calculate the new reduced strength and apply it
         */
        double newStrength = targetJedi.getStrength() - (multiplier * targetJedi.getStrength());
        targetJedi.setStrength(newStrength);

        /**
         *  Return the success message to be printed to the console
         */
        return "Successfully demoted " + jediName + " to " + previousRank + ". New strength: " + newStrength;
    }
}