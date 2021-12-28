package by.tms.project.controller.command;

import by.tms.project.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

public enum CommandType {
//    GO_TO_LOGIN(new ToLogin())
    ;
    private final Command command;
    private final List<Role> roleList;

    CommandType(Command command){
        this.command = command;
        this.roleList = new ArrayList<>();
    }

    CommandType(Command command, List<Role> roleList) {
        this.command = command;
        this.roleList = roleList;
    }

    public Command getCommand() {
        return command;
    }

    public List<Role> getRoleList() {
        return roleList;
    }
}
