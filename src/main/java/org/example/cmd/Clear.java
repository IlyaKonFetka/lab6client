package org.example.cmd;


import org.example.TCP_components.Request;
import org.example.cmd.utils.Command;

public class Clear extends Command {

    public Clear() {
        super("clear", "очистить коллекцию");
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) {
        return new Request(getName(), "");
    }
}
