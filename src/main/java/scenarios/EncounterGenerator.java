package scenarios;

import character.Hero;
import creator.Mission;
import support.Constants;

import java.util.Scanner;

/**
 * The encounter generator class is used to generate encounters for a mission.
 * @author Martin Roos Eriksson
 */
public class EncounterGenerator {

    /**
     * The overarching mission for the encounter generator.
     */
    private final Mission mission;
    private final Hero hero;
    private final Scanner scanner;

    /**
     * Constructor for the encounter generator class.
     * @param mission the mission for the encounter generator.
     */
    public EncounterGenerator(Mission mission, Scanner scanner, Hero hero) {
        this.mission = mission;
        this.hero = hero;
        this.scanner = scanner;
    }

    /**
     * Generates the encounters for the mission.
     * @return a list of encounters.
     */
    public Encounter generateEncounter(String option) {
        Encounter encounter;

        // Generate encounters based on the options for the current fork
        switch (option) {
            case Constants.MISSION_TYPE_SOCIAL -> encounter = new Social(mission, hero, scanner);
            case Constants.MISSION_TYPE_REST -> encounter = new Rest(mission, hero, scanner);
            case Constants.MISSION_TYPE_PUZZLE -> encounter = new Puzzle(mission, hero, scanner);
            default -> encounter = new Battle(mission, hero, scanner);
        }
        return encounter;
    }
}