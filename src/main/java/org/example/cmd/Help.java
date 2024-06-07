package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Command;
import org.example.interfaces.Console;
import org.example.managers.CommandManager;

public class Help extends Command {
    public Help() {
        super("help", "вывести информацию о доступных командах");
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) {
        return new Request(getName(),"");
    }
}
