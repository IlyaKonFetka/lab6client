package org.example;

import org.example.cmd.*;
import org.example.exceptions.ValidateException;
import org.example.managers.*;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;


public class Main {
    public static void main(String[] args) {

        var console = new ConsoleManager();

        try {
            var connector = new TCPConnectingManager(args[0], Short.parseShort(args[1]));
            var sender = new TCPSendManager(connector);
            var taker = new TCPTakeManager(connector);
            var serializator = new TCPSerializationManager();
            var commandManager = new CommandManager();

            commandManager.register(new Add(console, serializator));
            commandManager.register(new AddIfMax(console, serializator));
            commandManager.register(new Clear());
            commandManager.register(new ExecuteScript(console, commandManager, sender, taker, serializator));
            commandManager.register(new Exit());
            commandManager.register(new FilterContainsPassportID(console));
            commandManager.register(new Help());
            commandManager.register(new History());
            commandManager.register(new Info());
            commandManager.register(new MinByWeight());
            commandManager.register(new PrintAscending(console));
            commandManager.register(new RemoveByID(console));
            commandManager.register(new RemoveLower(console));
            commandManager.register(new Show(console));
            commandManager.register(new Update(console, serializator));

            connector.setKeepAliveMode(4000);

            new Runner(console, commandManager, sender, taker, serializator).interactiveMode();
        }catch (SocketTimeoutException e){
            console.printError("Соединение разорвано");
        } catch (IOException e) {
            console.printError("Сервер временно недоступен");
        }

    }
}