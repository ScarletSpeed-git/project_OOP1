package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.JediException;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import java.util.List;

/**
 * Command that removes a specific Jedi from a specified planet's population.
 * Usage: remove_jedi <planet_name> <jedi_name>
 */
public class RemoveJediCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the RemoveJediCommand.
     *
     * @param universe The universe instance used to locate the planet and Jedi.
     */
    public RemoveJediCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the removal of a Jedi from a planet.
     * Verifies the planet exists and that the Jedi currently inhabits it before removal.
     *
     * @param args Array of arguments where args[1] is the planet name and args[2] is the Jedi name.
     * @return A success message confirming the Jedi was removed.
     * @throws IllegalArgumentException if required arguments are missing.
     * @throws PlanetException if the specified planet does not exist.
     * @throws JediException if the Jedi is not found on the specified planet.
     */
    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user provided both the planet name and Jedi name
         */
        if (args.length < 3) {
            throw new IllegalArgumentException("Error: Missing arguments. Usage: remove_jedi <planet_name> <jedi_name>");
        }

        /**
         * Extract the target planet and Jedi names from the input
         */
        String planetName = args[1];
        String jediName = args[2];

        /**
         * Locate the target planet and verify it actually exists in the universe
         */
        Planet planet = universe.getPlanetByName(planetName);
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        /**
         * Retrieve the planet's population and search for the specific Jedi
         */
        List<Jedi> population = planet.getPopulation();
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getName().equalsIgnoreCase(jediName)) {
                // Remove the Jedi from the list once found and exit immediately
                population.remove(i);
                return "Successfully removed " + jediName + " from " + planetName;
            }
        }

        /**
         *  If the loop completes without finding the Jedi, throw an error
         */
        throw new JediException("Error: Jedi " + jediName + " does not inhabit " + planetName);
    }
}