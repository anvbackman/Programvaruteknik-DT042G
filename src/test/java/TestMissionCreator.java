import creator.Mission;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import support.Constants;

/**
 * This class is used to test the Mission class.
 * @author Emil Jönsson
 */
public class TestMissionCreator {

    /**
     * Tests if the assigned difficulty is returned correctly.
     */
    @Test
    public void testMissionDifficultyReturn() {
        String difficulty = Constants.DIFFICULTY_HARD;
        Mission mission = new Mission(difficulty, 3);
        Assertions.assertEquals(difficulty, mission.getDifficulty());
    }
}
