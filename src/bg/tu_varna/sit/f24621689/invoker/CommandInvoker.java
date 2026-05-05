package bg.tu_varna.sit.f24621689.invoker;

import bg.tu_varna.sit.f24621689.commands.*;
import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.interfaces.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, Command> commandRegistry;
    private final Universe universe;

    public CommandInvoker(Universe universe) {
        this.universe = universe;
        this.commandRegistry = new HashMap<>();

        commandRegistry.put("open", new OpenCommand(universe));
        commandRegistry.put("close", new CloseCommand(universe));
        commandRegistry.put("save", new SaveCommand(universe));
        commandRegistry.put("save as", new SaveAsCommand(universe));
        commandRegistry.put("help", new HelpCommand());
        commandRegistry.put("exit", new ExitCommand());

        commandRegistry.put("add_planet", new AddPlanetCommand(universe));
        commandRegistry.put("create_jedi", new CreateJediCommand(universe));
        commandRegistry.put("remove_jedi", new RemoveJediCommand(universe));
        commandRegistry.put("promote_jedi", new PromoteJediCommand(universe));
        commandRegistry.put("demote_jedi", new DemoteJediCommand(universe));
        commandRegistry.put("get_strongest_jedi", new GetStrongestJediCommand(universe));
        commandRegistry.put("get_youngest_jedi", new GetYoungestJediCommand(universe));
        commandRegistry.put("get_most_used_saber_color", new MostUsedSaberColorCommand(universe));
        commandRegistry.put("print", new PrintCommand(universe));
    }

    public String execute(String input) {
        String commandName;
        String[] commandArgs;

        if (input.toLowerCase().startsWith("save as")) {
            commandName = "save as";
            commandArgs = input.split("\\s+", 3);
        } else {
            commandArgs = input.split("\\s+");
            commandName = commandArgs[0].toLowerCase();
        }

        boolean isFileCommand = commandName.equals("open") || commandName.equals("help") || commandName.equals("exit");
        if (!isFileCommand && universe.getCurrentFilePath() == null) {
            throw new IllegalStateException("Error: You must 'open <file>' before using this command.");
        }

        Command commandToExecute = commandRegistry.get(commandName);

        if (commandToExecute != null) {
            return commandToExecute.execute(commandArgs);
        } else {
            throw new IllegalArgumentException("Error: Unknown command '" + commandName + "'. Type 'help' for a list of commands.");
        }
    }
}