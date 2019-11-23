package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {

    private static final double DEFAULT_OXYGEN = 70;
    private static final double DECREASED_OXYGEN_VALUE = 5;

    public Biologist(String name) {
        super(name, DEFAULT_OXYGEN);
    }

    @Override
    public void breath() {
        if (this.getOxygen() - DECREASED_OXYGEN_VALUE >=0) {
            this.setOxygen(this.getOxygen() - DECREASED_OXYGEN_VALUE);
        } else {
            this.setOxygen(0);
        }
    }
}
