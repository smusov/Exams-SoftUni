package models.cards;

public class MagicCard extends BaseCard {

//    defaultDamagePoints - constant value equal to 5
//    defaultHealthPoints - constant value equal to 80

    private static final int DEFAULT_DAMAGE_POINTS = 5;
    private static final int DEFAULT_HEALTH_POINTS = 80;


    public MagicCard(String name) {
        super(name, DEFAULT_DAMAGE_POINTS, DEFAULT_HEALTH_POINTS);
    }
}
