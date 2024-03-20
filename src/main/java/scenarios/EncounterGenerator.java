package scenarios;
import java.util.ArrayList;
import java.util.List;
import creator.Mission;
import support.Constants;

/**
 * The encounter generator class is used to generate encounters for a mission.
 * @author Martin Roos Eriksson
 */
public class EncounterGenerator {

    /**
     * The overarching mission for the encounter generator.
     */
    private final Mission mission;

    /**
     * Constructor for the encounter generator class.
     * @param mission the mission for the encounter generator.
     */
    public EncounterGenerator(Mission mission) {
        this.mission = mission;
    }

    /**
     * Generates the encounters for the mission.
     * @return a list of encounters.
     */
    public List<Encounter> generateEncounters() {
        List<Encounter> encounters = new ArrayList<>();

        // Get the options for the current fork
        List<String> forkOptions = mission.getNextFork();

        // Generate encounters based on the options for the current fork
        for (String option : forkOptions) {
            switch (option) {
                case Constants.MISSION_TYPE_COMBAT -> encounters.add(new Battle(mission));
                case Constants.MISSION_TYPE_SOCIAL -> encounters.add(new Social(mission));
                case Constants.MISSION_TYPE_REST -> encounters.add(new Rest(mission));
                case Constants.MISSION_TYPE_MYSTERY -> encounters.add(new Puzzle(mission));
                default -> {
                }
            }
        }

        return encounters;
    }
}