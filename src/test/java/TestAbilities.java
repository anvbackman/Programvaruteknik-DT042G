import abilities.BaseAbility;
import abilities.Brutalize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

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
     */
    @Test
    public void testAbilityName() {
        assertEquals("Brutalize", ability.getName());
        assertNotEquals("EldritchCrush", ability.getName());
    }

    /**
     * Test the ability's damage calculation
     */
    @Test
    public void testAbilityExecute() {
        int damage = ability.execute(1, 1);
        assertTrue(damage > 0 && damage <= 10, "Damage should be between 1 and 10");
        assertFalse(damage < 1 || damage > 10, "Damage should be between 1 and 10");
    }

    /**
     * Test the ability's number of targets
     */
    @Test
    public void testAbilityNotNull() {
        assertNotNull(ability, "Ability should not be null");
    }
}
