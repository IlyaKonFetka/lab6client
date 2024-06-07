package org.example.managers;

import org.example.cmd.utils.Command;

import java.util.*;

/**
 * Класс управляющий командами и хранящий их
 */
public class CommandManager {
    /**
     * Список доступных команд
     */
    private final Map<String, Command> commands;
    /**
     * Список введённых команд в хронологическом порядке
     */
    private final List<String> commandHistory;
    /**
     * Имя файла-скрипта
     */
    private final HashSet<String> scriptFileNames;

    /**
     * Проверка на то, что объект используется скриптом
     * @return да/нет
     */
    public boolean isScript(){
        return !scriptFileNames.isEmpty();
    }
    public CommandManager() {
        this.commands = new LinkedHashMap<>();
        this.commandHistory = new ArrayList<>();
        this.scriptFileNames = new HashSet<>();
    }
    public void cleanScriptStack(){
        this.scriptFileNames.clear();
    }
    /**
     * Добавление команд и инструкций из файла-скрипта в поле scriptFileNames
     * @param fileName имя файла-скрипта
     * @return получилось/нет
     */
    public boolean addScriptFileName(String fileName){
        if (!scriptFileNames.contains(fileName)){
            scriptFileNames.add(fileName);
            return true;
        }return false;
    }

    /**
     * Удаление последней инструкции из списка команд скрипта. Используется при исполнении скрипта
     * @param fileName имя файла-скрипта
     * @return получилось/нет
     */
    public boolean deleteLastScriptFileName(String fileName){
        if (scriptFileNames.contains(fileName)){
            scriptFileNames.remove(fileName);
            return true;
        }return false;
    }

    /**
     * Регистрация новой команды в памяти объекта
     * @param commandName - имя новой команды
     * @param command - тип команды (объект класса)
     */
    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Возвращает список доступных команд
     * @return список
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Возвращает историю введённых команд
     * @return список
     */
    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Добавляет в историю введённых команд новую
     * @param command новая команда
     */
    public void addToHistory(String command) {
        commandHistory.add(command);
    }
}
