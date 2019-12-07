package hell.entities.heroes;

public class Assassin extends HeroImpl {

    private static final long STRENGTH = 25;
    private static final long AGILITY = 100;
    private static final long INTELLIGENCE = 15;
    private static final long HIT_POINTS = 150;
    private static final long DAMAGE = 300;

    public Assassin(String name) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HIT_POINTS, DAMAGE);
    }
}
