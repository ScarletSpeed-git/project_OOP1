package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.DemoteJediException;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

public class DemoteJediCommand implements Command {
    private Universe universe;

    public DemoteJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            throw new DemoteJediException("Error: Missing arguments. Usage: demote_jedi <jedi_name> <multiplier>");
        }

        String jediName = args[1];
        double multiplier;

        try {
            multiplier = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            throw new DemoteJediException("Error: Multiplier must be a number.");
        }

        if (multiplier <= 0) {
            throw new DemoteJediException("Error: Multiplier must be a positive number.");
        }

        Jedi targetJedi = null;
        for (Planet planet : universe.getPlanets()) {
            for (Jedi jedi : planet.getPopulation()) {
                if (jedi.getName().equalsIgnoreCase(jediName)) {
                    targetJedi = jedi;
                    break;
                }
            }
            if (targetJedi != null) break;
        }

        if (targetJedi == null) {
            throw new DemoteJediException("Error: Jedi " + jediName + " not found.");
        }

        if (targetJedi.getRank() == Rank.YOUNGLING) {
            throw new DemoteJediException("Error: " + jediName + " is already a YOUNGLING and cannot be demoted.");
        }

        Rank[] allRanks = Rank.values();
        Rank previousRank = allRanks[targetJedi.getRank().ordinal() - 1]; // Move down one index
        targetJedi.setRank(previousRank);

        double newStrength = targetJedi.getStrength() - (multiplier * targetJedi.getStrength());
        targetJedi.setStrength(newStrength);

        return "Successfully demoted " + jediName + " to " + previousRank + ". New strength: " + newStrength;
    }
}