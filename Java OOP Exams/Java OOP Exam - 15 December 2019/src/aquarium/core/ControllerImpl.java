package aquarium.core;

import aquarium.factories.AquariumFactory;
import aquarium.factories.DecorationFactory;
import aquarium.models.aquariums.Aquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository decorations;
    private Map<String, Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new LinkedHashMap<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {

        Aquarium aquarium = AquariumFactory.makeAquarium(aquariumType, aquariumName);
        validateAquarium(aquarium);

        this.aquariums.put(aquarium.getName(), aquarium);

        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {

        Decoration decoration = DecorationFactory.makeDecoration(type);
        validateDecoration(decoration);

        this.decorations.add(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {

        Decoration byType = this.decorations.findByType(decorationType);

        if (byType == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        this.aquariums.get(aquariumName).addDecoration(byType);
        this.decorations.remove(byType);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        Fish fish;
        String lifeAquariumType;

        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                lifeAquariumType = "FreshwaterAquarium";
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                lifeAquariumType = "SaltwaterAquarium";
                break;
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        String aquariumType = this.aquariums.get(aquariumName).getClass().getSimpleName();


        if (lifeAquariumType.equals(aquariumType)) {
            try {
                this.aquariums.get(aquariumName).addFish(fish);
            }catch (Exception e){
                return e.getMessage();
            }
            return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fish.getClass().getSimpleName(), aquariumName);
        } else {
            return SaltwaterAquarium;
        }
    }

    @Override
    public String feedFish(String aquariumName) {
        this.aquariums.get(aquariumName).feed();
        return String.format(FISH_FED, this.aquariums.get(aquariumName).getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {

        double fishesPrice = calculateFishPrice(this.aquariums.values());
        double decorationPrice = calculateDecorationPrice(this.aquariums.values());

        double sum = fishesPrice + decorationPrice;

        return String.format(VALUE_AQUARIUM, aquariumName, sum);
    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        this.aquariums.values().forEach(e -> sb.append(e.getInfo()).append(System.lineSeparator()));

        return sb.toString().trim();
    }

    private void validateAquarium(Aquarium aquarium) {
        if (aquarium==null){
            throw new IllegalArgumentException(INVALID_AQUARIUM_TYPE);
        }
    }

    private void validateDecoration(Decoration decoration) {
        if (decoration==null){
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
    }

    private double calculateDecorationPrice(Collection<Aquarium> aquariums) {
        return aquariums
                .stream()
                .mapToDouble(
                        e->e.getDecorations()
                                .stream()
                                .mapToDouble(Decoration::getPrice)
                                .sum())
                .sum();
    }

    private double calculateFishPrice(Collection<Aquarium> aquariums) {

        return aquariums
                .stream()
                .mapToDouble(
                        e->e.getFish()
                                .stream()
                                .mapToDouble(Fish::getPrice)
                                .sum())
                .sum();
    }
}
