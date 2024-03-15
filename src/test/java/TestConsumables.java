import gears.Consumables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestConsumables {
    private Consumables consumables;

    /**
     * Initialize the consumables before each test
     */
    @BeforeEach
    public void setUp() {
        consumables = new Consumables("Health Potion");
    }

    /**
     * Test to get the correct consumables type
     */
    @Test
    public void testConsumablesType() {
        assertEquals("Health Potion", consumables.getType());
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
