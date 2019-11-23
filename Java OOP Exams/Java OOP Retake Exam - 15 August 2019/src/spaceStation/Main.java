package spaceStation;

import spaceStation.core.ControllerImpl;
import spaceStation.core.Engine;
import spaceStation.core.EngineImpl;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.PlanetImpl;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl(new ControllerImpl());
        engine.run();

    }
}
