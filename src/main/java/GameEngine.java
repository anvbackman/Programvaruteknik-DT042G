import character.Hero;
import creator.CharacterCreator;
import creator.Mission;
import scenarios.Shop;
import support.Constants;
import support.Output;
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
    private Shop shop;

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
        CharacterCreator characterCreator = new CharacterCreator();
        this.shop = new Shop(Constants.DIFFICULTY_MEDIUM, scanner);

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
                default -> Output.printInvalidChoiceMessage();
            }
        }
        // Waits for user to press enter before continuing.
        promptContinue();
        promptMissionCreation();
        Output.printSuccessMessage("Your mission begins...");

        // Loops until the mission is complete.
        proceed = false;
        while (!proceed) {
            proceed = promptEncounterSelect();
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

        // Prompt the user to select mission length.
        while (!proceed) {
            Output.printPromptHeader("Choose a mission length:");
            System.out.println("1. Short");
            System.out.println("2. Medium");
            System.out.println("3. Long");
            Output.printEnterNumberMessage();

            input = Validation.validateInput(scanner.nextLine());
            proceed = true;

            switch (input) {
                case 1 -> missionLength = Constants.VALUE_MISSION_LENGTH_SHORT;
                case 2 -> missionLength = Constants.VALUE_MISSION_LENGTH_MEDIUM;
                case 3 -> missionLength = Constants.VALUE_MISSION_LENGTH_LONG;
                default -> {
                    Output.printInvalidChoiceMessage();
                    proceed = false;
                }
            }
        }

        // Prompt the user to select mission difficulty.
        proceed = false;
        while (!proceed) {
            Output.printPromptHeader("Choose a mission difficulty:");
            for (int i = 1; i < Constants.DIFFICULTIES.size() + 1; i++) {
                System.out.printf("%d. %s\n", i, Constants.DIFFICULTIES.get(i - 1));
            }
            Output.printEnterNumberMessage();

            input = Validation.validateInput(scanner.nextLine());
            proceed = true;

            if (input < 1 || input > Constants.DIFFICULTIES.size()) {
                Output.printInvalidChoiceMessage();
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
    private boolean promptEncounterSelect() {
        int input;
        String encounterType = "";
        boolean proceed = false;
        List<String> fork = this.mission.getNextFork();

        // Generates new shop inventory after each encounter.
        this.shop.generateShop();

        // Checks if the mission has ended.
        if (fork.isEmpty()) {
            Output.printSuccessMessage("Mission complete!");
            return true;
        }

        // Prompt the user to select alternative actions before starting an encounter.
        while (!proceed) {
            promptPreEncounterOptions();
            proceed = selectPreEncounterOptions(Validation.validateInput(scanner.nextLine()));
        }

        // Prompts the user to continue if there is only one option at the current fork.
        if (fork.size() == 1) {
            encounterType = fork.get(0);
            Output.printPromptHeader("Your next encounter is " + encounterType + "...");
            promptContinue();
        } else {
            // Prompts the user to select one the available encounters until a valid one is chosen.
            proceed = false;
            while (!proceed) {
                Output.printPromptHeader("Choose an encounter...");
                // Prints options.
                for (int i = 0; i < fork.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, fork.get(i));
                }
                Output.printEnterNumberMessage();
                input = Validation.validateInput(scanner.nextLine());
                // Handles invalid input.
                if (input < 1 || input > fork.size()) {
                    Output.printInvalidChoiceMessage();
                } else {
                    // Displays chosen encounter and exits loop.
                    encounterType = fork.get(input - 1);
                    System.out.printf("%sYou chose %s.%s\n",
                            Constants.COLOR_GREEN, encounterType, Constants.COLOR_RESET);
                    proceed = true;
                }
                // End of loop
            }
        }
        // If next encounter is mystery, generate a random encounter.
        if (Objects.equals(encounterType, Constants.MISSION_TYPE_MYSTERY)) {
            encounterType = Randomizer.getMysteryEncounter();
        }

        // TODO Implement encounter logic here
        System.out.printf("%sDoing encounter stuff...%s\n", Constants.COLOR_BLUE, Constants.COLOR_RESET);

        return false;
    }

    /**
     * Allows selection of alternative actions before starting an encounter.
     * Only returns true if next encounter is chosen.
     * @param input user choice.
     * @return true if game should proceed, else false.
     */
    private boolean selectPreEncounterOptions(final int input) {
        switch (input) {
            case 1 -> {
                // Return to town.
                Output.printSuccessMessage("Returning to town...");
                this.shop.execute();
                return false;
            }
            case 2 -> {
                // Show character stats.
                Output.printSuccessMessage("Viewing stats...");
                System.out.println(hero.getStats());
                promptContinue();
                return false;
            }
            case 3 -> {
                // Show character inventory.
                Output.printSuccessMessage("Viewing inventory...");
                // TODO Implement inventory logic here
                promptContinue();
                return false;
            }
            case 4 -> {
                // Show mission map.
                Output.printSuccessMessage("Viewing map...");
                mission.showMission();
                // TODO Implement map logic here
                promptContinue();
                return false;
            }
            case 5 -> {
                // Continue to next encounter.
                return true;
            }
            default -> {
                // Handle invalid input.
                Output.printInvalidChoiceMessage();
                return false;
            }
        }
    }

    /**
     * Prompt message for alternative actions before starting an encounter.
     */
    private void promptPreEncounterOptions() {
        Output.printPromptHeader("What would you like to do?");
        System.out.println("1. Return to town");
        System.out.println("2. View stats");
        System.out.println("3. View inventory");
        System.out.println("4. View map");
        System.out.println("5. Continue to next encounter");
        Output.printEnterNumberMessage();
    }
}
