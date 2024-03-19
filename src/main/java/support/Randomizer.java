package support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The randomizer class.
 * Contains methods for rolling dice and generating random values.
 * @author Emil Jönsson
 */
public class Randomizer {

    /**
     * Rolls a 6-sided die n times and returns the sum of the rolls.
     * @param n the number of times to roll the die.
     * @return the sum of the rolls.
     */
    public static int rollD6(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += (int) (Math.random() * 6) + 1;
        }
        return result;
    }

    /**
     * Rolls a 6-sided die once and returns the result. Optional parameter for multiple rolls.
     * @return the result of the roll.
     */
    public static int rollD6() {
        return rollD6(1);
    }

    /**
     * Rolls a 10-sided die n times and returns the sum of the rolls.
     * @param n the number of times to roll the die.
     * @return the sum of the rolls.
     */
    public static int rollD10(int n) {
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result += (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    /**
     * Rolls a 10-sided die once and returns the result. Optional parameter for multiple rolls.
     * @return the result of the roll.
     */
    public static int rollD10() {
        return rollD10(1);
    }

    /**
     * Rolls a 20-sided die n times and returns the sum of the rolls.
     * @param n the number of times to roll the die.
     * @return the sum of the rolls.
     */
    public static int rollD20(int n) {
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result += (int) (Math.random() * 20) + 1;
        }
        return result;
    }

    /**
     * Rolls a 20-sided die once and returns the result. Optional parameter for multiple rolls.
     * @return the result of the roll.
     */
    public static int rollD20() {
        return rollD20(1);
    }

    /**
     * Rolls value for a stat base value, with logging.
     * @return the value for a stat base value.
     */
    public static int rollStat(boolean shouldLog) {
        int[] rolls = new int[Constants.VALUE_BASE_STAT_DICE_ROLLS];
        int temp;
        for (int i = 0; i < Constants.VALUE_BASE_STAT_DICE_ROLLS ; i++) {
            temp = rollD6();
            rolls[i] = temp;

            if (shouldLog) {
                System.out.printf("%s\tRolled a %d%s\n",
                        Constants.COLOR_PURPLE, temp, Constants.COLOR_RESET);
            }
        }
        Arrays.sort(rolls);
        temp = Arrays.stream(rolls).sum() - rolls[0];

        if (shouldLog) {
            System.out.printf("%s\tDropping lowest roll: %d%s\n",
                    Constants.COLOR_PURPLE, rolls[0], Constants.COLOR_RESET);
            System.out.printf("%s\tTotal: %d%s\n",
                    Constants.COLOR_PURPLE, temp, Constants.COLOR_RESET);
        }
        return temp;
    }

    /**
     * Rolls value for a stat base value.
     * @return the value for a stat base value.
     */
    public static int rollStat() {
        return rollStat(false);
    }

    /**
     * Randomly varies the length of a mission.
     * @param baseLength the selected base length of the mission.
     * @return the length of the mission.
     */
    public static int rollMissionLength(int baseLength) {
        if (baseLength < 1) {
            System.out.printf("%sChosen length is too short, setting to 1.%s\n",
                    Constants.COLOR_RED, Constants.COLOR_RESET);
            baseLength = 1;
        }
        return baseLength + (int) (Math.random() * Constants.VALUE_MISSION_LENGTH_VARIANCE);
    }

    /**
     * Rolls a random amount of mission forks.
     * @return the amount of mission forks.
     */
    public static int rollMissionForkAmount() {
        return Constants.VALUE_MISSION_FORK_AMOUNT_MIN + (int) (Math.random() *
                (Constants.VALUE_MISSION_FORK_AMOUNT_MAX - Constants.VALUE_MISSION_FORK_AMOUNT_MIN + 1));
    }

    /**
     * Generates a random encounter type for a mystery encounter.
     * @return the type of encounter as string value.
     */
    public static String getMysteryEncounter() {
        List<String> options = new ArrayList<>(Constants.MISSION_TYPES_ALL);
        Collections.shuffle(options);
        return options.get(0);
    }
}
