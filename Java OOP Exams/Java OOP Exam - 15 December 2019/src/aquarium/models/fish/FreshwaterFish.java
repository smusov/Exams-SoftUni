package aquarium.models.fish;

public class FreshwaterFish extends BaseFish {

    private static final int INCREASE_FISH_SIZE = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public void eat() {
        this.increaseSize(INCREASE_FISH_SIZE);
    }
}
