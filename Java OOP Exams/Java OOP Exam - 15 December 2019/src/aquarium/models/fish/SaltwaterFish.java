package aquarium.models.fish;

public class SaltwaterFish extends BaseFish {

    private static final int INCREASE_FISH_SIZE = 2;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public void eat() {
        this.increaseSize(INCREASE_FISH_SIZE);
    }
}
