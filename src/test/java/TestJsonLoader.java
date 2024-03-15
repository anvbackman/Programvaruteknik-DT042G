import gears.JsonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        load = new JsonLoader("src/gearlist.json");
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
}
