package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Ask;
import org.example.cmd.utils.Command;
import org.example.cmd.utils.SmartInputParser;
import org.example.exceptions.ExecutionException;
import org.example.interfaces.Console;
import org.example.model.Person;

public class RemoveByID extends Command {
    private Console console;
    public RemoveByID(Console console) {
        super("remove_by_id", "удалить элемент коллекции по id");
        this.console = console;
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) throws ExecutionException, Ask.AskBreak {
        int id;
        if (userCommandArgument.isEmpty()){
            id = SmartInputParser.parseTo_int(console,"id",true,isScript);
        }
        else {
            try {
                id = Integer.parseInt(userCommandArgument);
            } catch (NumberFormatException e) {
                throw new ExecutionException("Неверный формат id, введите целое число не меньше 0");
            }
        }
        return new Request(getName(), id+"");
    }
}
