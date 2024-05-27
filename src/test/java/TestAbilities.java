import abilities.BaseAbility;
import abilities.Brutalize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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
        assertNotEquals("EldritchCrush", ability.getName());
    }

    /**
     * Test the ability's damage calculation
     * The damage should be between 1 and 10
     */
    @Test
    public void testAbilityExecute() {
        int damage = ability.execute(1, 1);
        assertTrue("Damage should be between 1 and 10", damage > 0 && damage <= 10);
        assertFalse("Damage should be between 1 and 10", damage < 1 || damage > 10);
    }

    /**
     * Test the ability's cost
     * The cost of the ability should be 10
     */
    @Test
    public void testAbilityCost() {
        assertEquals(10, ability.getCost());
        assertNotEquals(5, ability.getCost());
    }

    /**
     * Test the ability's number of targets
     * The ability should only target 1 target
     */
    @Test
    public void testTarget() {
        assertEquals(1, ability.getTargets());
        assertNotEquals(2, ability.getTargets());
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
