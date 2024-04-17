package scenarios;

import character.Hero;
import creator.Mission;
import support.Constants;

import java.util.Scanner;

/**
 * The rest class is a scenario that contains the encounters for a rest.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 */
public class Rest implements Encounter {

    /**
     * The overarching mission for the scenario.
     */
    private final Mission mission;
    private final Hero hero;
    private final Scanner scanner;

    /**
     * Constructor for the rest class.
     * @param mission the mission for the rest.
     */
    public Rest(Mission mission, Hero hero, Scanner scanner) {
        this.mission = mission;
        this.hero = hero;
        this.scanner = scanner;
    }

    /**
     * Executes the encounters for the rest.
     */

    public void execute() {
        System.out.println("You take a rest and regain some health.");
        executeEncounter(mission.getDifficulty());
    }

    /**
     *  Executes the encounter for the rest.
     * @param difficulty the difficulty of the encounter, in this case it will still yield the same result, but it might be changed.
     */
    private void executeEncounter(String difficulty){
        switch (difficulty) {
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
        System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
    }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
    }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
    }



}
