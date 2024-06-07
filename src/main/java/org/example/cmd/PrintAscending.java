package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Command;
import org.example.interfaces.Console;
import org.example.model.Person;

public class PrintAscending extends Command {
    public PrintAscending(Console console) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) {
        return new Request(getName(), "");
    }
}
