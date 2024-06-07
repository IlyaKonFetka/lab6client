package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Command;
import org.example.interfaces.Console;
import org.example.model.Person;

public class MinByWeight extends Command {
    public MinByWeight() {
        super("min_by_weight", "вывести элемент с минимальным weight");
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) {
        return new Request(getName(),"");
    }
}
