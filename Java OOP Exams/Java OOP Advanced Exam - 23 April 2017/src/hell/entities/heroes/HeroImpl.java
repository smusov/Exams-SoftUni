package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class HeroImpl implements Hero {

    private String name;
    private long strength;
    private long agility;
    private long intelligence;
    private long hitPoints;
    private long damage;

    private HeroInventory heroInventory;

    protected HeroImpl(String name, long strength, long agility, long intelligence, long hitPoints, long damage) {

        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;

        this.heroInventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.heroInventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.heroInventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.heroInventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.heroInventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.heroInventory.getTotalDamageBonus();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Item> getItems() {

        Map<String, Item> items = new LinkedHashMap<>();

        Field[] commonItems = this.heroInventory.getClass().getFields();

        for (Field field : commonItems) {
            if (field.isAnnotationPresent(ItemCollection.class)) {
                field.setAccessible(true);
                try {
                    items = (Map<String, Item>) field.get(this.heroInventory);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return items.values();
    }

    @Override
    public void addItem(Item item) {
        this.heroInventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.heroInventory.addRecipeItem(recipe);
    }

}
