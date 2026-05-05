package bg.tu_varna.sit.f24621689.commands;

import bg.tu_varna.sit.f24621689.interfaces.Command;

public class ExitCommand implements Command {

    @Override
    public String execute(String[] args) {
        return "Exiting the program...";
    }
}