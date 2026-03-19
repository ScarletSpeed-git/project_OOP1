package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.CreateJediException;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

public class CreateJediCommand implements Command {
    private Universe universe;

    public CreateJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 7) {
            throw new CreateJediException("Error: Missing arguments. Usage: create_jedi <planet_name> <jedi_name> <jedi_rank> <jedi_age> <saber_color> <jedi_strength>");
        }

        String planetName = args[1];
        String jediName = args[2];
        Rank rank;
        int age;
        double strength;

        try {
            rank = Rank.valueOf(args[3].toUpperCase());
            age = Integer.parseInt(args[4]);
            strength = Double.parseDouble(args[6]);
        } catch (IllegalArgumentException e) {
            throw new CreateJediException("Error: Invalid rank, age, or strength format.");
        }

        String saberColor = args[5];

        Planet planet = universe.getPlanetByName(planetName);
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        // Check global uniqueness
        for (Planet p : universe.getPlanets()) {
            for (Jedi j : p.getPopulation()) {
                if (j.getName().equalsIgnoreCase(jediName)) {
                    throw new IllegalArgumentException("Error: A Jedi named " + jediName + " already exists.");
                }
            }
        }

        planet.getPopulation().add(new Jedi(jediName, rank, age, saberColor, strength));
        return "Successfully created Jedi " + jediName + " on " + planetName;
    }
}