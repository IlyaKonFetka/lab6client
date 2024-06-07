package org.example.cmd;

import org.example.TCP_components.Request;
import org.example.cmd.utils.Command;

/**
 * Класс команды "exit".
 * Прекращает выполнение программы
 */
public class Exit extends Command {
    public Exit() {
        super("exit","выйти из программы");
    }


    @Override
    public Request apply(String userCommandArgument, boolean isScript) {
        return new Request(getName(),"");
    }
}
