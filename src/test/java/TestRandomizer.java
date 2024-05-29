
import org.junit.jupiter.api.Test;
import support.Randomizer;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the Randomizer class in the game.
 * this class will test all dice and random number generation methods in the Randomizer class.
 * @author Martin Eriksson
 */
public class TestRandomizer {

    private Randomizer randomizer;

    /**
     * Test the rollD4 method in the Randomizer class
     * This method should return a random number between 1 and 4
     */
    @Test
    public void testRollD4() {
        randomizer = new Randomizer();
        int minValue = 1;
        int maxValue = 4;

        int result = randomizer.rollD4();
        System.out.println("Roll D4: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollD4 method in the Randomizer class with multiple rolls
     * This method should return a random number between 3 and 12
     */
    @Test
    public void testRollD4Multiple() {
        randomizer = new Randomizer();
        int minValue = 3;
        int maxValue = 12;

        int result = randomizer.rollD4(3);
        System.out.println("Roll D4 Multiple: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollD6 method in the Randomizer class
     * This method should return a random number between 1 and 6
     */
    @Test
    public void testRollD6() {
        randomizer = new Randomizer();
        int minValue = 1;
        int maxValue = 6;

        int result = randomizer.rollD6();
        System.out.println("Roll D6: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollD6 method in the Randomizer class with multiple rolls
     * This method should return a random number between 3 and 18
     */
    @Test
    public void testRollD6Multiple() {
        randomizer = new Randomizer();
        int minValue = 3;
        int maxValue = 18;

        int result = randomizer.rollD6(3);
        System.out.println("Roll D6 Multiple: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollD10 method in the Randomizer class
     * This method should return a random number between 1 and 10
     */
    @Test
    public void testRollD10() {
        randomizer = new Randomizer();
        int minValue = 1;
        int maxValue = 10;

        int result = randomizer.rollD10();
        System.out.println("Roll D10: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollD10 method in the Randomizer class with multiple rolls
     * This method should return a random number between 3 and 30
     */
    @Test
    public void testRollD10Multiple() {
        randomizer = new Randomizer();
        int minValue = 3;
        int maxValue = 30;

        int result = randomizer.rollD10(3);
        System.out.println("Roll D10 Multiple: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollD20 method in the Randomizer class
     * This method should return a random number between 1 and 20
     */
    @Test
    public void testRollD20() {
        randomizer = new Randomizer();
        int minValue = 1;
        int maxValue = 20;

        int result = randomizer.rollD20();
        System.out.println("Roll D20: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollD20 method in the Randomizer class with multiple rolls
     * This method should return a random number between 3 and 60
     */
    @Test
    public void testRollD20Multiple() {
        randomizer = new Randomizer();
        int minValue = 3;
        int maxValue = 60;

        int result = randomizer.rollD20(3);
        System.out.println("Roll D20 Multiple: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }

    /**
     * Test the rollStat method in the Randomizer class
     * This method should return a random number between 3 and 18
     */
    @Test
    public void testRollStat() {
        randomizer = new Randomizer();
        int minValue = 3;
        int maxValue = 18;

        int result = randomizer.rollStat();
        System.out.println("Roll Stat: " + result);
        assertTrue(result >= minValue && result <= maxValue);
    }




}
