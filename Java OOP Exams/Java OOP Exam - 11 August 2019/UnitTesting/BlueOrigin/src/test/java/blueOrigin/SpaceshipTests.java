package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    private static final String INVALID_NAME = "        ";
    private static final int INVALID_CAPACITY = -3;

    private static final String NAME = "SpaceShipTest";
    private static final int CAPACITY = 3;

    private static final int EXPECTED_COUNT = 0;
    private static final int EXPECTED_COUNT_AFTER_ADD = 2;

    private static final String CHECK_NAME_INVALID_MESSAGE = "Name is not correct!";
    private static final String WRONG_COUNT_ASTRONAUTS = "Astronauts count is wrong!";
    private static final String WRONG_CAPACITY = "Capacity method return wrong value!";
    private static final String METHOD_ADD_NOT_WORKING_CORRECT = "Method add not working correct!";


    private Spaceship spaceship;

    @Test(expected = NullPointerException.class)
    public void initialiseInvalidSpaceShipName() {
        this.spaceship = new Spaceship(INVALID_NAME, CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initialiseInvalidSpaceShipCapacity() {
        this.spaceship = new Spaceship(NAME, INVALID_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void initialiseInvalidSpaceShipNameAndCapacity() {
        this.spaceship = new Spaceship(INVALID_NAME, INVALID_CAPACITY);
    }

    @Before
    public void initialiseSpaceShip() {
        this.spaceship = new Spaceship(NAME, CAPACITY);
    }

    @Test
    public void countAstronauts() {
        int capacity = this.spaceship.getCount();
        Assert.assertEquals(WRONG_COUNT_ASTRONAUTS,EXPECTED_COUNT, capacity);
    }

    @Test
    public void checkName() {
        String spaceshipName = this.spaceship.getName();
        Assert.assertEquals(CHECK_NAME_INVALID_MESSAGE,NAME, spaceshipName);
    }

    @Test
    public void checkCapacity() {
        int spaceshipCapacity = this.spaceship.getCapacity();
        Assert.assertEquals(WRONG_CAPACITY,CAPACITY, spaceshipCapacity);
    }

    @Test
    public void checkAddAstronauts() {
        this.spaceship.add(new Astronaut("Astronaut", 2.00));
        this.spaceship.add(new Astronaut("Second Astronaut", 2.00));

        int count = this.spaceship.getCount();

        Assert.assertEquals(METHOD_ADD_NOT_WORKING_CORRECT, EXPECTED_COUNT_AFTER_ADD, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAstronautWithSameNames() {
        this.spaceship.add(new Astronaut("Astronaut", 2.00));
        this.spaceship.add(new Astronaut("Astronaut", 2.00));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAstronautsOverCapacity(){
        this.spaceship.add(new Astronaut("Astronaut", 2.00));
        this.spaceship.add(new Astronaut("Second Astronaut", 2.00));
        this.spaceship.add(new Astronaut("Third Astronaut", 2.00));
        this.spaceship.add(new Astronaut("fourth Astronaut", 2.00));
    }

    @Test
    public void removeNonExistentAstronaut(){
        this.spaceship.add(new Astronaut("Astronaut", 2.00));
        boolean removed = this.spaceship.remove("Second Astronaut");

        Assert.assertFalse(removed);

    }

    @Test
    public void removeExistAstronaut(){
        this.spaceship.add(new Astronaut("Astronaut", 2.00));
        boolean removed = this.spaceship.remove("Astronaut");
        Assert.assertTrue(removed);
    }
}
