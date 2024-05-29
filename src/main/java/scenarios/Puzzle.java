package scenarios;

import character.Hero;
import creator.Mission;
import support.Constants;
import support.Output;
import support.Randomizer;
import support.Validation;

import java.util.Scanner;

/**
 * The puzzle class is a scenario that contains the encounters for a puzzle.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 * */
public class Puzzle implements Encounter {

    /**
     * The overarching mission for the scenario.
     */
    private final Mission mission;
    private Hero hero;
    private Scanner scanner;

    /**
     * Constructor for the puzzle class.
     *
     * @param mission the mission for the puzzle.
     */
    public Puzzle(final Mission mission, final Hero hero, final Scanner scanner) {
        this.mission = mission;
        this.hero = hero;
        this.scanner = scanner;
    }

    /**
     * Executes the encounters for the puzzle.
     */
    public void execute() {
        executeEncounter(mission.getDifficulty());
    }

    /**
     * Executes the encounter for the puzzle.
     *
     * @param difficulty the difficulty of the encounter.
     */
    private void executeEncounter(final String difficulty) {
        switch (difficulty) {
            case Constants.DIFFICULTY_EASY -> generateEncountersForMissionEasy();
            case Constants.DIFFICULTY_MEDIUM -> generateEncountersForMissionMedium();
            case Constants.DIFFICULTY_HARD -> generateEncountersForMissionHard();
            default -> System.out.println("No encounter");
        }
    }

    /**
     * Generates the encounters for the easy difficulty. this is a puzzle with a wishing well
     */
    private final Runnable wellPuzzle = () -> {
        boolean proceed = false;
        System.out.println("""
                You see a locked door in front of you and a wishing well to your right
                There is a faint inscription on the door that reads, if you wish to pass, the way will be revealed.
                """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Wish for the way to be revealed");
            System.out.println("2. Melon? Melon!");
            System.out.println("3. Throw a coin in the well");
            System.out.println("4. Leave");
            if(hero.getCharacterClass() == Constants.CLASS_BARD){
                System.out.println("5. Sing a song to the well");
            }
        }
        switch (Validation.validateInput(scanner.nextLine())) {
            case 1 -> {
                System.out.println("You wish for the way to be revealed, but nothing happens.");
                System.out.println("You attempt to find another way, and waste valuable time.");
                int dmg = Randomizer.rollD6(1);
                hero.adjustHealth(-dmg);
                System.out.printf("You take %d damage from the alternate path.\n", dmg);
            }
            case 2 -> {
                System.out.println("You say 'melon? melon!' but nothing happens.");
                System.out.println("You attempt to find another way, and waste valuable time... and you feel slightly judged.");
                int dmg = Randomizer.rollD6(1);
                hero.adjustHealth(-dmg);
                System.out.printf("You take %d damage from the judgemental silence.\n", dmg);
            }
            case 3 -> {
                System.out.println("You throw a coin in the well, and the door opens.");
                System.out.println("You proceed through the door.");
                int heal = Randomizer.rollD6(2);
                hero.adjustHealth(heal);
                System.out.printf("You heal %d as a gift from the Well spirit..\n", heal);
                proceed = true;
            }
            case 4 -> {
                System.out.println("You decide to leave, and find another way.");
                int dmg = Randomizer.rollD6(1);
                hero.adjustHealth(-dmg);
                System.out.printf("You take %d damage from the alternate path.\n", dmg);
                proceed = true;
            }
            case 5 -> {
                System.out.println("You sing a song to the well, and the well responds in harmony, as glittering gold erupts from the well.");
                System.out.println("The well remains silent, but a grateful presence opens the door");
                int gold = Randomizer.rollD6(5);
                hero.addGold(gold);
                System.out.printf("You find %d gold from the well, and proceed through the door.\n", gold);
                proceed = true;
            }
        }
    };

