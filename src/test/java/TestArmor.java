import gears.Armor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is used to test the Armor class
 * @author Andreas Backman
 */
public class TestArmor {
    private Armor armor;

    /**
     * Initialize the armor before each test
     */
    @BeforeEach
    public void setUp() {
        armor = new Armor("Iron Armor", 1, 1);
    }

    /**
     * Test to get the correct armor type
     */
    @Test
    public void testArmorType() {
        assertEquals("Iron Armor", armor.getName());
    }

    /**
     * Test to get the correct armor value
     */
    @Test
    public void testArmorValue() {
        armor.setValue(10);
        assertEquals(10, armor.getValue());
    }
}
