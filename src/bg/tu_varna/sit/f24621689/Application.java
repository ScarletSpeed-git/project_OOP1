package bg.tu_varna.sit.f24621689;

import bg.tu_varna.sit.f24621689.commands.*;
import bg.tu_varna.sit.f24621689.data.Universe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Universe universe = new Universe();
        Scanner scanner = new Scanner(System.in);

        Map<String, Command> commandRegistry = new HashMap<>();

        commandRegistry.put("add_planet", new AddPlanetCommand(universe));
        commandRegistry.put("create_jedi", new CreateJediCommand(universe));
        commandRegistry.put("remove_jedi", new RemoveJediCommand(universe));
        commandRegistry.put("promote_jedi", new PromoteJediCommand(universe));
        commandRegistry.put("demote_jedi", new DemoteJediCommand(universe));
        commandRegistry.put("get_strongest_jedi", new GetStrongestJediCommand(universe));
        commandRegistry.put("get_youngest_jedi", new GetYoungestJediCommand(universe));
        commandRegistry.put("get_most_used_saber_color", new MostUsedSaberColorCommand(universe));
        commandRegistry.put("print", new PrintCommand(universe));

        System.out.println("Welcome to the Star Wars Universe! Type 'exit' to close.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            String[] commandArgs = input.split("\\s+");
            String commandName = commandArgs[0].toLowerCase();

            try {
                Command commandToExecute = commandRegistry.get(commandName);

                if (commandToExecute != null) {
                    String resultMessage = commandToExecute.execute(commandArgs);
                    System.out.println(resultMessage);
                } else {
                    System.out.println("Error: Unknown command '" + commandName + "'");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}