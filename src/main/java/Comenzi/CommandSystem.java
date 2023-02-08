package Comenzi;

import java.util.ArrayList;
import java.util.List;

public class CommandSystem {

    private List<Command> commands = new ArrayList<>();
    private static CommandSystem instance;

    private CommandSystem() {
    }

    public static CommandSystem getInstance() {
        if (instance == null) instance = new CommandSystem();
        return instance;
    }

    public void clear() {
        commands.clear();
        instance = null;
    }

    // bag o comanda noua in executorul de comenzi

    public void addCommand(Command command) {
        commands.add(command);
    }

    // executa toate comenzile din lista
    public void execute() {
        for (Command command: commands) {
            command.execute();
        }
    }
}
