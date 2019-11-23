package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.factories.AstronauntFactory;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.REPORT_ASTRONAUT_INFO;

public class ControllerImpl implements Controller {
    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private Mission mission;
    private int exploredPlanets = 0;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.mission = new MissionImpl();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut = AstronauntFactory.createAstronaut(type, astronautName);

        if (astronaut == null) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }

        this.astronautRepository.add(astronaut);

        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);

        Collection<String> planetItems = planet.getItems();

        Collections.addAll(planetItems, items);

        this.planetRepository.add(planet);

        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {

        Astronaut astronaut = astronautRepository.findByName(astronautName);

        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        this.astronautRepository.remove(astronaut);

        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {

        Collection<Astronaut> astronauts = this.astronautRepository
                .getModels()
                .stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toCollection(ArrayList::new));

        if (astronauts.size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planet = this.planetRepository.findByName(planetName);

        this.mission.explore(planet, astronauts);
        this.exploredPlanets++;

        long dead = astronauts.stream().filter(a -> a.getOxygen() == 0).count();
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, dead);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED, this.exploredPlanets));
        sb.append(System.lineSeparator());
        sb.append(REPORT_ASTRONAUT_INFO);

        this.astronautRepository.getModels().forEach(e->sb.append(System.lineSeparator()).append(e.toString()));

        return sb.toString();
    }
}
