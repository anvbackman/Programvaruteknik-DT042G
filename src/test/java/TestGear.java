import gears.Gear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is used to test the Gear class
 * @author Andreas Backman
 */
public class TestGear {

    private Gear gear;

    /**
     * Initialize the gear before each test
     */
    @BeforeEach
    public void setUp() {
        gear = new Gear("Iron Sword", "weapons");
    }

    /**
     * Test to get the correct gear type
     */
    @Test
    public void testGearType() {
        assertEquals("Iron Sword", gear.getType());
    }

    /**
     * Test to get the correct gear category
     */
    @Test
    public void testGearCategory() {
        assertEquals("weapons", gear.getGearCategory());
    }

    /**
     * Test to get the correct gear value
     */
    @Test
    public void testGearValue() {
        assertEquals(10, gear.getValue());
    }

    /**
     * Test to set the gear value
     */
    @Test
    public void testSetValue() {
        gear.setValue(20);
        assertEquals(20, gear.getValue());
    }

    /**
     * Test to set the gear type
     */
    @Test
    public void testSetType() {
        gear.setType("Steel Sword");
        assertEquals("Steel Sword", gear.getType());
    }
}
