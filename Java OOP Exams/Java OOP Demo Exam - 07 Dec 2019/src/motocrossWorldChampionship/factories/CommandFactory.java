package motocrossWorldChampionship.factories;

import motocrossWorldChampionship.commands.interfaces.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory {

    private static final String PATH = "motocrossWorldChampionship.commands.";

    public static Command makeCommand(String commandName){

        Command command = null;

        try {
            Class<?> clazz = Class.forName(PATH + commandName);

            Constructor<?> constructor = clazz.getConstructor();

            command = (Command) constructor.newInstance();

        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return command;
    }
}
