import character.Hero;
import creator.CharacterCreator;
import creator.Mission;
import scenarios.Battle;
import scenarios.BossBattle;
import scenarios.Encounter;
import scenarios.EncounterGenerator;
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
    private EncounterGenerator encounterGenerator;

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
        this.encounterGenerator = new EncounterGenerator(this.mission, scanner, this.hero);
        this.shop = new Shop(mission.getDifficulty(), scanner, this.hero);

        // Loops until the mission is complete.
        int code = 0;
        while (code == 0) {
            code = promptEncounterSelect();
        }
        switch (code) {
            // TODO handle mission complete and failure
            case Constants.RETURN_CODE_SUCCESS -> System.out.println("Mission complete!");
            case Constants.RETURN_CODE_FAILURE -> System.out.println("You died!");
            default -> System.out.println("Something went wrong...");
        }
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
     * @return return code for the game engine.
     */
    private int promptEncounterSelect() {
        int input;
        String encounterType = "";
        boolean proceed = false;
        List<String> fork = this.mission.getNextFork();

        // Generates new shop inventory after each encounter.
        this.shop.generateShop();

        // Checks if the mission has ended.
        if (fork.isEmpty()) {
            Output.printSuccessMessage("Mission complete!");
            return Constants.RETURN_CODE_SUCCESS;
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
        Encounter encounter = encounterGenerator.generateEncounter(encounterType);
        encounter.execute();
        if (encounter instanceof Battle) {
            CombatHandler combatHandler = new CombatHandler(this.hero, ((Battle) encounter).getEnemies());
            combatHandler.startCombat();
        } else if (encounter instanceof BossBattle) {
            CombatHandler combatHandler = new CombatHandler(this.hero, ((BossBattle) encounter).getEnemies());
            combatHandler.startCombat();
        }

        if (this.hero.getHealth() <= 0) {
            Output.printErrorMessage("You died!");
            return Constants.RETURN_CODE_FAILURE;
        }

        return Constants.RETURN_CODE_NEUTRAL;
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
                System.out.printf("%s%d/%d%s Health | %s%d/%d%s Mana | %s%d/%d%s XP\n",
                        Constants.COLOR_RED,
                        hero.getHealth(), hero.getMaxHealth(),
                        Constants.COLOR_RESET,

                        Constants.COLOR_BLUE,
                        hero.getManaPool(), hero.getMaxMana(),
                        Constants.COLOR_RESET,

                        Constants.COLOR_GREEN,
                        hero.getStats().getExperience(), hero.getStats().calculateNextLevelExperience(),
                        Constants.COLOR_RESET
                );
                System.out.println(hero.getStats());
                promptContinue();
                return false;
            }
            case 3 -> {
                // Show character inventory.
                hero.showInventory();
                promptContinue();
                return false;
            }
            case 4 -> {
                // Show mission map.
                Output.printSuccessMessage("Viewing map...");
                mission.showMission();
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
