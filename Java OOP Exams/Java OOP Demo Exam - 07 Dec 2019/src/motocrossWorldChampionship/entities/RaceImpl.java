package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static motocrossWorldChampionship.common.ExceptionMessages.*;

public class RaceImpl implements Race {

    private static final int MIN_LAPS = 1;
    private static final int MIN_NAME_LENGTH = 5;

    private String name;
    private int laps;
    private Collection<Rider> riders;

    public RaceImpl(String name , int laps) {
        setName(name);
        setLaps(laps);
        this.riders = new ArrayList<>();
    }

    private void setLaps(int laps) {
        if (laps<MIN_LAPS){
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS,MIN_LAPS));
        }
        this.laps = laps;
    }

    private void setName(String name) {
        if (name==null||name.trim().isEmpty()||name.length()< MIN_NAME_LENGTH){
            throw new IllegalArgumentException(String.format(INVALID_NAME,name, MIN_NAME_LENGTH));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return Collections.unmodifiableCollection(this.riders);
    }

    @Override
    public void addRider(Rider rider) {
        if (rider==null){
            throw new NullPointerException(RIDER_INVALID);
        }
        if (!rider.getCanParticipate()){
            throw new IllegalArgumentException(String.format(RIDER_NOT_PARTICIPATE,rider.getName()));
        }
        if (this.riders.contains(rider)){
            throw new IllegalArgumentException(String.format(RIDER_ALREADY_ADDED,rider.getName(),this.name));
        }
        this.riders.add(rider);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceImpl race = (RaceImpl) o;
        return name.equals(race.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
