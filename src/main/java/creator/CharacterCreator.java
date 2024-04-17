package creator;

import character.Hero;
import character.StatSheet;
import support.Constants;
import support.Output;
import support.Validation;

import java.util.Scanner;

/**
 * The character creator class.
 * @author Emil Jönsson
 */
public class CharacterCreator {

    private String classChoice;
    private final StatSheet statSheet;
    private String name;
    private final Scanner scanner;

    /**
     * Constructor for the character creator class.
     */
    public CharacterCreator() {
        this.scanner = new Scanner(System.in);
        this.statSheet = new StatSheet();
    }

    /**
     * Prompts the user with all the steps to create a character.
     * @return the created character.
     */
    public final Hero createCharacter() {
        boolean proceed = false;

        // Loops until a valid class is selected
        while (!proceed) {
            promptClassSelect();
            proceed = selectClass(Validation.validateInput(scanner.nextLine()));
        }

        // Loops until all re-rolls are used or the user is satisfied with the stat sheet.
        proceed = false;
        while (!proceed) {
            promptReRollStatSheet();
            proceed = reRollStatSheet(Validation.validateInput(scanner.nextLine()));
        }

        // Loops until a valid name is selected.
        proceed = false;
        while (!proceed) {
            promptNameSelect();
            proceed = selectName(scanner.nextLine());
        }

        // Creates the character and prints the result.
        Output.printSuccessMessage("Character created!.");
        Hero hero = new Hero(statSheet, name, classChoice);
        System.out.println(hero.getName() + " the " + classChoice);
        System.out.println(hero.getStats());

        return hero;
    }

    /**
     * Prompts the user to select a class.
     */
    private void promptClassSelect() {
        Output.printPromptHeader("Select a class:");
        for (int i = 1; i < Constants.CLASSES.size() + 1; i++) {
            System.out.printf("%d. %s\n", i, Constants.CLASSES.get(i - 1));
        }
        Output.printEnterNumberMessage();
    }

    /**
     * Selects a class based on the user input.
     * @param input number input from the user.
     * @return true if the input is valid, false if not.
     */
    public boolean selectClass(final int input) {

        if (input < 1 || input > Constants.CLASSES.size()) {
            Output.printInvalidChoiceMessage();
            return false;
        } else {
            classChoice = Constants.CLASSES.get(input - 1);
            System.out.printf("%sSelected %s.%s\n",
                    Constants.COLOR_GREEN, classChoice, Constants.COLOR_RESET);
            return true;
        }
    }

    /**
     * Prompts the user to re-roll a stat.
     */
    private void promptReRollStatSheet() {
        if (statSheet.getReRollAmount() != 0) {
            if (statSheet.getReRollAmount() > 1) {
                System.out.println("You have " + statSheet.getReRollAmount() + " re-rolls left.");
            } else {
                System.out.println("You have " + statSheet.getReRollAmount() + " re-roll left.");
            }
            System.out.println("Which stat would you like to re-roll?");
            for (int i = 1; i < Constants.STATS.size() + 1; i++) {
                System.out.printf("%d. %s (%d)\n", i, Constants.STATS.get(i - 1), statSheet.getStat(Constants.STATS.get(i - 1)));
            }
            System.out.printf("%d. Proceed\n", Constants.STATS.size() + 1);
            Output.printEnterNumberMessage();
        }
    }

    /**
     * Re-rolls a stat based on the user input.
     * @param input number input from the user.
     * @return true if the input is valid, false if not.
     */
    public boolean reRollStatSheet(final int input) {

        // Checks if the user has no re-rolls left.
        if (statSheet.getReRollAmount() == 0) {
            System.out.println("You have no re-rolls left.");
            return true;
        }

        // Checks if input is not a valid stat choice.
        if (input < 1 || input > Constants.STATS.size()) {
            if (input != Constants.STATS.size() + 1) {
                Output.printInvalidChoiceMessage();
                return false;
            } else {
                return true;
            }
        } else {
            statSheet.reRollSelected(Constants.STATS.get(input - 1));
        }
        return statSheet.getReRollAmount() == 0;
    }

    /**
     * Prompts the user to select a name.
     */
    private void promptNameSelect() {
        Output.printPromptHeader("Enter a name for your Hero:");
    }

    /**
     * Selects a name based on the user input.
     * @param input the name input from the user.
     * @return true if the input is valid, false if not.
     */
    public boolean selectName(final String input) {
        boolean proceed = false;

        if (input.isEmpty()) { // Checks if the input is empty.
            System.out.printf("%sPlease enter a name.%s\n",
                    Constants.COLOR_RED, Constants.COLOR_RESET);
        } else if (input.length() > 20) { // Checks if the input is too long.
            System.out.printf("%sName cannot exceed 20 characters.%s\n",
                    Constants.COLOR_RED, Constants.COLOR_RESET);
        } else if (input.replace(" ", "").isEmpty()) { // Checks if the input only contains spaces.
            System.out.printf("%sName cannot only contain spaces.%s\n",
                    Constants.COLOR_RED, Constants.COLOR_RESET);
        } else {
            name = input;
            proceed = true;
        }
        return proceed;
    }

    /**
     * Returns the stat sheet of the current character.
     * @return stat sheet object.
     */
    public StatSheet getStatSheet() {
        return statSheet;
    }

    /**
     * Returns the amount of re-rolls left.
     * @return the amount of re-rolls left as integer value.
     */
    public int getReRollAmount() {
        return statSheet.getReRollAmount();
    }
}
