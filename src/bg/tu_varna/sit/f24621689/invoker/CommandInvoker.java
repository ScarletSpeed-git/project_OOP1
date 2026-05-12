package bg.tu_varna.sit.f24621689.invoker;

import bg.tu_varna.sit.f24621689.commands.*;
import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.interfaces.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Acts as the "Invoker" component in the Command Design Pattern.
 * This class is responsible for receiving raw user input, routing it to the appropriate
 * {@link Command} object.
 */
public class CommandInvoker {

    /**
     * A central registry mapping string-based command names
     * to their corresponding executable {@link Command} object implementations.
     */
    private final Map<String, Command> commandRegistry;

    /**
     * A reference to the central data repository.
     */
    private final Universe universe;

    /**
     * Constructs a new CommandInvoker, initializes the command registry, and pre-loads
     * every supported application command into memory.
     *
     * @param universe The central {@link Universe} instance to be passed down to the commands.
     */
    public CommandInvoker(Universe universe) {
        this.universe = universe;
        this.commandRegistry = new HashMap<>();

        // Register File Management Commands
        commandRegistry.put("open", new OpenCommand(universe));
        commandRegistry.put("close", new CloseCommand(universe));
        commandRegistry.put("save", new SaveCommand(universe));
        commandRegistry.put("save as", new SaveAsCommand(universe));
        commandRegistry.put("help", new HelpCommand());
        commandRegistry.put("exit", new ExitCommand());

        // Register Domain Logic Commands
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

    /**
     * Parses the raw user input, validates the application's current state,
     * and triggers the execution of the requested command.
     *
     * @param input The raw, unparsed string typed by the user in the console.
     * @return A formatted String containing the success message or requested data
     * returned by the executed command.
     * @throws IllegalStateException    If a user attempts to run a data-modifying command
     * before opening a file.
     * @throws IllegalArgumentException If the user types a command that does not exist
     * in the registry.
     */
    public String execute(String input) {
        String commandName;
        String[] commandArgs;

        /**
         *  Custom string splitting logic to handle the multi-word "save as" command
         */
        if (input.toLowerCase().startsWith("save as")) {
            commandName = "save as";
            commandArgs = input.split("\\s+", 3);
        } else {
            /**
             *  Standard string splitting for all other command
             */
            commandArgs = input.split("\\s+");
            commandName = commandArgs[0].toLowerCase();
        }


        /**
         *  Retrieve the appropriate command object from the registry
         */
        Command commandToExecute = commandRegistry.get(commandName);

        /**
         *  Execute the command if found, otherwise throw an error
         */
        if (commandToExecute != null) {
            return commandToExecute.execute(commandArgs);
        } else {
            throw new IllegalArgumentException("Error: Unknown command '" + commandName + "'. Type 'help' for a list of commands.");
        }
    }
}