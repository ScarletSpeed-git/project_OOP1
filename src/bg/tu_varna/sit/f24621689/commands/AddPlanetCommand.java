package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.models.Planet;

public class AddPlanetCommand  implements Command{

private Universe universe;

    public AddPlanetCommand(Universe universe) {
        this.universe = universe;
    }

    public String execute(String[] args) {
        if (args.length < 2) {
            throw new PlanetException("Missing planet name. Usage: add_planet <planet_name>");
        }

        String planetName = args[1];

        if (universe.getPlanetByName(planetName) != null) {
            throw new PlanetException("Error: Planet " + planetName + " already exists.");
        }

        universe.getPlanets().add(new Planet(planetName));
        return "Successfully added planet " + planetName;

    }

}
