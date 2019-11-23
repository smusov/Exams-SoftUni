package spaceStation.factories;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;

public class AstronauntFactory {

    public static Astronaut createAstronaut(String type, String name) {
        switch (type) {
            case "Biologist":
                return new Biologist(name);
            case "Geodesist":
                return new Geodesist(name);
            case "Meteorologist":
                return new Meteorologist(name);
            default:
                return null;
        }
    }

}
