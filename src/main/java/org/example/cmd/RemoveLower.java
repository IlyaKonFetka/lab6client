package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Ask;
import org.example.cmd.utils.SmartInputParser;
import org.example.cmd.utils.Command;
import org.example.interfaces.Console;
import org.example.model.Person;

public class RemoveLower extends Command {
    Console console;
    public RemoveLower(Console console) {
        super("remove_lover", "удалить все элементы меньше данного");
        this.console = console;
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) throws Ask.AskBreak {
        String newPersonName;
        if (userCommandArgument.isEmpty()) {
            if(!isScript)console.print("Введите имя Person, элементы меньше которого хотите удалить: ");

            newPersonName = SmartInputParser.parseToString(console, "name", isScript);
        }else {
            newPersonName = userCommandArgument;
        }

        return new Request(getName(),newPersonName);
    }
}
