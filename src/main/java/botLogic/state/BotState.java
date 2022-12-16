package botLogic.state;

import command.CommandList;
import request.Request;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Перечисление, хранящее состояние диалога бота
 */
public enum BotState {
    /**
     * Основное состояние, в котором бот выполняет базовый набор команд
     */
    Main {
        @Override
        public BotState nextState(Request request) {
            if (request.command().equals(CommandList.RACE))
                return RaceInfo;
            return this;
        }

        @Override
        public ArrayList<String> getAvailableCommands() {
            return new ArrayList<>(Arrays.asList(
                    CommandList.START,
                    CommandList.HELP,
                    CommandList.RACE,
                    CommandList.LIST
            ));
        }
    },

    /**
     * Состояние в ветке диалога команды вывода информации о расе
     */
    RaceInfo {
        @Override
        public ArrayList<String> getAvailableCommands() {
            return new ArrayList<>(Arrays.asList(
                    CommandList.HELP,
                    CommandList.INFO,
                    CommandList.EXIT
            ));
        }

        @Override
        public BotState nextState(Request request) {
            if (request.command().equals(CommandList.EXIT))
                return Main;
            return this;
        }
    };

    /**
     * Функция перехода в следующее состояние
     *
     * @param request - команда, после которой состояние может измениться
     * @return возвращает новое состояние диалога бота
     */
    public abstract BotState nextState(Request request);

    /**
     * Функция получения доступных команд в текущем состоянии
     *
     * @return возвращает список доступных команд
     */
    public abstract ArrayList<String> getAvailableCommands();
}