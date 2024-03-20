package scenarios;

import creator.Mission;
import support.Constants;
import support.Randomizer;

import java.util.List;

/**
 * The puzzle class is a scenario that contains the encounters for a puzzle.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 * */
public class Puzzle implements Encounter{

    /**
     * The overarching mission for the scenario.
     */
    private final Mission mission;

    /**
     * Constructor for the puzzle class.
     * @param mission the mission for the puzzle.
     */
    public Puzzle(Mission mission) {
        this.mission = mission;
    }

    /**
     * Executes the encounters for the puzzle.
     */
    public void execute() {
        List<String> encounters = mission.getNextFork();
        for (String encounter : encounters) {
            if (encounter.equals(Constants.MISSION_TYPE_COMBAT)) {
                executeEncounter(mission.getDifficulty());
            }
        }
    }

    /**
     * Executes the encounter for the puzzle.
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
        int numEncounters = mission.getLength();
        for (int i = 0; i < numEncounters; i++) {
            int randomEncounter = Randomizer.rollD4(1);
            switch (randomEncounter) {
                case 1, 2 -> System.out.println("""
                        You see a locked door in front of you and a wishing well to your right
                        There is a faint inscription on the door that reads, if you wish to pass, the way will be revealed.
                        """); // the answer is to throw a coin in the well - will be implemented in the future when we have a player class
                case 3, 4 -> System.out.println("""
                        You meet an old hermit in the woods, he offers you a riddle, if you solve it, he will give you a reward.
                        The riddle is: I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?
                        """); // the answer is an echo
                default -> System.out.println("You encounter nothing unusual.");
            }
        }
    }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        int numEncounters = mission.getLength();
        for (int i = 0; i < numEncounters; i++) {
            int randomEncounter = Randomizer.rollD4(1);
            switch (randomEncounter) {
                case 1, 2 ->
                        System.out.println("""
                                    You come across a group of kobolds, they are not hostile, but they are blocking the path
                                    As you approach them, they start to speak in their own language, and you can see they are trying to communicate with you
                                    roll a wisdom saving throw to understand them""");
                case 3, 4 ->
                        System.out.println("""
                                     You can see a chest with a intricate lock on it, corresponding to letters in the common tongue, you can see the letters
                                    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
                                    You will need to solve the riddle to open the chest
                                    The riddle is: I am taken from a mine, and shut up in a wooden case, from which I am never released, and yet I am used by almost every person. What am I?"""); // answer is pencil
                default -> System.out.println("You encounter nothing unusual.");
            }
        }
    }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        int numEncounters = mission.getLength();
        for (int i = 0; i < numEncounters; i++) {
            int randomEncounter = Randomizer.rollD4(1);
            switch (randomEncounter) {
                case 1, 2 ->
                        System.out.println("You find safety in a small residence in the outskirts of town, as you begin to prepare for your next move, you hear a faint scratching at the door\n" +
                                "You can see the door is being pushed in by a group of zombies, roll a strength saving throw to hold the door shut");
                case 3, 4 ->
                        System.out.println("You see an abandoned construct, clad in silver and gold, it is a mechanical automaton, but it is not moving\n" +
                                "You can see a small inscription on the automaton, what is the answer to life, the universe, and everything?"); // the answer is 42
                default -> System.out.println("You encounter nothing unusual.");
            }
        }
    }
}


