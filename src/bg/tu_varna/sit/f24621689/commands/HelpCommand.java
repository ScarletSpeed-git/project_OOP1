package bg.tu_varna.sit.f24621689.commands;


import bg.tu_varna.sit.f24621689.interfaces.Command;

public class HelpCommand implements Command {

    @Override
    public String execute(String[] args) {
        StringBuilder helpText = new StringBuilder();

        helpText.append("========================================================================================\n");
        helpText.append("                               STAR WARS UNIVERSE COMMANDS                              \n");
        helpText.append("========================================================================================\n");

        helpText.append("[File Management]\n");
        helpText.append("  open <file>                 | opens <file>\n");
        helpText.append("  close                       | closes currently opened file\n");
        helpText.append("  save                        | saves the currently open file\n");
        helpText.append("  save as <file>              | saves the currently open file in <file>\n");
        helpText.append("  help                        | prints this information\n");
        helpText.append("  exit                        | exits the program\n\n");

        helpText.append("[Jedi & Planet Management]\n");
        helpText.append("  add_planet <name>                                               | Adds a new planet\n");
        helpText.append("  create_jedi <planet> <name> <rank> <age> <color> <strength>     | Creates a new Jedi\n");
        helpText.append("  remove_jedi <name>                                              | Removes a Jedi\n");
        helpText.append("  promote_jedi <name> <multiplier>                                | Promotes a Jedi\n");
        helpText.append("  demote_jedi <name> <multiplier>                                 | Demotes a Jedi\n\n");

        helpText.append("[Queries & Printing]\n");
        helpText.append("  get_strongest_jedi <planet>                                     | Finds strongest Jedi\n");
        helpText.append("  get_youngest_jedi <planet> <rank>                               | Finds youngest Jedi of rank\n");
        helpText.append("  get_most_used_saber_color <planet> [rank]                       | Finds most popular saber color\n");
        helpText.append("  print <planet>                                                  | Prints planet population\n");
        helpText.append("  print <jedi_name>                                               | Prints specific Jedi info\n");
        helpText.append("  print <planet_name> + <planet_name>                             | Prints combined planet info\n");
        helpText.append("========================================================================================");

        return helpText.toString();
    }
}