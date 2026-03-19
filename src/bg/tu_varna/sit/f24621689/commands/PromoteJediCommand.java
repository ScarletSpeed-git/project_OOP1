package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PromoteJediException;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import bg.tu_varna.sit.f24621689.models.Rank;

public class PromoteJediCommand implements Command {
    private Universe universe;

    public PromoteJediCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            throw new PromoteJediException("Error: Missing arguments. Usage: promote_jedi <jedi_name> <multiplier>");
        }

        String jediName = args[1];
        double multiplier;

        try {
            multiplier = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            throw new PromoteJediException("Error: Multiplier must be a number.");
        }

        if (multiplier <= 0) {
            throw new PromoteJediException("Error: Multiplier must be a positive number.");
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
            throw new PromoteJediException("Error: Jedi " + jediName + " not found.");
        }

        if (targetJedi.getRank() == Rank.GRAND_MASTER) {
            throw new PromoteJediException("Error: " + jediName + " is already a GRAND_MASTER.");
        }

        Rank[] allRanks = Rank.values();
        Rank nextRank = allRanks[targetJedi.getRank().ordinal() + 1];
        targetJedi.setRank(nextRank);

        double newStrength = targetJedi.getStrength() + (multiplier * targetJedi.getStrength());
        targetJedi.setStrength(newStrength);

        return "Successfully promoted " + jediName + " to " + nextRank + ". New strength: " + newStrength;
    }
}