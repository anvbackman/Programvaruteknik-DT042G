package Scenarios;

import support.Constants;
import support.Randomizer;
import creator.Mission;

import java.util.List;

/**
 * The social class is a scenario that contains the encounters for a social encounter.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 */
public class Social implements Encounter{

    /**
     * The overarching mission for the scenario.
     */
    private final Mission mission;

    /**
     * Constructor for the social class.
     * @param mission the mission for the social.
     */
    public Social(Mission mission) {
        this.mission = mission;
    }

    /**
     * Executes the encounters for the social.
     */
    public void execute() {
        List<String> encounters = mission.getNextFork();
        for (String encounter : encounters) {
            if (encounter.equals(Constants.MISSION_TYPE_SOCIAL)) {
                executeEncounter(mission.getDifficulty());
            }
        }
    }

    /**
     *  Executes the encounter for the social.
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
            int randomEncounter = Randomizer.rollD6(1);
            switch (randomEncounter) {
                case 1, 2, 3 -> System.out.println("""
                            You come across a trader, who is willing to trade with you, he looks at you with a smile, and offers you a deal.
                            
                            "Hello there, can I interest you in my wares?"
                            """);
                case 4, 5, 6 -> System.out.println("""
                            You notice in the corner of your eye, an injured guardsman from the village, he is lying on the ground, and he seems to be in pain.
                            As you approach him, he looks at you with a pained expression, and asks for your help.
                            """);
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
            int randomEncounter = Randomizer.rollD6(1);
            switch (randomEncounter) {
                case 1, 2, 3 ->
                        System.out.println("""
                                You meet a kobold who is holding up a small stand, he is selling various trinkets and baubles, and he looks at you with a smile.
                                unlike many of the other Kobolds you have met, this one seems to be friendly, and he offers you a deal.
                                """);
                case 4, 5, 6 ->
                        System.out.println("""
                                You come across a small group of survivors, they are huddled together, and they look at you with a mix of fear and hope.
                                They ask you for your help, and they offer you a reward if you help them.
                                """);

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
            int randomEncounter = Randomizer.rollD6(1);
            switch (randomEncounter) {
                case 1, 2, 3 -> System.out.println("""
                        You come across a group of bandits, they are not hostile, but they are blocking the path and tells you that you need to pay a toll to pass.
                        They look at you with a mix of greed and malice, and they offer you a deal.
                        """);
                case 4, 5, 6 -> System.out.println("""
                        You enter an abandoned shop, but as you search through the shelves, you hear a voice behind you.
                        You turn around and see a ghostly figure, who looks at you with a mix of sadness and anger.
                        "Thou hast to pay for thine items, or I shall curse thee!"
                        """);
                default -> System.out.println("You encounter nothing unusual.");
            }
        }
    }
}
