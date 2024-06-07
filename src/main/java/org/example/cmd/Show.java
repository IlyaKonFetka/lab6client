package org.example.cmd;


import org.example.TCP_components.Request;
import org.example.cmd.utils.Command;
import org.example.interfaces.Console;

public class Show extends Command {
    Console console;
    public Show(Console console) {
        super("show", "вывести элементы коллекции");
        this.console = console;
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) {
        return new Request(getName(),"");
    }
}
