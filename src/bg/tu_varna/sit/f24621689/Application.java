package bg.tu_varna.sit.f24621689;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.invoker.CommandInvoker;
import java.util.Scanner;

/**
 * The main entry point for the Star Wars Universe application.
 * This class initializes the core data structures and runs the interactive
 * command-line interface (REPL) that accepts and processes user input.
 */
public class Application {

    /**
     * The main method that bootstraps and runs the application.
     *
     * @param args Command line arguments (not utilized in this application).
     */
    public static void main(String[] args) {
        /**
         *  Initialize the central data repository that will hold all active memory
         */
        Universe universe = new Universe();

        /**
         *  Initialize the invoker, which acts as the controller mapping text to actual commands
         */
        CommandInvoker invoker = new CommandInvoker(universe);

        /**
         *  Set up the scanner to read input directly from the user's console
         */
        Scanner scanner = new Scanner(System.in);

        /**
         *  Display the initial greeting and instructions
         */
        System.out.println("Welcome to the Star Wars Universe! Type 'help' for commands or 'exit' to close.");

        /**
         *  Start the infinite Read-Eval-Print Loop
         */
        while (true) {
            /**
             *  Print the prompt indicator so the user knows the program is waiting for input
             */
            System.out.print("> ");

            /**
             *  Read the entire line of user input and remove any accidental leading/trailing spaces
             */
            String input = scanner.nextLine().trim();

            /**
             *  If the user just pressed Enter without typing anything, simply prompt them again
             */
            if (input.isEmpty()) continue;

            try {
                /**
                 *  Pass the raw text input to the invoker, execute the command, and print the resulting message
                 */
                System.out.println(invoker.execute(input));

                /**
                 *  If the user typed the exit command, break out of the infinite loop to shut down the program
                 */
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
            } catch (Exception e) {
                /**
                 *  Catch any custom exceptions and print the clean error message
                 */
                System.out.println(e.getMessage());
            }
        }

        /**
         *  Clean up system resources by closing the scanner before the program finishes
         */
        scanner.close();
    }
}