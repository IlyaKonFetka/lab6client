package org.example.cmd;


import com.google.gson.JsonSyntaxException;
import org.example.TCP_components.Request;
import org.example.TCP_components.Response;
import org.example.cmd.utils.*;
import org.example.interfaces.Console;
import org.example.managers.TCPTakeManager;
import org.example.managers.TCPSendManager;
import org.example.managers.TCPSerializationManager;
import org.example.model.Person;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс - команды "add".
 * Команда добавляет в коллекцию новый элемент, последовательно считывая поля с консоли или из файла
 */
public class Add extends Command {
    private Console console;
    private TCPSerializationManager serializator;

    public Add(Console console, TCPSerializationManager serializator) {
        super("add", "добавить новый элемент в коллекцию");
        this.console = console;
        this.serializator = serializator;
    }

    @Override
    public Request apply(String userCommandArgument, boolean isScript) throws Ask.AskBreak {

        if (!isScript) {
            console.println("Введите данные для нового Person: ");
        }

        Person newPersonWithNullID = Ask.askPerson(console, null, null, isScript);
        String stringNewPersonWithNullID = serializator.serialize(newPersonWithNullID);

        return new Request(getName(), stringNewPersonWithNullID);

    }
}
