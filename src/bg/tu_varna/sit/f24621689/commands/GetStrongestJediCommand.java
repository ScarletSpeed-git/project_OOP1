package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.interfaces.Command;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;

public class GetStrongestJediCommand implements Command {
    private Universe universe;

    public GetStrongestJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Error: Missing arguments. Usage: get_strongest_jedi <planet_name>");
        }

        String planetName = args[1];
        Planet planet = universe.getPlanetByName(planetName);

        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        if (planet.getPopulation().isEmpty()) {
            return "Planet " + planetName + " has no Jedi inhabitants.";
        }

        Jedi strongest = planet.getPopulation().get(0);
        for (Jedi jedi : planet.getPopulation()) {
            if (jedi.getStrength() > strongest.getStrength()) {
                strongest = jedi;
            }
        }

        return "The strongest Jedi on " + planetName + " is " + strongest.getName() +
                " (Strength: " + strongest.getStrength() + ")";
    }
}