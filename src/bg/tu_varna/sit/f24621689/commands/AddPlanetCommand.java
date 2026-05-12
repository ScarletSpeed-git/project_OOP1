package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Planet;

/**
 * Command that creates a new empty planet and adds it to the universe.
 * Usage: add_planet <planet_name>
 */
public class AddPlanetCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the AddPlanetCommand.
     *
     * @param universe The universe instance to add the new planet to.
     */
    public AddPlanetCommand(Universe universe) {
        this.universe = universe;
    }

    /**
     * Executes the addition of a new planet to the universe.
     * Validates the input and ensures no duplicate planets are created.
     *
     * @param args Array of arguments where args[1] is the new planet's name.
     * @return A success message confirming the planet was added.
     * @throws IllegalArgumentException if the planet name argument is missing.
     * @throws PlanetException if a planet with the exact same name already exists.
     */
    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user provided the required planet name argument
         */
        if (args.length < 2) {
            throw new IllegalArgumentException("Missing planet name. Usage: add_planet <planet_name>");
        }

        /**
         *  Extract the planet name from the user input
         */
        String planetName = args[1];

        /**
         *  Check if a planet with this name already exists in the universe to prevent duplicate.
         */
        if (universe.getPlanetByName(planetName) != null) {
            throw new PlanetException("Error: Planet " + planetName + " already exists.");
        }

        /**
         *  Create the new empty planet and add it to the universe's master list
         */
        universe.getPlanets().add(new Planet(planetName));

        /**
         *  Return the success message to be printed to the console
         */
        return "Successfully added planet " + planetName;
    }
}