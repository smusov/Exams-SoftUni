package motocrossWorldChampionship.core;

import motocrossWorldChampionship.commands.interfaces.Command;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.factories.CommandFactory;
import motocrossWorldChampionship.io.ConsoleReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EngineImpl implements Engine {

    private ConsoleReader reader;
    private OutputWriter writer;

    private ChampionshipController championshipController;

    public EngineImpl(ConsoleReader reader, OutputWriter writer, ChampionshipController championshipController) {
        this.reader = reader;
        this.writer = writer;
        this.championshipController = championshipController;
    }

    @Override
    public void run() {

        String input;

        while (true) {
            try {
                if ("End".equals(input = this.reader.readLine())) {
                    break;
                }
                this.writer.writeLine(invoker(input));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException | NullPointerException ex) {
                this.writer.writeLine(ex.getMessage());
            }


        }

    }

    private String invoker(String input) {
        List<String> args = Arrays.stream(input.split("\\s+")).collect(Collectors.toList());
        Command command = CommandFactory.makeCommand(args.get(0));
        return command.execute(args.subList(1, args.size()), this.championshipController);
    }
}
