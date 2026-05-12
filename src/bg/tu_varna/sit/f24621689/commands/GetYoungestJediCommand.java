package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.exceptions.JediException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

/**
 * Command that finds the youngest Jedi of a specific rank on a given planet.
 * If multiple Jedi share the same youngest age, it resolves the tie alphabetically by name.
 * Usage: get_youngest_jedi <planet_name> <jedi_rank>
 */
public class GetYoungestJediCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the GetYoungestJediCommand.
     *
     * @param universe The universe instance used to search for planets and Jedi.
     */
    public GetYoungestJediCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the search for the youngest Jedi of a specified rank.
     * Validates the inputs, filters the population, and handles age ties alphabetically.
     *
     * @param args Array of arguments where args[1] is the planet name and args[2] is the target rank.
     * @return A formatted string detailing the youngest Jedi found, or a message if none exist.
     * @throws JediException if arguments are missing or the rank format is invalid.
     * @throws PlanetException if the specified planet does not exist.
     */
    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user provided both the planet name and the required rank
         */
        if (args.length < 3) {
            throw new JediException("Error: Missing arguments. Usage: get_youngest_jedi <planet_name> <jedi_rank>");
        }

        String planetName = args[1];
        Rank targetRank;

        /**
         *  Safely parse the requested rank from the user's input
         */
        try {
            targetRank = Rank.valueOf(args[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JediException("Error: Invalid rank format.");
        }

        /**
         *  Locate the target planet and verify it actually exists
         */
        Planet planet = universe.getPlanetByName(planetName);
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        /**
         *  Variable to keep track of the youngest Jedi found so far
         */
        Jedi youngest = null;

        /**
         *  Iterate through all Jedi on the planet
         */
        for (Jedi jedi : planet.getPopulation()) {
            /**
             *  Only evaluate Jedi who hold the exact rank requested
             */
            if (jedi.getRank() == targetRank) {
                /**
                 *  If this is the first match, or if this Jedi is strictly younger, set them as the new youngest
                 */
                if (youngest == null) {
                    youngest = jedi;
                } else if (jedi.getAge() < youngest.getAge()) {
                    youngest = jedi;
                }
                /**
                 * If the ages are exactly the same, resolve the tie alphabetically by name
                 */
                else if (jedi.getAge() == youngest.getAge()) {
                    if (jedi.getName().compareToIgnoreCase(youngest.getName()) < 0) {
                        youngest = jedi;
                    }
                }
            }
        }

        /**
         *  If the loop finishes and no Jedi matched the rank criteria, inform the user
         */
        if (youngest == null) {
            return "There are no Jedi with rank " + targetRank + " on planet " + planetName;
        }

        /**
         *  Return the final result string with the youngest Jedi's name and age
         */
        return "The youngest " + targetRank + " on " + planetName + " is " + youngest.getName() +
                " (Age: " + youngest.getAge() + ")";
    }
}