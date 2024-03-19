import character.Hero;
import creator.CharacterCreator;
import creator.Mission;
import support.Constants;
import support.Randomizer;
import support.Validation;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The game engine class. Contains methods that ensures the game can progress.
 * @author Emil Jönsson
 */
public class GameEngine {

    private final Scanner scanner;
    private Mission mission;
    private Hero hero;

    /**
     * Constructor for the game engine class.
     */
    public GameEngine() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initializes the game engine.
     */
    public void init() {
        System.out.println("Game Engine initialized.");
        CharacterCreator characterCreator = new CharacterCreator();

        int input;
        boolean proceed = false;

        while (!proceed) {
            System.out.println("1. Create a character");
            System.out.println("2. Exit");
            input = Validation.validateInput(scanner.nextLine());
            switch (input) {
                case 1 -> {
                    this.hero = characterCreator.createCharacter();
                    proceed = true;
                }
                case 2 -> {
                    return;
                }
                default -> System.out.printf("%sInvalid choice.%s\n",
                        Constants.COLOR_RED, Constants.COLOR_RESET);
            }
        }
        promptContinue();
        promptMissionCreation();
        while (!promptMissionSelect()) {
            // Do nothing
        }
        // TODO Implement mission complete logic here
    }

    /**
     * Prompts the user to continue, preventing the game from progressing too fast.
     */
    private void promptContinue() {
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    /**
     * Prompts the user to select mission length and difficulty.
     */
    private void promptMissionCreation() {
        int input;
        boolean proceed = false;

        int missionLength = 0;
        String missionDifficulty = "";

        while (!proceed) {
            System.out.println("Choose a mission length:");
            System.out.println("1. Short");
            System.out.println("2. Medium");
            System.out.println("3. Long");
            System.out.printf("%sEnter a number: %s", Constants.COLOR_YELLOW, Constants.COLOR_RESET);

            input = Validation.validateInput(scanner.nextLine());
            proceed = true;

            switch (input) {
                case 1 -> missionLength = Constants.VALUE_MISSION_LENGTH_SHORT;
                case 2 -> missionLength = Constants.VALUE_MISSION_LENGTH_MEDIUM;
                case 3 -> missionLength = Constants.VALUE_MISSION_LENGTH_LONG;
                default -> {
                    System.out.printf("%sInvalid choice.%s\n",
                            Constants.COLOR_RED, Constants.COLOR_RESET);
                    proceed = false;
                }
            }
        }

        proceed = false;
        while (!proceed) {
            System.out.println("Choose a mission difficulty:");
            for (int i = 1; i < Constants.DIFFICULTIES.size() + 1; i++) {
                System.out.printf("%d. %s\n", i, Constants.DIFFICULTIES.get(i - 1));
            }
            System.out.printf("%sEnter a number: %s", Constants.COLOR_YELLOW, Constants.COLOR_RESET);

            input = Validation.validateInput(scanner.nextLine());
            proceed = true;

            if (input < 1 || input > Constants.DIFFICULTIES.size()) {
                System.out.printf("%sInvalid choice.%s\n",
                        Constants.COLOR_RED, Constants.COLOR_RESET);
                proceed = false;
            } else {
                missionDifficulty = Constants.DIFFICULTIES.get(input - 1);
            }
        }
        this.mission = new Mission(missionDifficulty, missionLength);
        this.mission.generateMission();
    }

    /**
     * Prompts the user to select an encounter or proceed if there is only one option.
     * @return true if the mission is complete, false if not.
     */
    private boolean promptMissionSelect() {
        int input;
        String encounterType = "";
        boolean proceed = false;
        List<String> fork = this.mission.getNextFork();
        // TODO Add option to return to town/shop, maybe show stats, inventory, map, etc.

        if (fork.isEmpty()) {
            System.out.printf("%sMission complete!%s\n", Constants.COLOR_BLUE, Constants.COLOR_RESET);
            return true;
        } else if (fork.size() == 1) {
            encounterType = fork.get(0);
            System.out.printf("\n%sYour next mission is %s.%s\n",
                    Constants.COLOR_GREEN, encounterType, Constants.COLOR_RESET);
            promptContinue();
        } else {
            while (!proceed) {
                System.out.println("\nChoose an encounter...");
                for (int i = 0; i < fork.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, fork.get(i));
                }
                System.out.printf("%sEnter a number: %s", Constants.COLOR_YELLOW, Constants.COLOR_RESET);
                input = Validation.validateInput(scanner.nextLine());
                if (input < 1 || input > fork.size()) {
                    System.out.printf("%sInvalid choice.%s\n",
                            Constants.COLOR_RED, Constants.COLOR_RESET);
                } else {
                    encounterType = fork.get(input - 1);
                    System.out.printf("%sYou chose %s.%s\n",
                            Constants.COLOR_GREEN, encounterType, Constants.COLOR_RESET);
                    proceed = true;
                }
            }
        }
        if (Objects.equals(encounterType, Constants.MISSION_TYPE_MYSTERY)) {
            encounterType = Randomizer.getMysteryEncounter();
        }
        // TODO Implement encounter logic here
        System.out.printf("%sDoing encounter stuff...%s\n", Constants.COLOR_BLUE, Constants.COLOR_RESET);
        return false;
    }
}
