package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static motocrossWorldChampionship.common.ExceptionMessages.RACE_EXISTS;

public class RaceRepository implements Repository<Race> {

    private Collection<Race> races;

    public RaceRepository() {
        this.races = new HashSet<>();
    }

    @Override
    public Race getByName(String name) {
        Race race = null;

        for (Race model : races) {
            if(model.getName().equals(name)){
                race = model;
            }
        }

        return race;
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.races);
    }

    @Override
    public void add(Race model) {

        if (this.races.contains(model)){
            throw new IllegalArgumentException(String.format(RACE_EXISTS,model.getName()));
        }

        this.races.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return this.races.remove(model);
    }
}
