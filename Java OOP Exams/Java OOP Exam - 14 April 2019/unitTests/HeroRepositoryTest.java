package heroRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTest {

    private HeroRepository repository;
    private Hero validHero;
    private Hero invalidHeroStrength;
    private Hero invalidHeroAgility;
    private Hero invalidHeroInteligence;

    @Before
    public void initialiseObjects(){
        this.repository = new HeroRepository();
        this.validHero = new Hero("Test",42,new Item(100,100,100));
        this.invalidHeroStrength = new Hero("Invalid",-1,new Item(-1,100,100));
        this.invalidHeroAgility = new Hero("Invalid",-1,new Item(100,-1,100));
        this.invalidHeroInteligence = new Hero("Invalid",-1,new Item(100,100,-1));
    }

    @Test
    public void getCountShouldReturnZero(){
        assertEquals(0,this.repository.getCount());
    }

    @Test
    public void addHero(){
        this.repository.add(validHero);
        int count = this.repository.getCount();
        assertEquals(1,count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addHeroShouldThrowExceptionIfHeroExist(){
        this.repository.add(validHero);
        this.repository.add(validHero);
    }

    @Test
    public void removeHero(){
        this.repository.add(validHero);
        this.repository.add(invalidHeroStrength);
        this.repository.remove("Invalid");
        assertEquals(1,this.repository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void removeHeroShouldThrowExceptionIfHeroNotExist(){
        this.repository.add(validHero);
        this.repository.remove("Invalid");
    }
    @Test
    public void getHeroWithHighestAgilityShouldReturnCorrectHero(){
        this.repository.add(validHero);
        assertEquals(validHero,this.repository.getHeroWithHighestAgility());
    }

    @Test(expected = NullPointerException.class)
    public void getHeroWithHighestAgilityShouldThrowExceptionIfNotHeroWithHighestAgility(){
        this.repository.add(invalidHeroAgility);
        this.repository.getHeroWithHighestAgility();
    }

    @Test
    public void getHeroWithHighestIntelligenceShouldReturnCorrectHero(){
        this.repository.add(validHero);
        assertEquals(validHero,this.repository.getHeroWithHighestIntelligence());
    }

    @Test(expected = NullPointerException.class)
    public void getHeroWithHighestIntelligenceShouldThrowExceptionIfNotHeroWithHighestAgility(){
        this.repository.add(invalidHeroInteligence);
        this.repository.getHeroWithHighestIntelligence();
    }
    @Test
    public void getHeroWithHighestStrengthShouldReturnCorrectHero(){
        this.repository.add(validHero);
        assertEquals(validHero,this.repository.getHeroWithHighestStrength());
    }

    @Test(expected = NullPointerException.class)
    public void getHeroWithHighestStrengthShouldThrowExceptionIfNotHeroWithHighestAgility(){
        this.repository.add(invalidHeroStrength);
        this.repository.getHeroWithHighestStrength();
    }

    @Test
    public void toStringShouldReturnCorrectString(){
        this.repository.add(validHero);
        String expected =  String.format("Hero: %s – %d%n" +
                        "  *  Strength: %d%n" +
                        "  *  Agility: %d%n" +
                        "  *  Intelligence: %d%n", validHero.getName(), validHero.getLevel(),
                100, 100, 100);

        assertEquals(expected,this.repository.toString());
    }
}