    /**
     * Generates the encounters for the easy difficulty, this is a hermit encounter
     */
    private final Runnable hermitEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                You meet an old hermit in the woods, he offers you a riddle, if you solve it, he will give you a reward.
                The riddle is: I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?
                """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. A Senile old man?");
            System.out.println("2. What is in my pocket?");
            System.out.println("3. An Echo?");
            System.out.println("4. A shadow?");
            if (hero.getCharacterClass().equals(Constants.CLASS_WIZARD)) {
                System.out.println("5. Answer with ease, and tell him a riddle of your own.");
            }
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    System.out.println("Your answer is incorrect, the hermit looks at you with a sad smile.");
                    System.out.println("You feel a sense of loss...");
                    int dmg = Randomizer.rollD6(1);
                    hero.adjustHealth(-dmg);
                    System.out.printf("You take %d psychic damage from the hermit's disappointing gaze.\n", dmg);
                    proceed = true;
                }
                case 2 -> {
                    System.out.println("Your answer is not really an answer... and in the confusion and embarrassment, you feel a sense of loss as the Hermit leaves...");
                    proceed = true;
                }
                case 3 -> {
                    System.out.println("Your answer is correct, the hermit looks at you with a smile.");
                    System.out.println("You feel a sense of gain...");
                    int heal = Randomizer.rollD6(4);
                    hero.adjustHealth(heal);
                    System.out.printf("You heal %d from the hermit's approval.\n", heal);
                    System.out.println("The hermit gives you a small token of his appreciation before he leaves...");
                    int gold = Randomizer.rollD6(2);
                    hero.addGold(gold);
                    proceed = true;
                }
                case 4 -> {
                    System.out.println("Your answer is incorrect... but at least you tried... that is what matters.");
                    System.out.println("The hermit leaves, and nothing is gained or lost.");
                    proceed = true;
                }
                case 5 -> {
                    System.out.println("Your answer is correct, the hermit looks at you with a smile, as he ponders your riddle)");
                    System.out.println("You feel a sense of gain...");
                    int heal = Randomizer.rollD6(4);
                    hero.adjustHealth(heal);
                    System.out.printf("You heal %d from the hermit's approval.\n", heal);
                    System.out.println("The hermit gives you a small token of his appreciation before he leaves... scratching his head over your riddle.");
                    int gold = Randomizer.rollD6(6);
                    hero.addGold(gold);
                    System.out.printf("You find %d gold from the hermit, and proceed on your journey.\n", gold);
                    proceed = true;
                }
            }
        }
    };

    /**
     * Generates the encounters for the medium difficulty.
     */
    private final Runnable KoboldEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                You come across a group of kobolds, they are not hostile, but they are blocking the path
                As you approach them, they start to speak in their own language, and you can see they are trying to communicate with you
                roll a wisdom saving throw to understand them
                """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Attempt to communicate with them (Wisdom saving throw)");
            System.out.println("2. Attack them");
            System.out.println("3. Leave");
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    int bonus = ((hero.getStats().getStat(Constants.STAT_WISDOM) - 10) / 2);
                    int dc = Randomizer.rollD20(1);
                    if(dc >= 10 + bonus){
                        System.out.println("You attempt to communicate with the kobolds, and you are able to understand them.");
                        System.out.println("They are defecting from their tribe, and they offer you a reward for your help clearing out their old tribe.");
                        int gold = Randomizer.rollD6(2);
                        hero.addGold(gold);
                        System.out.printf("You recieve %d gold from the kobolds, and proceed on your journey.\n", gold);
                    } else {
                        System.out.println("You attempt to communicate with the kobolds, but you are unable to understand them.");
                        System.out.println("You feel a sense of loss...");
                        int dmg = Randomizer.rollD6(1);
                        hero.adjustHealth(-dmg);
                        System.out.printf("You take %d psychic damage from the kobolds' confusing language.\n", dmg);
                    }
                }
                case 2 -> {
                    System.out.println("You attack the kobolds, and they retaliate.");
                    System.out.println("Although you defeat them with ease, you are injured in the process.");
                    int dmg = Randomizer.rollD6(2);
                    hero.adjustHealth(-dmg);
                    System.out.printf("You take %d damage from the kobolds' retaliation.\n", dmg);
                    proceed = true;
                }
                case 3 -> {
                    System.out.println("You decide to ignore them and leave, finding another way.");
                    proceed = true;
                }
            }
        }
    };

    /**
     * Generates the encounters for the medium difficulty.
     */
    private final Runnable ChestEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                You can see a chest with a intricate lock on it, corresponding to letters in the common tongue.
                You will need to solve the riddle to open the chest
                """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Attempt to open the chest (Intelligence saving throw)");
            System.out.println("2. Leave");
            if (hero.getCharacterClass().equals(Constants.CLASS_ROGUE)) {
                System.out.println("3. You have no time for riddles, so you attempt to pick the lock instead");
            }


            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    int bonus = ((hero.getStats().getStat(Constants.STAT_INTELLIGENCE) - 10) / 2);
                    int dc = Randomizer.rollD20(1);
                    if (dc >= 10 + bonus) {
                        System.out.println("You attempt to open the chest, and you are able to solve the riddle.");
                        System.out.println("The chest opens, and you find a small token of the previous owner.");
                        int gold = Randomizer.rollD6(2);
                        hero.addGold(gold);
                        System.out.printf("You recieve %d gold from the chest, and proceed on your journey.\n", gold);
                    } else {
                        System.out.println("You attempt to open the chest, but you are unable to solve the riddle.");
                        System.out.println("You feel a sense of loss...");
                        int dmg = Randomizer.rollD6(1);
                        hero.adjustHealth(-dmg);
                        System.out.printf("You take %d psychic damage from the chest's confusing riddle.\n", dmg);
                    }
                }
                case 2 -> {
                    System.out.println("You decide to ignore the chest and leave, finding another way.");
                    proceed = true;
                }
                case 3 -> {
                    System.out.println("With your nimble fingers and skill with locks, you attempt to pick the lock.");
                    System.out.println("You are able to open the chest, and you find a small token of the previous owner.");
                    int gold = Randomizer.rollD6(2);
                    System.out.printf("You recieve %d gold from the chest, and proceed on your journey.\n", gold);
                    hero.addGold(gold);
                    proceed = true;
                }
            }
        }
    };

    /**
     * Generates the encounters for the hard difficulty.
     */
    private final Runnable ZombieEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                You find safety in a small residence in the outskirts of town, as you begin to prepare for your next move, you hear a faint scratching at the door
                You can see the door is being pushed in by a group of zombies, roll a strength saving throw to hold the door shut
                """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Attempt to hold the door shut (Strength saving throw)");
            System.out.println("2. Attempt to barricade the door");
            System.out.println("3. Leave");
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    int bonus = ((hero.getStats().getStat(Constants.STAT_STRENGTH) - 10) / 2);
                    int dc = Randomizer.rollD20(1);
                    if (dc >= 10 + bonus) {
                        System.out.println("You attempt to hold the door shut, and you are able to keep the zombies out.");
                        System.out.println("The zombies eventually give up, and you are able to rest for the night.");
                        int heal = Randomizer.rollD6(2);
                        hero.adjustHealth(heal);
                        System.out.printf("You heal %d from the night's rest.\n", heal);
                        proceed = true;
                    } else {
                        System.out.println("You attempt to hold the door shut, but the zombies are too strong.");
                        System.out.println("You feel a sense of loss...");
                        int dmg = Randomizer.rollD6(2);
                        hero.adjustHealth(-dmg);
                        System.out.printf("You take %d damage from the zombies' attack.\n", dmg);
                    }
                }
                case 2 -> {
                    System.out.println("You attempt to barricade the door, but the zombies are too strong.");
                    System.out.println("You face the zombies head on, and you are injured in the process.");
                    int dmg = Randomizer.rollD6(2);
                    hero.adjustHealth(-dmg);
                    System.out.printf("You take %d damage from the zombies' attack.\n", dmg);
                    proceed = true;
                }
                case 3 -> {
                    System.out.println("You decide to leave, and find another way.");
                    proceed = true;
                }
            }
        }
    };

    /**
     * Generates the encounters for the hard difficulty.
     */
    private final Runnable AutomatonEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                You see an abandoned construct, clad in silver and gold, it is a mechanical automaton, but it is not moving
                You can see a small inscription on the automaton, what is the answer to life, the universe, and everything?
                """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. That's a very good question, and one the clerics has tried to answer for centuries...");
            System.out.println("2. Take no survivors?");
            System.out.println("3. 74");
            System.out.println("4. 42");
            if(hero.getCharacterClass().equals(Constants.CLASS_MONK)){
                System.out.println("5. During your meditation, you have found the answer to the automaton's question, explaining the abstract thoughts in a mosaic of the astral planes.");
            }
            switch (Validation.validateInput(scanner.nextLine())) {

                case 1 -> {
                    System.out.println("the automaton remains still...");
                    proceed = true;
                }
                case 2 -> {
                    System.out.println("The Automaton rumble to life, and targets you.");
                    System.out.println("You attack, but against its metal body, you are unable to do any damage.");
                    int dmg = Randomizer.rollD6(1);
                    hero.adjustHealth(-dmg);
                    System.out.printf("You take %d piercing damage from the automaton's weapons before you manage to flee.\n", dmg);
                    proceed = true;
                }
                case 3 -> {
                    System.out.println("The automaton remains still.");
                    proceed = true;
                }
                case 4 -> {
                    System.out.println("You answer 42, and the automaton comes to life.");
                    System.out.println("The automaton gives you a small token of its appreciation before it leaves...");
                    int gold = Randomizer.rollD6(4);
                    hero.addGold(gold);
                    System.out.printf("You recieve %d gold from the automaton, and proceed on your journey.\n", gold);
                    proceed = true;
                }
                case 5 -> {
                    System.out.println("Your answer does not seem to be what the Automaton was looking for...");
                    int heal = Randomizer.rollD6(12);
                    hero.adjustHealth(heal);
                    System.out.printf("But such a spiritual discovery is not without reward, and you are healed for %d.\n", heal);
                    proceed = true;
                }
            }
        }
    };



    /**
     * Generates the encounters for the easy difficulty.
     */
    private void generateEncountersForMissionEasy() {
        int randomEncounter = Randomizer.rollD4();
        switch (randomEncounter) {
            case 1, 2 -> {
                wellPuzzle.run();
            }
            case 3, 4 ->{
                hermitEncounter.run();
            }
            default -> System.out.println("You encounter nothing unusual.");
        }
    }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        int randomEncounter = Randomizer.rollD4();
        switch (randomEncounter) {
            case 1, 2 ->
                    KoboldEncounter.run();
            case 3, 4 ->
                    ChestEncounter.run();
            default -> System.out.println("You encounter nothing unusual.");
        }
    }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        int randomEncounter = Randomizer.rollD4();
        switch (randomEncounter) {
            case 1, 2 ->
                    ZombieEncounter.run();
            case 3, 4 ->
                    AutomatonEncounter.run();
            default -> System.out.println("You encounter nothing unusual.");
        }
    }
}


