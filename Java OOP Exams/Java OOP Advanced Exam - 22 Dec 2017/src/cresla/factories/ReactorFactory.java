package cresla.factories;

import cresla.interfaces.Reactor;
import cresla.models.reactors.CryoReactor;
import cresla.models.reactors.HeatReactor;

import java.util.List;

public class ReactorFactory extends Factory{

    //Parameters – type (string), additionalParameter (int), moduleCapacity (int).
    //Reactor Cryo 10 10

    public Reactor createReactor(List<String> args){
        if (args.get(1).equals("Cryo")){
            return new CryoReactor(super.getNextId(),Integer.parseInt(args.get(3)),Integer.parseInt(args.get(2)));
        }else {
            return new HeatReactor(super.getNextId(),Integer.parseInt(args.get(3)),Integer.parseInt(args.get(2)));
        }
    }
}
