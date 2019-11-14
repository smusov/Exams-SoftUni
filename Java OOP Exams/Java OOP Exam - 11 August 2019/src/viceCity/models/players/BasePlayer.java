package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.GunRepository;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player {

    private static final String PLAYER_LIFE_POINTS_CANNOT_BE_BELOW_ZERO = "Player life points cannot be below zero!";
    private static final String NOT_NULL_OR_EMPTY_EXCEPTION_MESSAGE = "Player's name cannot be null or a whitespace!";
    private static final int MINIMUM_LIFE_POINTS = 0;

    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    BasePlayer(String name, int lifePoints) {
        setName(name);
        setLifePoints(lifePoints);
        this.gunRepository = new GunRepository<>();
    }

    private void setLifePoints(int lifePoints) {
        if (lifePoints < 0) {
            throw new IllegalArgumentException(PLAYER_LIFE_POINTS_CANNOT_BE_BELOW_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(NOT_NULL_OR_EMPTY_EXCEPTION_MESSAGE);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > MINIMUM_LIFE_POINTS;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        this.lifePoints -= points;
        if (this.lifePoints - points < MINIMUM_LIFE_POINTS) {
            this.lifePoints = MINIMUM_LIFE_POINTS;
        }
    }
}
