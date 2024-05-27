package support;

import gears.Gear;

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
        for (int i = 0; i < n; i++) {
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
        for (int i = 0; i < n; i++) {
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
     * Rolls a 4-sided die n times and returns the sum of the rolls.
     * @param n the number of times to roll the die.
     * @return the sum of the rolls.
     */
    public static int rollD4(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += (int) (Math.random() * 4) + 1;
        }
        return result;
    }

    /**
     * Rolls a 4-sided die once and returns the result. Optional parameter for multiple rolls.
     * @return the result of the roll.
     */
    public static int rollD4() {
        return rollD4(1);
    }

    /**
     * Rolls value for a stat base value, with logging. Rolls a six sided die 4 times.
     * Discards the lowest roll and returns the sum of the remaining rolls.
     * @return stat base value as integer.
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
     * Rolls value for a stat base value without logging.
     * @return the value for a stat base value.
     */
    public static int rollStat() {
        return rollStat(false);
    }

    /**
     * Randomly varies the length of a mission, defaults to 1 if input is value too small.
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
     * Rolls the amount of options that will be available at a fork in a mission.
     * Upper and lower bounds dictated by the Constants class.
     * @return the amount of mission forks.
     */
    public static int rollMissionForkAmount() {
        return Constants.VALUE_MISSION_FORK_AMOUNT_MIN + (int) (Math.random() *
                (Constants.VALUE_MISSION_FORK_AMOUNT_MAX - Constants.VALUE_MISSION_FORK_AMOUNT_MIN + 1));
    }

    /**
     * Generates a random encounter type for a mystery encounter from the Constants class.
     * @return the type of encounter as string value.
     */
    public static String getMysteryEncounter() {
        List<String> options = new ArrayList<>(Constants.MISSION_TYPES_AVAILABLE_AS_MYSTERY);
        Collections.shuffle(options);
        String result = options.get(0);
        System.out.printf("\t%sMystery encounter is %s.%s\n",
                Constants.COLOR_PURPLE, result, Constants.COLOR_RESET);
        return result;
    }

    /**
     * Rolls the size of the shop inventory.
     * @return the size of the shop inventory.
     */
    public static int rollShopSize() {
        return Constants.VALUE_SHOP_INITIAL_SIZE + (int) (Math.random() * Constants.VALUE_SHOP_SIZE_VARIANCE);
    }

    /**
     * Selects a random gear from a list of gear.
     * @param gearList the list of gear to select from.
     * @return the selected gear.
     */
    public static List<Gear> selectRandom(List<Gear> gearList, int amount) {
        List<Gear> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            result.add(gearList.get((int) (Math.random() * gearList.size())));
        }
        return result;
    }
}
