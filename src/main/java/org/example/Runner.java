package org.example;


import com.google.gson.JsonSyntaxException;
import org.example.TCP_components.Request;
import org.example.TCP_components.Response;
import org.example.cmd.utils.Ask;
import org.example.cmd.utils.Command;
import org.example.exceptions.ExecutionException;
import org.example.exceptions.NoSuchCommandFoundException;
import org.example.interfaces.Console;
import org.example.managers.CommandManager;
import org.example.managers.TCPSendManager;
import org.example.managers.TCPSerializationManager;
import org.example.managers.TCPTakeManager;

import java.io.IOException;
import java.util.*;

/**
 * Класс запускающий в себе интерактивный режим - бесконечный цикл приёма команд из консоли/файла-скрипта их обработки
 * и вывода результата
 */
public class Runner {
    private Console console;
    private CommandManager commandManager;
    private TCPSendManager sender;
    private TCPTakeManager taker;
    private TCPSerializationManager packager;

    public Runner(Console console, CommandManager commandManager, TCPSendManager sender, TCPTakeManager taker,
                  TCPSerializationManager packager) {
        this.console = console;
        this.commandManager = commandManager;
        this.sender = sender;
        this.taker = taker;
        this.packager = packager;
    }
    public void interactiveMode() {
        try {
            Request request;
            String userCommandName;
            String userCommandArgument;

            while (true) {
                console.prompt();
                userCommandName = console.read().trim();
                userCommandArgument = console.readln().trim();
                if (userCommandName.equals("exit")) {
                    break;
                }

                try {
                    request = launchCommand(userCommandName, userCommandArgument);
                    if (request == null) {
                        continue;
                    }
                    if (request.getSerializedRequestBody().equals("exit")) {
                        break;
                    }
                    String stringRequest = packager.serialize(request);

                    // Отправка сообщения
                    sender.push(stringRequest);

                    // Приём ответа
                    String stringResponse = taker.take();
                    Response response = packager.response(stringResponse);
                    String responseBody = response.getResponseBody();
                    if (response.isStatus()) {
                        if (responseBody != null && !responseBody.isEmpty()) {
                            console.println(response.getResponseBody());
                        }
                        console.printSuccessful(response.getTextStatus());
                    } else {
                        if (responseBody != null && !responseBody.isEmpty()) {
                            console.println(response.getResponseBody());
                        }
                        console.printError(response.getTextStatus());
                    }

                } catch (Ask.AskBreak e) {
                    console.printWarning("Остановка команды...");
                } catch (ExecutionException e) {
                    console.printError(e.getMessage() == null ? "Ошибка выполнения команды" : e.getMessage());
                } catch (NoSuchCommandFoundException e) {
                    console.printError(e.getMessage() == null ? "Команда не найдена у клиента" : e.getMessage());
                } catch (IOException e) {
                    console.printError("Сервер не отвечает");
                } catch (JsonSyntaxException e) {
                    console.printError("Ошибка чтения ответа сервера");
                }
            }
        } catch (NoSuchElementException exception) {
            console.printError("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            console.printError("Непредвиденная ошибка!");
        }
    }



    private Request launchCommand(String userCommandName, String userCommandArgument) throws ExecutionException,
            Ask.AskBreak, NoSuchCommandFoundException {
        if (userCommandName.isEmpty()) return null;
        Command command = commandManager.getCommands().get(userCommandName);

        if (command == null) {
            throw new NoSuchCommandFoundException("Команда '" + userCommandName + "' не найдена у клиента. " +
                    "Наберите 'help' для справки");
        }
        commandManager.addToHistory(userCommandName);
        return command.apply(userCommandArgument, commandManager.isScript());
    }
    public void powerOff(){
        System.exit(0);
    }
}