package by.tms.project.controller.command;

import java.util.Arrays;
import java.util.Optional;

public class CommandDefiner {

    public CommandDefiner() {

    }

    public static Optional<CommandType> defineCommand(String commandName) {
        return Arrays.stream(CommandType.values())
                .filter(currentType -> currentType.name().equalsIgnoreCase(commandName))
                .findAny();
    }
}
