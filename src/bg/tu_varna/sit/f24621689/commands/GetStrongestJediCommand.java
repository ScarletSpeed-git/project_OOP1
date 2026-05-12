package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;

/**
 * Command that finds and returns the Jedi with the highest strength on a specified planet.
 * Usage: get_strongest_jedi <planet_name>
 */
public class GetStrongestJediCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the GetStrongestJediCommand.
     *
     * @param universe The universe instance used to search for the planet and its inhabitants.
     */
    public GetStrongestJediCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the search for the strongest Jedi.
     * Validates the planet, checks for an empty population, and calculates the maximum strength.
     *
     * @param args Array of arguments where args[1] is the target planet name.
     * @return A formatted string detailing the strongest Jedi and their exact strength.
     * @throws IllegalArgumentException if the planet name argument is missing.
     * @throws PlanetException if the specified planet does not exist.
     */
    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user provided the required planet name argument
         */
        if (args.length < 2) {
            throw new IllegalArgumentException("Error: Missing arguments. Usage: get_strongest_jedi <planet_name>");
        }

        String planetName = args[1];

        /**
         *  Locate the target planet and verify it actually exists in the universe
         */
        Planet planet = universe.getPlanetByName(planetName);
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        /**
         *  Safely handle the edge case where the planet exists but has no Jedi living on it
         */
        if (planet.getPopulation().isEmpty()) {
            return "Planet " + planetName + " has no Jedi inhabitants.";
        }

        /**
         *  Assume the first Jedi in the list is the strongest to establish a baseline
         */
        Jedi strongest = planet.getPopulation().get(0);

        /**
         *  Iterate through the rest of the population to see if anyone has a higher
         */
        for (Jedi jedi : planet.getPopulation()) {
            if (jedi.getStrength() > strongest.getStrength()) {
                strongest = jedi; // Update the record holder if a stronger Jedi is found
            }
        }

        /**
         *  Return the final result string to the console
         */
        return "The strongest Jedi on " + planetName + " is " + strongest.getName() +
                " (Strength: " + strongest.getStrength() + ")";
    }
}