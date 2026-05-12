package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.JediException;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

/**
 * Command that creates a new Jedi and assigns them to a specific planet.
 * Ensures that the Jedi's name is globally unique across the universe.
 * Usage: create_jedi <planet_name> <jedi_name> <jedi_rank> <jedi_age> <saber_color> <jedi_strength>
 */
public class CreateJediCommand implements Command {

    /**
     * The data repository containing the current state of the application.
     */
    private Universe universe;

    /**
     * Constructs the CreateJediCommand.
     *
     * @param universe The universe instance where the Jedi will be added.
     */
    public CreateJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        /**
         *  Verify that the user provided exactly the right amount of arguments
         */
        if (args.length < 7) {
            throw new IllegalArgumentException("Error: Missing arguments. Usage: create_jedi <planet_name> <jedi_name> <jedi_rank> <jedi_age> <saber_color> <jedi_strength>");
        }

        /**
         * Extract the basic string arguments
         */
        String planetName = args[1];
        String jediName = args[2];

        Rank rank;
        int age;
        double strength;

        /**
         * Safely parse the complex data types
         */
        try {
            rank = Rank.valueOf(args[3].toUpperCase());
            age = Integer.parseInt(args[4]);
            strength = Double.parseDouble(args[6]);
        } catch (IllegalArgumentException e) {
            /**
             *  Catch any typos in the rank, or non-numbers in age/strength
             */
            throw new JediException("Error: Invalid rank, age, or strength format.");
        }

        String saberColor = args[5];

        /**
         *  Look up the target planet and ensure it actually exist
         */
        Planet planet = universe.getPlanetByName(planetName);
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        /**
         * Enforce global uniqueness: ensure no other Jedi in the entire universe has this name
         */
        for (Planet p : universe.getPlanets()) {
            for (Jedi j : p.getPopulation()) {
                if (j.getName().equalsIgnoreCase(jediName)) {
                    throw new JediException("Error: A Jedi named " + jediName + " already exists.");
                }
            }
        }

        /**
         *  Instantiate the new Jedi and add them to the planet's population list
         */
        planet.getPopulation().add(new Jedi(jediName, rank, age, saberColor, strength));

        /**
         *  Return the success message to be displayed in the console
         */
        return "Successfully created Jedi " + jediName + " on " + planetName;
    }
}