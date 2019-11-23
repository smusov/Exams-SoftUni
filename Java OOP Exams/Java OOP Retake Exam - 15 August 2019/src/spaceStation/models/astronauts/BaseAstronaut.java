package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public abstract class BaseAstronaut implements Astronaut {

    private String name;
    private double oxygen;
    private Bag bag;

    private static final double DECREASED_OXYGEN_VALUE = 10;

    BaseAstronaut(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        this.bag = new Backpack();
    }

    void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public void breath() {

        if (this.oxygen - DECREASED_OXYGEN_VALUE < 0) {
            this.setOxygen(0);
        } else {
            this.setOxygen(this.oxygen - DECREASED_OXYGEN_VALUE);
        }

    }

    @Override
    public String toString() {
        return String.format(REPORT_ASTRONAUT_NAME, this.getName())
                + System.lineSeparator()
                + String.format(REPORT_ASTRONAUT_OXYGEN, this.getOxygen())
                + System.lineSeparator()
                + this.bag.toString();
    }
}
