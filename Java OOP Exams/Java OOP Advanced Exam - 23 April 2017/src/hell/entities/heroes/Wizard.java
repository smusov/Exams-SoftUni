package hell.entities.heroes;

public class Wizard extends HeroImpl {

    private static final int STRENGTH = 25;
    private static final int AGILITY = 100;
    private static final int INTELLIGENCE = 15;
    private static final int HIT_POINTS = 150;
    private static final int DAMAGE = 300;

    public Wizard(String name) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HIT_POINTS, DAMAGE);
    }
}
