import gears.Consumables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is used to test the Consumables class
 * @author Andreas Backman
 */
public class TestConsumables {
    private Consumables consumables;

    /**
     * Initialize the consumables before each test
     */
    @BeforeEach
    public void setUp() {
        consumables = new Consumables("Health Potion", 1, 1);
    }

    /**
     * Test to get the correct consumables type
     */
    @Test
    public void testConsumablesType() {
        assertEquals("Health Potion", consumables.getName());
    }

    /**
     * Test to get the correct consumables value
     */
    @Test
    public void testConsumablesValue() {
        consumables.setValue(10);
        assertEquals(10, consumables.getValue());
    }
}
