package support;

/**
 * Class for print messages that are used multiple times during runtime.
 * @author Emil Jönsson.
 */
public class Output {

    /**
     * Prints a message that prompts the user to select a number.
     * Can be used in combination with a selection menu,
     */
    public static void printEnterNumberMessage() {
        System.out.printf("%sEnter a number: %s", Constants.COLOR_YELLOW, Constants.COLOR_RESET);
    }

    /**
     * Prints a message that indicates the chosen option is invalid.
     * More specific input feedback should be handled elsewhere.
     */
    public static void printInvalidChoiceMessage() {
        System.out.printf("%sInvalid choice.%s\n", Constants.COLOR_RED, Constants.COLOR_RESET);
    }

    /**
     * Prints an options select prompt header with a given message.
     * Appends Color code green from Constants class.
     * @param message the message to be printed.
     */
    public static void printPromptHeader(String message) {
        System.out.printf("\n%s%s%s\n", Constants.COLOR_GREEN, message, Constants.COLOR_RESET);
    }

    /**
     * Prints a success message to the console, using a given string.
     * Appends Color code blue from the Constants class.
     * @param message the message to be printed.
     */
    public static void printSuccessMessage(String message) {
        System.out.printf("%s%s%s\n", Constants.COLOR_BLUE, message, Constants.COLOR_RESET);
    }
}
