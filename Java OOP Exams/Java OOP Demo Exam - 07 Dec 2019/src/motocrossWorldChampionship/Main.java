package motocrossWorldChampionship;

import motocrossWorldChampionship.core.ChampionshipControllerImpl;
import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.io.ConsoleReader;
import motocrossWorldChampionship.io.ConsoleWriter;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;
import motocrossWorldChampionship.repositories.interfaces.Repository;

public class Main {
    public static void main(String[] args) {

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();

        Repository<Race> raceRepository = new RaceRepository();
        Repository<Motorcycle> motorcycleRepository = new MotorcycleRepository();
        Repository<Rider> riderRepository = new RiderRepository();
        ChampionshipController championshipController = new ChampionshipControllerImpl(riderRepository,motorcycleRepository,raceRepository);

        Engine engine = new EngineImpl(reader,writer,championshipController);

        engine.run();

    }
}
