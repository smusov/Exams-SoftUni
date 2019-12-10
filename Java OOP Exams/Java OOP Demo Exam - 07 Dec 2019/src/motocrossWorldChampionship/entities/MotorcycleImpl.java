package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;

import java.util.Objects;

import static motocrossWorldChampionship.common.ExceptionMessages.*;


public abstract class MotorcycleImpl implements Motorcycle {

    public static final int MIN_MOTORCYCLE_NAME_LENGTH = 4;

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model,int horsePower,double cubicCentimeters) {
        setModel(model);
        this.horsePower = horsePower;
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < MIN_MOTORCYCLE_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model, MIN_MOTORCYCLE_NAME_LENGTH));
        }
        this.model = model;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / (this.horsePower * laps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotorcycleImpl that = (MotorcycleImpl) o;
        return model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
