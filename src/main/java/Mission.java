import support.Constants;
import support.Randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Mission {

    private int currentFork;
    private final int length;
    private final int difficulty;

    public Mission(final int difficulty, final int missionLength) {
        this.length = Randomizer.rollMissionLength(missionLength);
        this.difficulty = difficulty;
        this.currentFork = 0;
    }

    public final void generateMission() {
        HashMap<Integer, List<String>> mission = new HashMap<>();

        for (int i = 1; i <= length; i++) {
            mission.put(i, generateFork(i));
        }
        mission.forEach((key, value) -> System.out.println("Fork " + key + ": " + value));
    }

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

    public final int getDifficulty() {
        return difficulty;
    }
}
