package creator;

import support.Constants;
import support.Randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * The mission class acts as a generator and container for mission data.
 * @author Emil Jönsson
 */
public class Mission {

    private int currentFork;
    private final int length;
    private final String difficulty;
    private HashMap<Integer, List<String>> mission;

    /**
     * Constructor for the mission class.
     * @param difficulty the difficulty of the mission.
     * @param missionLength the length of the mission.
     */
    public Mission(final String difficulty, final int missionLength) {
        this.length = Randomizer.rollMissionLength(missionLength);
        this.difficulty = difficulty;
        this.currentFork = 0;
    }

    /**
     * Generates a mission and saves it to a hashmap.
     */
    public final void generateMission() {
        mission = new HashMap<>();

        for (int i = 1; i <= length; i++) {
            mission.put(i, generateFork(i));
        }
        showMission();
    }

    /**
     * Generates a fork of the mission.
     * @param fork the fork number.
     * @return a list of mission types.
     */
    private List<String> generateFork(final int fork) {
        if (fork == 1) {
            return List.of(Constants.MISSION_TYPE_COMBAT);
        } else if (fork == length){
            return List.of(Constants.MISSION_TYPE_BOSS);
        } else {
            List <String> options = new ArrayList<>(Constants.MISSION_TYPES_BASIC);
            Collections.shuffle(options);
            int forkAmount = Randomizer.rollMissionForkAmount();

            if (forkAmount < Constants.VALUE_MISSION_FORK_AMOUNT_MAX &&
                    Randomizer.rollD20() <= Constants.VALUE_MINI_BOSS_ENCOUNTER_CHANCE) {
                options.add(forkAmount, Constants.MISSION_TYPE_MINI_BOSS);
                forkAmount++;
            }
            return options.subList(0, forkAmount);
        }
    }

    /**
     * Returns the difficulty of the mission.
     * @return the difficulty of the mission as string value.
     */
    public final String getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the length of the mission.
     * @return the length of the mission as integer value.
     */
    public final int getLength() {
        return length;
    }

    /**
     * Returns the next fork in the mission or an empty list if there are no more forks.
     * @return the next fork in the mission as a list of mission types.
     */
    public final List<String> getNextFork() {
        List <String> fork = new ArrayList<>();
        if (currentFork < length) {
            fork = mission.get(currentFork);
        }
        currentFork++;
        return fork;
    }

    /**
     * Shows the mission in the console.
     */
    public final void showMission() {
        mission.forEach((key, value) -> System.out.println("Fork " + key + ": " + value));
    }
}
