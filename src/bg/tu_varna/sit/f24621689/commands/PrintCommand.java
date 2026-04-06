package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.exceptions.PrintException;
import bg.tu_varna.sit.f24621689.models.Jedi;
import bg.tu_varna.sit.f24621689.models.Planet;
import java.util.ArrayList;
import java.util.List;

public class PrintCommand implements Command {
    private Universe universe;

    public PrintCommand(Universe universe) {
        this.universe = universe;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Error: Missing arguments.");
        }

        if (args.length == 4 && args[2].equals("+")) {
            Planet p1 = universe.getPlanetByName(args[1]);
            Planet p2 = universe.getPlanetByName(args[3]);

            if (p1 == null || p2 == null) {
                throw new PrintException("Error: One or both planets do not exist.");
            }

            List<Jedi> combinedJedi = new ArrayList<>();
            combinedJedi.addAll(p1.getPopulation());
            combinedJedi.addAll(p2.getPopulation());

            combinedJedi.sort((j1, j2) -> j1.getName().compareToIgnoreCase(j2.getName()));

            StringBuilder sb = new StringBuilder("Combined Jedi of " + p1.getName() + " and " + p2.getName() + ":\n");
            for (Jedi j : combinedJedi) {
                sb.append("- ").append(j.getName()).append(" (").append(j.getRank()).append(")\n");
            }
            return sb.toString().trim();
        }

        String targetName = args[1];

        Planet planet = universe.getPlanetByName(targetName);
        if (planet != null) {
            List<Jedi> sortedJedi = new ArrayList<>(planet.getPopulation());

            sortedJedi.sort((j1, j2) -> {
                int rankCompare = Integer.compare(j1.getRank().ordinal(), j2.getRank().ordinal());
                if (rankCompare != 0) return rankCompare;
                return j1.getName().compareToIgnoreCase(j2.getName());
            });

            StringBuilder sb = new StringBuilder("Planet: " + planet.getName() + "\nJedi Population:\n");
            for (Jedi j : sortedJedi) {
                sb.append("- ").append(j.getName())
                        .append(" [Rank: ").append(j.getRank())
                        .append(", Age: ").append(j.getAge())
                        .append(", Saber: ").append(j.getSaberColor())
                        .append(", Strength: ").append(j.getStrength()).append("]\n");
            }
            return sb.toString().trim();
        }

        for (Planet p : universe.getPlanets()) {
            for (Jedi j : p.getPopulation()) {
                if (j.getName().equalsIgnoreCase(targetName)) {
                    return "Jedi Info:\nName: " + j.getName() +
                            "\nRank: " + j.getRank() +
                            "\nAge: " + j.getAge() +
                            "\nSaber Color: " + j.getSaberColor() +
                            "\nStrength: " + j.getStrength() +
                            "\nCurrent Planet: " + p.getName();
                }
            }
        }

        throw new PrintException("Error: Could not find a planet or Jedi named '" + targetName + "'.");
    }
}