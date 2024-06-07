package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Command;

public class History extends Command {
    public History() {
        super("history", "вывести последние 8 команд");
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) {
        return new Request(getName(), "");
    }
}
