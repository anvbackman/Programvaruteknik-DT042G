import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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
        Assertions.assertTrue(statSheet.reRollSelected("Strength"));
        Assertions.assertEquals(ReRollAmount - 1, statSheet.getReRollAmount());
    }

    /**
     * Tests if an invalid stat is handled correctly.
     */
    @Test
    public void testInvalidStatReRoll() {
        Assertions.assertFalse(statSheet.reRollSelected("InvalidStat"));
    }

    /**
     * Tests if attempting to get an invalid stat returns -1.
     */
    @Test
    public void testGettingInvalidStat() {
        Assertions.assertEquals(-1, statSheet.getStat("InvalidStat"));
    }

    /**
     * Tests if setting a valid stat is updated correctly.
     */
    @Test
    public void testSetStat() {
        int expected = 10;
        Assertions.assertTrue(statSheet.setStat("Strength", expected));
        Assertions.assertEquals(expected, statSheet.getStat("Strength"));
    }

    /**
     * Tests if setting an invalid stat is handled correctly.
     */
    @Test
    public void testSetInvalidStat() {
        int expected = 10;
        Assertions.assertFalse(statSheet.setStat("InvalidStat", expected));
    }
}
