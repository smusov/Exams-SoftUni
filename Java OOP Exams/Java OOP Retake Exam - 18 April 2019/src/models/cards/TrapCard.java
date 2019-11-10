package models.cards;

public class TrapCard extends BaseCard{

//    defaultDamagePoints - constant value equal to 120
//    defaultHealthPoints - constant value equal to 5

    private static final int DEFAULT_DAMAGE_POINTS = 120;
    private static final int DEFAULT_HEALTH_POINTS = 5;

    public TrapCard(String name) {
        super(name, DEFAULT_DAMAGE_POINTS, DEFAULT_HEALTH_POINTS);
    }
}
