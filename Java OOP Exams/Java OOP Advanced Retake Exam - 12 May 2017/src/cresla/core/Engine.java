package cresla.core;

import cresla.factories.ModuleFactory;
import cresla.factories.ReactorFactory;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.io.InputReaderImpl;
import cresla.io.OutputWriterImpl;
import cresla.repositores.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {

    private Repository repository;
    private ModuleFactory moduleFactory;
    private ReactorFactory reactorFactory;

    private InputReader reader;
    private OutputWriter writer;

    public Engine() {

        this.repository = new Repository();
        this.moduleFactory = new ModuleFactory();
        this.reactorFactory = new ReactorFactory();

        this.reader = new InputReaderImpl();
        this.writer = new OutputWriterImpl();
    }

    public void run() throws IOException {

        Manager manager = new ManagerImpl(this.repository, this.moduleFactory, this.reactorFactory);

        List<String> commands = Arrays.stream(this.reader.readLine().split("\\s+")).collect(Collectors.toList());


        while (true) {

            String output = "";

            switch (commands.get(0)) {
                case "Reactor":
                    output = manager.reactorCommand(commands);
                    break;
                case "Module":
                    output = manager.moduleCommand(commands);
                    break;
                case "Report":
                    output = manager.reportCommand(commands);
                    break;
                case "Exit":
                    output = manager.exitCommand(commands);
                    break;
            }

            this.writer.writeLine(output);

            if (commands.get(0).equals("Exit")) {
                return;
            }
            commands = Arrays.stream(this.reader.readLine().split("\\s+")).collect(Collectors.toList());
        }

    }
}
