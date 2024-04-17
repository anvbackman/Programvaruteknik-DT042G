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

    /**
     * Prints an error message to the console, using a given string.
     * Appends Color code red from the Constants class.
     * @param message the message to be printed.
     */
    public static void printErrorMessage(String message) {
        System.out.printf("%s%s%s\n", Constants.COLOR_RED, message, Constants.COLOR_RESET);
    }

    /**
     * Prints a message to the console, using a given string.
     * @param health the health of the player
     * @param damage the damage dealt by the player
     * @param type the type of enemy
     */
    public static void printHeroAttackCombatLog(int health, int damage, String type){
        System.out.printf("You attack %s for %d damage (%s%d%s-%s%d%s=%d)\n",
                type,
                damage,
                Constants.COLOR_GREEN,
                health,
                Constants.COLOR_RESET,
                Constants.COLOR_RED,
                damage,
                Constants.COLOR_RESET,
                health - damage
        );
    }

    public static void printHeroAbilityCombatLog(int health, int damage, String type, String ability){
        System.out.printf("You use %s on %s for %d damage (%s%d%s-%s%d%s=%d)\n",
                ability,
                type,
                damage,
                Constants.COLOR_GREEN,
                health,
                Constants.COLOR_RESET,
                Constants.COLOR_RED,
                damage,
                Constants.COLOR_RESET,
                health - damage
        );
    }

    /**
     * Prints the combat log for an enemy attack.
     * @param health the health of the player
     * @param damage the damage dealt by the enemy
     * @param type the type of the enemy
     */
    public static void printEnemyAttackCombatLog(int health, int damage, String type){
        System.out.printf("%s attacks you for %d damage (%s%d%s-%s%d%s=%d)\n",
                type,
                damage,
                Constants.COLOR_GREEN,
                health,
                Constants.COLOR_RESET,
                Constants.COLOR_RED,
                damage,
                Constants.COLOR_RESET,
                health - damage
        );
    }

    /**
     * Clears the console screen.
     */
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
