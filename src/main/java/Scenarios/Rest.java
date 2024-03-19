package Scenarios;
import support.Constants;
import creator.Mission;


import java.util.List;

/**
 * The rest class is a scenario that contains the encounters for a rest.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 */
public class Rest implements Encounter{

    /**
     * The overarching mission for the scenario.
     */
    private final Mission mission;

    /**
     * Constructor for the rest class.
     * @param mission the mission for the rest.
     */
    public Rest(Mission mission) {
        this.mission = mission;
    }

    /**
     * Executes the encounters for the rest.
     */

    public void execute() {
        System.out.println("You take a rest and regain some health.");
        List<String> encounters = mission.getNextFork();
        for (String encounter : encounters) {
            if (encounter.equals(Constants.MISSION_TYPE_REST)) {
                executeEncounter(mission.getDifficulty());
            }
        }
    }

    /**
     *  Executes the encounter for the rest.
     * @param encounter the difficulty of the encounter, in this case it will still yield the same result, but it might be changed.
     */
    private void executeEncounter(String encounter){
        switch (encounter) {
            case Constants.DIFFICULTY_EASY -> generateEncountersForMissionEasy();
            case Constants.DIFFICULTY_MEDIUM -> generateEncountersForMissionMedium();
            case Constants.DIFFICULTY_HARD -> generateEncountersForMissionHard();
            default -> System.out.println("No encounter");
        }
    }

    /**
     * Generates the encounters for the easy difficulty.
     */
    private void generateEncountersForMissionEasy() {
        int numEncounters = mission.getLength();
        for (int i = 0; i < numEncounters; i++) {
            System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
        }
    }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        int numEncounters = mission.getLength();
        for (int i = 0; i < numEncounters; i++) {
            System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
        }
    }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        int numEncounters = mission.getLength();
        for (int i = 0; i < numEncounters; i++) {
            System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
        }
    }



}
