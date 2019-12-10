package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.factories.MotorcycleFactory;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static motocrossWorldChampionship.common.ExceptionMessages.*;
import static motocrossWorldChampionship.common.OutputMessages.*;

public class ChampionshipControllerImpl implements ChampionshipController {

    private static final int MIN_PARTICIPANTS = 3;

    private Repository<Race> raceRepository;
    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Rider> riderRepository;

    public ChampionshipControllerImpl(Repository<Rider> riderRepository, Repository<Motorcycle> motorcycleRepository,
                                      Repository<Race> raceRepository) {

        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createRider(String riderName) {

        Rider rider = new RiderImpl(riderName);
        this.riderRepository.add(rider);

        return String.format(RIDER_CREATED,rider.getName());
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {

        Motorcycle motorcycle = MotorcycleFactory.createMotorcycle(type, model, horsePower);
        this.motorcycleRepository.add(motorcycle);

        return String.format(MOTORCYCLE_CREATED,motorcycle.getClass().getSimpleName(),motorcycle.getModel());
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {

        Rider rider = getRiderWithGivenName(riderName);
        Motorcycle motorcycle = getMotorcycleWithGivenModel(motorcycleModel);

        rider.addMotorcycle(motorcycle);

        return String.format(MOTORCYCLE_ADDED,riderName,motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {

        Race race = getRaceWithGivenName(raceName);
        Rider rider = getRiderWithGivenName(riderName);
        race.addRider(rider);

        return String.format(RIDER_ADDED,riderName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = getRaceWithGivenName(raceName);

        if (race.getRiders().size()<MIN_PARTICIPANTS){
            throw new IllegalArgumentException(String.format(RACE_INVALID,raceName,MIN_PARTICIPANTS));
        }

        List<Rider> bestRiders = race.getRiders()
                .stream()
                .sorted((e1, e2) ->
                        Double.compare(e2.getMotorcycle().calculateRacePoints(race.getLaps()),
                                e1.getMotorcycle().calculateRacePoints(race.getLaps()))).collect(Collectors.toList());

        this.raceRepository.remove(race);
        return getOutputMessage(bestRiders,raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name,laps);
        this.raceRepository.add(race);
        return String.format(RACE_CREATED,name);
    }

    private String getOutputMessage(List<Rider> bestRiders,String raceName) {

        return String.format(RIDER_FIRST_POSITION, bestRiders.get(0).getName() , raceName) +
                System.lineSeparator() +
                String.format(RIDER_SECOND_POSITION, bestRiders.get(1).getName(), raceName) +
                System.lineSeparator() +
                String.format(RIDER_THIRD_POSITION, bestRiders.get(2).getName(), raceName);
    }

    private Rider getRiderWithGivenName(String riderName) {
        Rider rider = this.riderRepository.getByName(riderName);
        if (rider==null){
            throw new NullPointerException(String.format(RIDER_NOT_FOUND,riderName));
        }
        return rider;
    }

    private Motorcycle getMotorcycleWithGivenModel(String motorcycleModel) {
        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);
        if (motorcycle==null){
            throw new NullPointerException(String.format(MOTORCYCLE_NOT_FOUND,motorcycleModel));
        }
        return motorcycle;
    }

    private Race getRaceWithGivenName(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race==null){
            throw new NullPointerException(String.format(RACE_NOT_FOUND,raceName));
        }
        return race;
    }
}
