package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static motocrossWorldChampionship.common.ExceptionMessages.RIDER_EXISTS;

public class RiderRepository implements Repository<Rider> {

    private Collection<Rider> riders;

    public RiderRepository() {
        this.riders = new HashSet<>();
    }

    @Override
    public Rider getByName(String name) {
        Rider rider = null;
        for (Rider model : riders) {
            if(model.getName().equals(name)){
                rider = model;
            }
        }
        return rider;
    }

    @Override
    public Collection<Rider> getAll() {
        return Collections.unmodifiableCollection(this.riders);
    }

    @Override
    public void add(Rider model) {

        if (this.riders.contains(model)){
            throw new IllegalArgumentException(String.format(RIDER_EXISTS,model.getName()));
        }

        this.riders.add(model);
    }

    @Override
    public boolean remove(Rider model) {
        return this.riders.remove(model);
    }
}
