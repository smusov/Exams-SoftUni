package heroRepository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HeroRepository {
    private Map<String,Hero> data;

    public HeroRepository() {
        this.data = new LinkedHashMap<>();
    }

    public void add(Hero hero){
        this.data.put(hero.getName(),hero);
    }
    public void remove(String name){
        this.data.remove(name);
    }
    public Hero getHeroWithHighestStrength(){
        return this.data.values().stream().max(Comparator.comparingInt(e -> e.getItem().getStrength())).orElse(null);
    }
    public Hero getHeroWithHighestAgility(){
        return this.data.values().stream().max(Comparator.comparingInt(e -> e.getItem().getAgility())).orElse(null);
    }
    public Hero getHeroWithHighestIntelligence(){
        return this.data.values().stream().max(Comparator.comparingInt(e -> e.getItem().getIntelligence())).orElse(null);
    }
    public int getCount(){
        return this.data.size();
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        this.data.values().forEach(e->result.append(e.toString()).append(System.lineSeparator()));

        return result.toString().trim();
    }
}
