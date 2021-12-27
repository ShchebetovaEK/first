package by.tms.project.controller.command;

import java.util.Arrays;
import java.util.Optional;

public class CommandChoose {

    public CommandChoose(){

    }

    public static Optional<CommandType> chooseCommand(String commandName){
        return Arrays.stream(CommandType.values())
                .filter(currentType -> currentType.name().equalsIgnoreCase(commandName))
                .findAny();
    }
}
