package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static motocrossWorldChampionship.common.ExceptionMessages.MOTORCYCLE_EXISTS;

public class MotorcycleRepository implements Repository<Motorcycle> {

    private Collection<Motorcycle> motorcycles;

    public MotorcycleRepository() {
        this.motorcycles = new HashSet<>();
    }

    @Override
    public Motorcycle getByName(String name) {
        Motorcycle motorcycle = null;

        for (Motorcycle model : motorcycles) {
            if(model.getModel().equals(name)){
                motorcycle = model;
            }
        }

        return motorcycle;
    }

    @Override
    public Collection<Motorcycle> getAll() {
        return Collections.unmodifiableCollection(this.motorcycles);
    }

    @Override
    public void add(Motorcycle model) {

        if (this.motorcycles.contains(model)){
            throw new IllegalArgumentException(String.format(MOTORCYCLE_EXISTS,model.getModel()));
        }

        this.motorcycles.add(model);
    }

    @Override
    public boolean remove(Motorcycle model) {
        return this.motorcycles.remove(model);
    }
}
