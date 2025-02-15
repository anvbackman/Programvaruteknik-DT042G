import abilities.BaseAbility;
import abilities.Brutalize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for the abilities in the game.
 * @author Andreas Backman
 */
public class TestAbilities {

    private BaseAbility ability;

    /**
     * Set up the test environment
     */
    @BeforeEach
    public void setUp() {
        ability = new Brutalize();
    }

    /**
     * Test the ability's name
     * The name should be "Brutalize"
     */
    @Test
    public void testAbilityName() {
        assertEquals("Brutalize", ability.getName());
    }

    /**
     * Test the ability's cost
     * The cost of the ability should be 10
     */
    @Test
    public void testAbilityCost() {
        assertEquals(10, ability.getCost());
    }

    /**
     * Test the ability's number of targets
     * The ability should only target 1 target
     */
    @Test
    public void testTarget() {
        assertEquals(1, ability.getTargets());
    }

    /**
     * Test the ability's number of targets
     * The ability should not be null
     */
    @Test
    public void testAbilityNotNull() {
        assertNotNull("Ability should not be null", ability);
    }
}
