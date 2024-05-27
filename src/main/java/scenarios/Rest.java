package scenarios;

import character.Hero;
import creator.Mission;
import support.Constants;
import support.Randomizer;

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
        int Danger = Randomizer.rollD20(1);
        if (hero.getCharacterClass() == Constants.CLASS_RANGER) {
            Danger += 5;
        }
            System.out.println("You are a ranger, and you are more aware of your surroundings. +5 to rolls to avoid danger");
            if (Danger <= 5) {
                System.out.println("During your rest you are ambushed by a group of goblins!");
                System.out.println("You fight them off, but you are injured during the fight.");
                hero.adjustHealth(-Randomizer.rollD6(1));
            } else {
                System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
            }
            System.out.println("The rest of the night is peaceful, and you embark further on your journey.");
        }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        int Danger = Randomizer.rollD20(1);
        if (hero.getCharacterClass() == Constants.CLASS_RANGER) {
            Danger += 5;
            System.out.println("You are a ranger, and you are more aware of your surroundings. +5 to rolls to avoid danger");
        }
            if (Danger <= 5) {
                System.out.println(" During the night, someone seems to have stolen some of your supplies.");
                hero.removeConsumable(hero.getConsumables().get(Randomizer.rollD4(1) - 1));
                hero.addGold(-10);
            }
            if (Danger <= 10) {
                System.out.println("During your rest you are ambushed by a group of Kobolds!");
                System.out.println("You fight them off, but you are injured during the fight.");
                hero.adjustHealth(-Randomizer.rollD6(2));
            } else {
                System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
            }
            System.out.println("The rest of the night is peaceful, and you embark further on your journey.");
        }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        int Danger = Randomizer.rollD20(1);
        if(hero.getCharacterClass() == Constants.CLASS_RANGER){
            Danger += 5;
            System.out.println("You are a ranger, and you are more aware of your surroundings. +5 to rolls to avoid danger");
        }
        if(Danger <= 5){
            System.out.println(" During the night, one of the survivors in the village must have taken some of your money");
            hero.addGold(-10);
        }
        if(Danger <= 15){
            System.out.println("During your rest you are ambushed by a group of Zombies!");
            System.out.println("You fight them off, but you are injured during the fight.");
            hero.adjustHealth(-Randomizer.rollD6(3));
        } else {
            System.out.println("You encounter nothing unusual, and enjoy a peaceful rest.");
        }
        System.out.println("The rest of the night is peaceful, and you embark further on your journey.");
    }

}
