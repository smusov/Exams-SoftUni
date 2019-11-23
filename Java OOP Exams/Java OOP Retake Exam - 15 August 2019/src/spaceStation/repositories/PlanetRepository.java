package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.*;

public class PlanetRepository implements Repository<Planet> {

    private Set<Planet> planets;

    public PlanetRepository() {
        this.planets = new LinkedHashSet<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return this.planets;
    }

    @Override
    public void add(Planet model) {
        this.planets.add(model);
    }

    @Override
    public boolean remove(Planet model) {
        return this.planets.remove(model);
    }

    @Override
    public Planet findByName(String name) {
        return this.planets.stream().filter(e->e.getName().equals(name)).findFirst().orElse(null);
    }
}
