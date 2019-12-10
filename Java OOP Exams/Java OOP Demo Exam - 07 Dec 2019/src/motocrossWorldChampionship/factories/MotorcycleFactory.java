package motocrossWorldChampionship.factories;

import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public class MotorcycleFactory {

    public static Motorcycle createMotorcycle(String type, String model, int horsePower){
        if (type.equals("Speed")){
            return new SpeedMotorcycle(model, horsePower);
        }
        return new PowerMotorcycle(model, horsePower);
    }
}
