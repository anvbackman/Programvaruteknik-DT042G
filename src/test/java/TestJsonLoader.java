import gears.JsonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class is used to test the JsonLoader class
 * @author Andreas Backman
 */
public class TestJsonLoader {
    JsonLoader load;

    /**
     * Initialize the JsonLoader before each test
     */
    @BeforeEach
    public void setUp() {
        load = new JsonLoader(Objects.requireNonNull(getClass().getResource("/gearlist.json")).getPath());
    }

    /**
     * Test to load the JSON file
     */
    @Test
    public void testLoadJSON() {
        assertNotNull(load, "Should not be null");
    }

    /**
     * Test to get the value of a specific weapon
     */
    @Test
    public void testGetValue() {
        assertEquals(5, load.getValue("weapons", "Scrappy Sword"));
    }

    /**
     * Test to get the types of a specific category
     */
    @Test
    public void testGetTypes() {
        assertTrue(load.getTypes("weapons").contains("Scrappy Sword"));
    }

    /**
     * Test to get the categories
     */
    @Test
    public void testGetCategories() {
        assertTrue(load.getCategories().contains("weapons"));
    }

    /**
     * Test to get the cost of a specific gear
     */
    @Test
    public void testGetCost() {
        assertEquals(10, load.getCost("weapons", "Scrappy Sword"));
    }
}
