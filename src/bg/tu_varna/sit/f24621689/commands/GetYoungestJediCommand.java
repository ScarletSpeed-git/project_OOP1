package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PlanetException;
import bg.tu_varna.sit.f24621689.exceptions.JediException;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

public class GetYoungestJediCommand implements Command {
    private Universe universe;

    public GetYoungestJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            throw new JediException("Error: Missing arguments. Usage: get_youngest_jedi <planet_name> <jedi_rank>");
        }

        String planetName = args[1];
        Rank targetRank;

        try {
            targetRank = Rank.valueOf(args[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JediException("Error: Invalid rank format.");
        }

        Planet planet = universe.getPlanetByName(planetName);
        if (planet == null) {
            throw new PlanetException("Error: Planet " + planetName + " does not exist.");
        }

        Jedi youngest = null;

        for (Jedi jedi : planet.getPopulation()) {
            if (jedi.getRank() == targetRank) {
                if (youngest == null) {
                    youngest = jedi;
                } else if (jedi.getAge() < youngest.getAge()) {
                    youngest = jedi;
                } else if (jedi.getAge() == youngest.getAge()) {
                    if (jedi.getName().compareToIgnoreCase(youngest.getName()) < 0) {
                        youngest = jedi;
                    }
                }
            }
        }

        if (youngest == null) {
            return "There are no Jedi with rank " + targetRank + " on planet " + planetName;
        }

        return "The youngest " + targetRank + " on " + planetName + " is " + youngest.getName() +
                " (Age: " + youngest.getAge() + ")";
    }
}