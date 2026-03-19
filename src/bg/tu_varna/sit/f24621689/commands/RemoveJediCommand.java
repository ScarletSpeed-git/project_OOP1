package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.exceptions.RemoveJebiException;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import java.util.List;

public class RemoveJediCommand implements Command {
    private Universe universe;

    public RemoveJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            throw new RemoveJebiException("Error: Missing arguments. Usage: remove_jedi <planet_name> <jedi_name>");
        }

        String planetName = args[1];
        String jediName = args[2];

        Planet planet = universe.getPlanetByName(planetName);
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        List<Jedi> population = planet.getPopulation();
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getName().equalsIgnoreCase(jediName)) {
                population.remove(i);
                return "Successfully removed " + jediName + " from " + planetName;
            }
        }

        throw new RemoveJebiException("Error: Jedi " + jediName + " does not inhabit " + planetName);
    }
}