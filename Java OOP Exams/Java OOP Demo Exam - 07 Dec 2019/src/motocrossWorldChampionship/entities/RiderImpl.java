package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.Objects;

import static motocrossWorldChampionship.common.ExceptionMessages.*;

public class RiderImpl implements Rider {

    private static final int MIN_NAME_LENGTH = 5;

    private String name;
    private Motorcycle motorcycle;
    private int numberOfWins;
    private boolean canParticipate;

    public RiderImpl(String name) {
       setName(name);
    }

    private void setName(String name) {
        if (name==null||name.trim().isEmpty()||name.length()<MIN_NAME_LENGTH){
            throw new IllegalArgumentException(String.format(INVALID_NAME,name,MIN_NAME_LENGTH));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Motorcycle getMotorcycle() {
        return this.motorcycle;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addMotorcycle(Motorcycle motorcycle) {
        if (motorcycle==null){
            throw new NullPointerException(MOTORCYCLE_INVALID);
        }
        this.motorcycle = motorcycle;
        this.canParticipate = true;
    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiderImpl rider = (RiderImpl) o;
        return name.equals(rider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
