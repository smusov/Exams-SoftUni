package motocrossWorldChampionship.entities;

import static motocrossWorldChampionship.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SpeedMotorcycle extends MotorcycleImpl {

    private static final int DEFAULT_CUBIC_CENTIMETERS = 125;
    private static final int MIN_HORSEPOWER = 50;
    private static final int MAX_HORSEPOWER = 69;

    public SpeedMotorcycle(String model, int horsePower) {
        super(model, setHorsePower(horsePower), DEFAULT_CUBIC_CENTIMETERS);
    }

    private static int setHorsePower(int horsePower) {
        if (horsePower<MIN_HORSEPOWER||horsePower>MAX_HORSEPOWER){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
        return horsePower;
    }
}
