import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
        int difficulty = 1;
        Mission mission = new Mission(difficulty, 3);
        Assertions.assertEquals(difficulty, mission.getDifficulty());
    }
}
