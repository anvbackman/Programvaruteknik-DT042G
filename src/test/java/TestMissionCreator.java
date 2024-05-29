import creator.Mission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.Constants;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * This class is used to test the Mission class.
 * @author Emil Jönsson
 */
public class TestMissionCreator {

    private Mission mission;

    /**
     * Set up the mission before each test.
     */
    @BeforeEach
    public void setUp() {
        mission = new Mission(Constants.DIFFICULTY_HARD, 3);
        mission.generateMission();
    }

    /**
     * Tests if the mission length is positive.
     */
    @Test
    public void testMissionLengthIsPositive() {
        assertTrue(mission.getLength() > 0);
    }

    /**
     * Tests that the first fork is combat.
     */
    @Test
    public void testFirstForkIsCombat() {
        List<String> firstFork = mission.getNextFork();
        assertEquals(1, firstFork.size());
        assertEquals(Constants.MISSION_TYPE_COMBAT, firstFork.get(0));
    }

    /**
     * Tests that the last fork is a boss.
     */
    @Test
    public void testLastForkIsBoss() {
        List<String> lastFork = null;
        for (int i = 0; i < mission.getLength(); i++) {
            lastFork = mission.getNextFork();
        }
        assertEquals(1, lastFork.size());
        assertEquals(Constants.MISSION_TYPE_BOSS, lastFork.get(0));
    }

    /**
     * Tests that the next fork after the last is empty.
     */
    @Test
    public void testNextForkAfterLastIsEmpty() {
        for (int i = 0; i < mission.getLength(); i++) {
            mission.getNextFork();
        }
        assertTrue(mission.getNextFork().isEmpty());
    }

    /**
     * Tests if the assigned difficulty is returned correctly.
     */
    @Test
    public void testMissionDifficultyReturn() {
        String difficulty = Constants.DIFFICULTY_HARD;
        assertEquals(difficulty, mission.getDifficulty());
    }
}
