package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Ask;
import org.example.cmd.utils.SmartInputParser;
import org.example.cmd.utils.Command;
import org.example.interfaces.Console;
import org.example.model.Person;

public class FilterContainsPassportID extends Command {
    Console console;
    public FilterContainsPassportID(Console console) {
        super("filter_contains_passport_i_d", "вывести элементы, значение passportID которых содержит подстроку");
        this.console = console;
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) throws Ask.AskBreak {
        String subStr;
        if (userCommandArgument.isEmpty()) {
            if(!isScript){
                console.print("Введите подстроку: ");
            }
            subStr = SmartInputParser.parseToString(console, "substring of passportID", isScript);

        }else subStr = userCommandArgument;
        return new Request(getName(), subStr);
    }
}
