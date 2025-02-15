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

    /** The current fork in the mission. */
    private int currentFork;

    /** The length of the mission. */
    private final int length;

    /** The difficulty of the mission. */
    private final String difficulty;

    /** The mission map. */
    private HashMap<Integer, List<String>> mission;

    /**
     * Constructor for the mission class.
     * @param missionDifficulty the difficulty of the mission.
     * @param missionLength the length of the mission.
     */
    public Mission(final String missionDifficulty, final int missionLength) {
        this.length = Randomizer.rollMissionLength(missionLength);
        this.difficulty = missionDifficulty;
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
        } else if (fork == length) {
            return List.of(Constants.MISSION_TYPE_BOSS);
        } else {
            List<String> options = new ArrayList<>(Constants.MISSION_TYPES_BASIC);
            Collections.shuffle(options);
            int forkAmount = Randomizer.rollMissionForkAmount();

            if (forkAmount < Constants.VALUE_MISSION_FORK_AMOUNT_MAX
                    && Randomizer.rollD20() <= Constants.VALUE_MINI_BOSS_ENCOUNTER_CHANCE) {
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
        currentFork++;
        List<String> fork = new ArrayList<>();
        if (currentFork <= length) {
            fork = mission.get(currentFork);
        }
        return fork;
    }

    /**
     * Shows the current state of the mission map in the console.
     */
    public final void showMission() {
        mission.forEach((key, value) -> {
            String colorModifier = Constants.COLOR_RESET;
            // Grey out forks that have already been passed.
            if (key < currentFork) {
                System.out.print(Constants.COLOR_GRAY);

                System.out.printf("\nFork " + key + ": ");
                value.forEach(type -> System.out.printf("[%s] ", type));

                System.out.print(Constants.COLOR_RESET);
                return;
            } else if (key == currentFork) {
                // Highlight the current fork.
                colorModifier = Constants.COLOR_YELLOW;
            }

            System.out.print(colorModifier);
            System.out.printf("\nFork " + key + ": ");

            for (String type : value) {
                // Highlight special mission types.
                String typeColor = colorModifier;
                if (type.equals(Constants.MISSION_TYPE_BOSS) || type.equals(Constants.MISSION_TYPE_MINI_BOSS)) {
                    typeColor = Constants.COLOR_RED;
                } else if (type.equals(Constants.MISSION_TYPE_MYSTERY)) {
                    typeColor = Constants.COLOR_PURPLE;
                }
                System.out.printf("%s[%s]%s ", typeColor, type, colorModifier);
            }
            System.out.print(Constants.COLOR_RESET);
        });
        System.out.println();
    }
}
