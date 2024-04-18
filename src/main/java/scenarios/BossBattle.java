package scenarios;

import character.Hero;
import creator.Mission;
import enemies.Boblin;
import enemies.Enemies;
import enemies.Goblin;
import enemies.Skara;
import enemies.Vorkath;
import support.Constants;
import support.Randomizer;

import java.util.List;
import java.util.Scanner;

/**
 * The battle class is a scenario that contains the encounters for a battle.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 */
public class BossBattle implements Encounter {

    /**
     * The overarching mission for the scenario.
     */
    private final Mission mission;
    private final Hero hero;
    private final Scanner scanner;
    private List<Enemies> enemies;

    /**
     * Constructor for the battle class.
     * @param mission the mission for the battle.
     */
    public BossBattle(Mission mission, Hero hero, Scanner scanner) {
        this.mission = mission;
        this.hero = hero;
        this.scanner = scanner;
    }

    /**
     * Executes the encounters for the battle.
     */
    public void execute() {
        executeEncounter(mission.getDifficulty());
    }

    /**
     * Executes the encounter for the battle.
     * @param encounter the difficulty of the encounter.
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
        System.out.println("You encounter a goblin boss, Dressed in rags with a red skin, he looks at you with a grin.");
        new Boblin(true); //might elaborate further here.

    }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        System.out.println("You encounter the blind Kobold, Skara, his clicking maw echoing in the dark, as he leaps out from the shadows!");
        new Skara(true);
    }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        System.out.println("You encounter Vorkath, the Devourer of Souls! appearing from smoke and shadow his glowing eyes look at you with malice!");
        new Vorkath(true);

    }

    public List<Enemies> getEnemies() {
        return enemies;
    }
}


