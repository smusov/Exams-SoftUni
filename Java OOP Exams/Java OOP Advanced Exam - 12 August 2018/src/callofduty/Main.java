package callofduty;

import callofduty.core.Engine;
import callofduty.core.MissionManagerImpl;
import callofduty.io.ConsoleReader;
import callofduty.io.ConsoleWriter;

public class Main {
    public static void main(String[] args)  {

        Engine engine = new Engine(new MissionManagerImpl(),new ConsoleReader(),new ConsoleWriter() );
        engine.run();

    }
}




