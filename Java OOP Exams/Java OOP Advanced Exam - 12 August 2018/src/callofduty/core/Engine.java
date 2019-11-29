package callofduty.core;

import callofduty.interfaces.io.InputReader;
import callofduty.interfaces.io.OutputWriter;
import callofduty.interfaces.managers.MissionManager;
import callofduty.io.ConsoleReader;
import callofduty.io.ConsoleWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {
    private InputReader reader;
    private OutputWriter writer;
    private MissionManager missionManager;

    public Engine(MissionManager missionManager, ConsoleReader reader , ConsoleWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.missionManager = missionManager;
    }

    public void run(){

        String line = this.reader.readLine();

        while (true){

            List<String> data = Arrays.stream(line.split(" ")).collect(Collectors.toList());

            List<String> arguments = data.stream().skip(1).collect(Collectors.toList());

            String output = "";

            switch (data.get(0)){
                case "Agent":
                    output = this.missionManager.agent(arguments);
                    break;
                case "Request":
                    output =  this.missionManager.request(arguments);
                    break;
                case "Complete":
                    output = this.missionManager.complete(arguments);
                    break;
                case "Status":
                    output =  this.missionManager.status(arguments);
                    break;
            }


            if (line.equals("Over")){
                System.out.println(this.missionManager.over(new ArrayList<>()));
                break;
            }

            this.writer.println(output);

            line = this.reader.readLine();
        }
    }
}
