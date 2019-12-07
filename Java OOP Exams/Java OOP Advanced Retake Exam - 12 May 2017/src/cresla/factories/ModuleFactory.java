package cresla.factories;

import cresla.interfaces.Module;
import cresla.models.modules.CooldownSystem;
import cresla.models.modules.CryogenRod;
import cresla.models.modules.HeatProcessor;

import java.util.List;

public class ModuleFactory extends Factory {


    //Parameters – reactorId (int), type (string), additionalParameter (int).
    //Module 3 CryogenRod 100
    //The type will either be “CryogenRod”, “HeatProcessor” or “CoolingSystem”.

    public Module createModule(List<String> args){
        switch (args.get(2)){
            case "CryogenRod":
                return new CryogenRod(super.getNextId(),Integer.parseInt(args.get(3)));
            case "HeatProcessor":
                return new HeatProcessor(super.getNextId(),Integer.parseInt(args.get(3)));
            case "CooldownSystem":
                return new CooldownSystem(super.getNextId(),Integer.parseInt(args.get(3)));
            default:
                return null;
        }
    }
}
