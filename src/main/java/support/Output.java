package support;

import abilities.BaseAbility;
import character.Hero;
import enemies.Enemies;

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
     * Prints a combat log message for the hero attack.
     * @param target the enemy being attacked.
     * @param hero the hero in combat.
     * @return the final damage dealt by the hero attack.
     */
    public static int printHeroAttackCombatLog(Enemies target, Hero hero) {
        int damage = hero.getAttack();
        int defense = target.getArmor();
        int finalDamage = Math.max(damage - defense, 0);
        System.out.printf("You attack %s for %s%d%s-%s%d%s damage (%d-%d HP)\n",
                target.getType(),
                Constants.COLOR_RED,
                damage,
                Constants.COLOR_RESET,
                Constants.COLOR_YELLOW,
                defense,
                Constants.COLOR_RESET,
                target.getHealth(),
                finalDamage
        );
        return finalDamage;
    }

    /**
     * Prints a combat log message for the hero ability.
     * @param target the enemy being attacked.
     * @param hero the hero in combat.
     * @param ability the ability used by the hero.
     * @return the final damage dealt by the hero ability.
     */
    public static int printHeroAbilityCombatLog(Enemies target, Hero hero, BaseAbility ability) {
        int damage = ability.damageCalc(hero.getLevel());
        System.out.printf("You use %s on %s for %d damage (%d-%d HP)\n",
                ability,
                target.getType(),
                damage,
                target.getHealth(),
                damage
        );
        return damage;
    }

    /**
     * Prints a log about the enemy attack.
     *
     * @param enemy the enemy attacking
     * @param hero the hero in combat
     * @return the final damage dealt by the enemy attack
     */
    public static int printEnemyAttackCombatLog(Enemies enemy, Hero hero) {
        int damage = enemy.getDamage();
        int defense = hero.getDefense();
        int finalDamage = Math.max(damage - defense, 0);
        System.out.printf("%s attacks you for %s%d%s-%s%d%s damage (%d-%d HP)\n",
                enemy.getType(),
                Constants.COLOR_RED,
                damage,
                Constants.COLOR_RESET,
                Constants.COLOR_YELLOW,
                defense,
                Constants.COLOR_RESET,
                hero.getHealth(),
                finalDamage
        );
        return finalDamage;
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
