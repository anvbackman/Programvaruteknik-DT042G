import gears.Weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is used to test the Weapons class
 * @author Andreas Backman
 */
public class TestWeapons {
    private Weapons weapons;

    /**
     * Initialize the weapons before each test
     */
    @BeforeEach
    public void setUp() {
        weapons = new Weapons("Iron Sword", 1 ,1);
    }

    /**
     * Test to get the correct weapons type
     */
    @Test
    public void testWeaponsType() {
        assertEquals("Iron Sword", weapons.getName());
    }

    /**
     * Test to get the correct weapons value
     */
    @Test
    public void testWeaponsValue() {
        weapons.setValue(10);
        assertEquals(10, weapons.getValue());
    }
}
