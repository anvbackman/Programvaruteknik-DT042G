import gears.Gear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class is used to test the Gear class
 * @author Andreas Backman
 */
public class TestGear {

    private Gear gear;
    private Gear gear2;

    /**
     * Initialize the gear before each test
     */
    @BeforeEach
    public void setUp() {
        gear = new Gear("Iron Sword", "weapons", 10, 20);
        gear2 = new Gear("Leather Armor", "armor", 5, 1);
    }

    /**
     * Test to get the correct gear type
     */
    @Test
    public void testGearType() {
        assertEquals("Iron Sword", gear.getName());
        assertEquals("Leather Armor", gear2.getName());
    }

    /**
     * Test to get the correct gear category
     */
    @Test
    public void testGearCategory() {
        assertEquals("weapons", gear.getGearCategory());
        assertEquals("armor", gear2.getGearCategory());
    }

    /**
     * Test to get the correct gear value
     */
    @Test
    public void testGearValue() {
        assertEquals(10, gear.getValue());
        assertEquals(5, gear2.getValue());
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
        gear.setName("Steel Sword");
        assertEquals("Steel Sword", gear.getName());
    }

    /**
     * Test to get the cost of the gear
     */
    @Test
    public void testGetCost() {
        assertEquals(20, gear.getCost());
    }

    /**
     * Test to set the cost of the gear
     */
    @Test
    public void testSetCost() {
        gear.setCost(30);
        assertEquals(30, gear.getCost());
    }

    /**
     * Test to set the gear category to null
     */
    @Test
    public void testGearNameIsSetToNull() {
        gear.setName(null);
        assertNull(gear.getName());
    }

    /**
     * Test to set the gear category to an empty string
     */
    @Test
    public void testSetEmptyGearName() {
        gear.setName("");
        assertEquals("", gear.getName());
    }

    /**
     * Test to set the gear value to zero
     */
    @Test
    public void testSetGearValueToZero() {
        gear.setValue(0);
        assertEquals(0, gear.getValue());
    }

    /**
     * Test to set the gear value to a negative number
     * This should be allowed for future implementations
     * due to having effects that can reduce the value of the gear
     */
    @Test
    public void testSetGearCostSetToNegative() {
        gear.setValue(-1);
        assertEquals(-1, gear.getValue());
    }
}
