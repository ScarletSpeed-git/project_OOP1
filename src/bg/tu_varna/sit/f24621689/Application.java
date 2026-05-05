package bg.tu_varna.sit.f24621689;

import bg.tu_varna.sit.f24621689.data.Universe;
import bg.tu_varna.sit.f24621689.invoker.CommandInvoker;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Universe universe = new Universe();
        CommandInvoker invoker = new CommandInvoker(universe);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Star Wars Universe! Type 'help' for commands or 'exit' to close.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            try {
                System.out.println(invoker.execute(input));

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}