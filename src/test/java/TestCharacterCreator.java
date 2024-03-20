import creator.CharacterCreator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import support.Constants;

/**
 * Tests the character creator class.
 * @author Emil Jönsson
 */
public class TestCharacterCreator {

    private CharacterCreator characterCreator = new CharacterCreator();

    /**
     * Tests if all classes can be selected correctly
     */
    @Test
    public void testValidClassSelect() {
        for (int i = 1; i <= Constants.CLASSES.size(); i++) {
            Assertions.assertTrue(characterCreator.selectClass(i));
        }
    }

    /**
     * Tests if invalid input is handled correctly.
     */
    @Test
    public void testInValidClassSelect() {
        Assertions.assertFalse(characterCreator.selectClass(Constants.CLASSES.size() + 1));
    }

    /**
     * Tests if the name can be selected correctly.
     */
    @Test
    public void testValidNameSelect() {
        Assertions.assertTrue(characterCreator.selectName("TestName"));
    }

    /**
     * Tests if an empty name is handled correctly.
     */
    @Test
    public void testEmptyNameSelect() {
        Assertions.assertFalse(characterCreator.selectName(""));
    }

    /**
     * Tests if a name that is too long is handled correctly.
     */
    @Test
    public void testLongNameSelect() {
        Assertions.assertFalse(characterCreator.selectName("ThisNameIsTooLongToBeAccepted"));
    }

    /**
     * Tests if a name that only contains spaces is handled correctly.
     */
    @Test
    public void testIfNameOnlyContainsSpaces() {
        Assertions.assertFalse(characterCreator.selectName("    "));
    }

    /**
     * Tests if valid stat re-rolls are handled correctly.
     */
    @Test
    public void testValidStatReRolls() {
        for (int i = 1; i < 6; i++) {
            // Creates new characterCreator instance for each test, ensuring that the re-roll amount is reset.
            characterCreator = new CharacterCreator();
            Assertions.assertFalse(characterCreator.reRollStatSheet(i));
        }
    }

    /**
     * Tests if invalid stat re-rolls are handled correctly.
     */
    @Test
    public void testInvalidStatReRolls() {
        characterCreator = new CharacterCreator();
        Assertions.assertFalse(characterCreator.reRollStatSheet(7));
    }

    /**
     * Tests if re-rolling can be performed intended amount of times per character instance.
     */
    @Test
    public void testStatRollsValidAmount() {
        characterCreator = new CharacterCreator();
        for (int i = 0; i < Constants.VALUE_MAX_STAT_REROLLS; i++) {
            characterCreator.reRollStatSheet(1);
        }
        Assertions.assertEquals(0, characterCreator.getReRollAmount());
    }

    /**
     * Tests if re-rolling stats with no re-rolls left is handled correctly.
     */
    @Test
    public void testStatRollsInvalidAmount() {
        characterCreator = new CharacterCreator();
        for (int i = 0; i < Constants.VALUE_MAX_STAT_REROLLS + 1; i++) {
            characterCreator.reRollStatSheet(1);
        }
        Assertions.assertEquals(0, characterCreator.getReRollAmount());
    }
}
