package hell.entities.heroes;

public class Barbarian extends HeroImpl {

    private static final long STRENGTH = 90;
    private static final long AGILITY = 25;
    private static final long INTELLIGENCE = 10;
    private static final long HIT_POINTS = 350;
    private static final long DAMAGE = 150;

    public Barbarian(String name) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HIT_POINTS, DAMAGE);
    }

}
