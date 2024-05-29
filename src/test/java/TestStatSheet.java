import character.Hero;
import character.StatSheet;
import org.junit.jupiter.api.Test;
import support.Constants;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests the StatSheet class.
 * @author Emil Jönsson
 */
public class TestStatSheet {

    public static final int ReRollAmount = 3;
    private final StatSheet statSheet = new StatSheet();

    /**
     * Tests if a valid stat can be re-rolled.
     */
    @Test
    public void testReRollSelected() {
        assertTrue(statSheet.reRollSelected("Strength"));
        assertEquals(ReRollAmount - 1, statSheet.getReRollAmount());
    }

    /**
     * Tests if an invalid stat is handled correctly.
     */
    @Test
    public void testInvalidStatReRoll() {
        assertFalse(statSheet.reRollSelected("InvalidStat"));
    }

    /**
     * Tests if attempting to get an invalid stat returns -1.
     */
    @Test
    public void testGettingInvalidStat() {
        assertEquals(-1, statSheet.getStat("InvalidStat"));
    }

    /**
     * Tests if setting a valid stat is updated correctly.
     */
    @Test
    public void testSetStat() {
        int expected = 10;
        assertTrue(statSheet.setStat("Strength", expected));
        assertEquals(expected, statSheet.getStat("Strength"));
    }

    /**
     * Tests if setting a negative stat is handled correctly.
     */
    @Test
    public void testSetStatNegative() {
        int expected = -10;
        assertTrue(statSheet.setStat("Strength", expected));
        assertEquals(expected, statSheet.getStat("Strength"));
    }

    /**
     * Tests if setting an invalid stat is handled correctly.
     */
    @Test
    public void testSetInvalidStat() {
        int expected = 10;
        assertFalse(statSheet.setStat("InvalidStat", expected));
    }

    /**
     * Tests if the level up threshold is correct.
     */
    @Test
    public void testLevelUpThreshold() {
        assertEquals(Constants.VALUES_EXPERIENCE_PER_LEVEL.get(0), statSheet.calculateNextLevelExperience());
    }

    /**
     * Tests if the level up threshold correctly remains constant after leveling up beyond set values.
     */
    @Test
    public void testLevelUpThresholdExcess() {
        int excess;
        int expected;
        StatSheet testingSheet = new StatSheet();
        statSheet.setHero(new Hero(statSheet, "TestHero", "TestClass"));
        testingSheet.setHero(new Hero(testingSheet, "TestHero", "TestClass"));
        for (int i = 0; i < Constants.VALUES_EXPERIENCE_PER_LEVEL.size() + 1; i++) {
            statSheet.levelUp();
        }
        excess = statSheet.calculateNextLevelExperience();
        for (int i = 0; i < Constants.VALUES_EXPERIENCE_PER_LEVEL.size(); i++) {
            testingSheet.levelUp();
        }
        expected = testingSheet.calculateNextLevelExperience();

        assertEquals(expected, excess);
    }
}
