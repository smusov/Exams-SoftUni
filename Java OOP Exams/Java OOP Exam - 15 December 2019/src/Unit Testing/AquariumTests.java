
import aquarium.models.aquariums.Aquarium;
import aquarium.models.fish.Fish;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    private Aquarium aquarium;

    @Before
    public void initialiseObjects(){
        this.aquarium = new Aquarium("Test",1);
    }

    @Test
    public void getNameShouldReturnCorrectName(){
        Assert.assertEquals("Test", this.aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void initialiseInvalidCapacity(){
        this.aquarium = new Aquarium("asd",-50);
    }

    @Test(expected = NullPointerException.class)
    public void initialiseInvalidName(){
        this.aquarium = new Aquarium("       ",50);
    }

    @Test
    public void getCapacityShouldReturnCorrectValue(){
        Assert.assertEquals(1,this.aquarium.getCapacity());
    }

    @Test
    public void getCountShouldReturnZero(){
        Assert.assertEquals(0,this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFishShouldThrowException(){
        this.aquarium.add(new Fish("asd"));
        this.aquarium.add(new Fish("bsa"));
    }
    @Test
    public void addFishShouldAdd(){
        this.aquarium.add(new Fish("asd"));
        Assert.assertEquals(1,this.aquarium.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowException(){
        this.aquarium.add(new Fish("daw"));
        this.aquarium.remove("asd");
    }
    @Test
    public void removeShouldRemoveFish(){
        this.aquarium.add(new Fish("asd"));
        this.aquarium.remove("asd");
        Assert.assertEquals(0,this.aquarium.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void sellFishShouldThrowException(){
        this.aquarium.add(new Fish("Asd"));
        this.aquarium.sellFish("bas");
    }
    @Test
    public void sellShouldSellFish(){
        Fish fish = new Fish("asd");
        this.aquarium.add(fish);
        Fish fish1 = this.aquarium.sellFish("asd");

        Assert.assertEquals(fish,fish1);
    }
    @Test
    public void sellShouldSellFishShouldSetFalse(){
        Fish fish = new Fish("asd");
        this.aquarium.add(fish);
        Fish fish1 = this.aquarium.sellFish("asd");

        Assert.assertFalse(fish1.isAvailable());
    }

    @Test
    public void report(){
        this.aquarium.add(new Fish("asd"));
        Assert.assertEquals("Fish available at Test: asd",this.aquarium.report());
    }
}

