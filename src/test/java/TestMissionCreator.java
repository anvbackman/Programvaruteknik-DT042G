import creator.Mission;
import org.junit.Test;
import support.Constants;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        assertEquals(difficulty, mission.getDifficulty());
    }
}
