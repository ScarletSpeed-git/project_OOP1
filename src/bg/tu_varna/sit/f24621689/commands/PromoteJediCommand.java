package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.JediException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

/**
 * Command that promotes a Jedi to the next rank and increases their strength.
 * Usage: promote_jedi <jedi_name> <multiplier>
 */
public class PromoteJediCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the PromoteJediCommand.
     *
     * @param universe The universe instance used to search for and modify the target Jedi.
     */
    public PromoteJediCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the promotion of a Jedi.
     * Finds the Jedi by name, upgrades their rank by one level, and boosts their strength.
     *
     * @param args Array of arguments where args[1] is the Jedi's name and args[2] is the strength multiplier.
     * @return A success message detailing the Jedi's new rank and updated strength.
     * @throws IllegalArgumentException if required arguments are missing.
     * @throws JediException if the multiplier is invalid, the Jedi is not found, or they are already a GRAND_MASTER.
     */
    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user provided both the Jedi name and the multiplier
         */
        if (args.length < 3) {
            throw new IllegalArgumentException("Error: Missing arguments. Usage: promote_jedi <jedi_name> <multiplier>");
        }

        String jediName = args[1];
        double multiplier;

        /**
         *  Safely parse the multiplier argument into a decimal number
         */
        try {
            multiplier = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            throw new JediException("Error: Multiplier must be a number.");
        }

        /**
         *  Ensure the multiplier is a valid positive value
         */
        if (multiplier <= 0) {
            throw new JediException("Error: Multiplier must be a positive number.");
        }

        /**
         *  Search through all planets to find the specific Jedi by name
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
         *  If the loop finished and the Jedi wasn't found, throw an error
         */
        if (targetJedi == null) {
            throw new JediException("Error: Jedi " + jediName + " not found.");
        }

        /**
         *  Prevent promoting a Grand Master, as it is the highest possible rank
         */
        if (targetJedi.getRank() == Rank.GRAND_MASTER) {
            throw new JediException("Error: " + jediName + " is already a GRAND_MASTER.");
        }

        /**
         *  Calculate and set the new, higher rank
         */
        Rank[] allRanks = Rank.values();
        Rank nextRank = allRanks[targetJedi.getRank().ordinal() + 1]; // Move up one index in the enum
        targetJedi.setRank(nextRank);

        /**
         * Calculate the new increased strength and apply it
         */
        double newStrength = targetJedi.getStrength() + (multiplier * targetJedi.getStrength());
        targetJedi.setStrength(newStrength);

        /**
         *  Return the success message to be printed to the console
         */
        return "Successfully promoted " + jediName + " to " + nextRank + ". New strength: " + newStrength;
    }
}