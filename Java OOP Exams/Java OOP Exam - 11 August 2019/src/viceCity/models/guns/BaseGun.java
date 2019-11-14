package viceCity.models.guns;

public abstract class BaseGun implements Gun {

    private static final String NULL_OR_EMPTY_EXCEPTION_MESSAGE = "Name cannot be null or whitespace!";
    private static final String BULLETS_PER_BARREL_BELOW_ZERO_EXCEPTION_MESSAGE = "Bullets cannot be below zero!";
    private static final String TOTAL_BULLETS_BELOW_ZERO_EXCEPTION_MESSAGE = "Total bullets cannot be below zero!";

    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;

    BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        setName(name);
        setBulletsPerBarrel(bulletsPerBarrel);
        setTotalBullets(totalBullets);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    @Override
    public boolean canFire() {
        if (this.totalBullets == 0) {
            return bulletsPerBarrel != 0;
        }
        return true;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    @Override
    public abstract int fire();

    protected abstract void rechargeGun();

    void setBulletsPerBarrel(int bulletsPerBarrel) {
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException(BULLETS_PER_BARREL_BELOW_ZERO_EXCEPTION_MESSAGE);
        }
        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    void setTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException(TOTAL_BULLETS_BELOW_ZERO_EXCEPTION_MESSAGE);
        }
        this.totalBullets = totalBullets;
    }

    boolean barrelIsEmpty() {
        return this.bulletsPerBarrel == 0;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(NULL_OR_EMPTY_EXCEPTION_MESSAGE);
        }
        this.name = name;
    }
}
