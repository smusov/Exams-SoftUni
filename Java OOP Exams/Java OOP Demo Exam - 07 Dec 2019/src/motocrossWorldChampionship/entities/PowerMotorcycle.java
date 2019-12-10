package motocrossWorldChampionship.entities;

import static motocrossWorldChampionship.common.ExceptionMessages.INVALID_HORSE_POWER;

public class PowerMotorcycle extends MotorcycleImpl {

    private static final int DEFAULT_CUBIC_CENTIMETERS = 450;
    private static final int MIN_HORSEPOWER = 70;
    private static final int MAX_HORSEPOWER = 100;

    public PowerMotorcycle(String model, int horsePower) {
        super(model, setHorsePower(horsePower), DEFAULT_CUBIC_CENTIMETERS);
    }

    private static int setHorsePower(int horsePower) {

        if (horsePower<MIN_HORSEPOWER||horsePower>MAX_HORSEPOWER){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }

        return horsePower;
    }
}
