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

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void execute() {
        for (Command command: commands) {
            command.execute();
        }
    }
}
