package character;

import support.Constants;
import support.Randomizer;

import java.util.HashMap;

/**
 * The stat sheet class.
 * Contains all the stats for a character.
 * @author Emil Jönsson
 */
public class StatSheet {


    private int reRollAmount;
    private final HashMap<String, Integer> stats;

    /**
     * Constructor for the stat sheet class.
     */
    public StatSheet() {
        reRollAmount = Constants.VALUE_MAX_STAT_REROLLS;
        stats = new HashMap<>();
        Constants.STATS.forEach(stat -> stats.put(stat, Randomizer.rollStat()));
    }

    /**
     * Re-rolls a selected stat.
     * @param stat the stat to re-roll.
     * @return true if the stat is valid, false if not.
     */
    public boolean reRollSelected(final String stat) {
        int oldValue;
        try {
            oldValue = stats.get(stat);
        } catch (NullPointerException e) {
            System.out.printf("%sInvalid stat.%s\n", Constants.COLOR_RED, Constants.COLOR_RESET);
            return false;
        }
        int newValue = Randomizer.rollStat(true);
        String colorCode;
        String operator;
        reRollAmount--;
        stats.put(stat, newValue);

        if (oldValue < newValue) {
            // If value has increased.
            colorCode = Constants.COLOR_GREEN;
            operator = "+";
        } else if (oldValue == newValue) {
            // If value has not changed.
            colorCode = Constants.COLOR_BLUE;
            operator = "+";
        } else {
            // If value has decreased.
            colorCode = Constants.COLOR_RED;
            operator = "";
        }

        System.out.printf(colorCode + "Re-rolled %s: %d (%s%d)" + Constants.COLOR_RESET + "\n",
                stat, newValue, operator, (newValue - oldValue));
        return true;
    }

    /**
     * Returns the amount of re-rolls left.
     * @return the amount of re-rolls left as integer value.
     */
    public int getReRollAmount() {
        return reRollAmount;
    }

    /**
     * Returns the value of a stat.
     * @param stat the stat name to get the value of.
     * @return the value of the stat as integer value or -1 if the stat is unassigned.
     */
    public int getStat(final String stat) {
        try {
            return stats.get(stat);
        } catch (NullPointerException e) {
            System.out.printf("%sInvalid stat.%s\n", Constants.COLOR_RED, Constants.COLOR_RESET);
            return -1;
        }
    }

    /**
     * Sets the value of a selected stat.
     * @param stat the stat to set the value of.
     * @param value the value to set the stat to.
     * @return true if the stat is valid, false if not.
     */
    public boolean setStat(final String stat, final int value) {
        if (Constants.STATS.contains(stat)) {
            stats.put(stat, value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the stat sheet as a formatted string.
     * @return stat sheet as a string.
     */
    @Override
    public String toString() {
        StringBuilder statsString = new StringBuilder();
        stats.forEach((key, value) -> statsString.append(key).append(": ").append(value).append("\n"));
        return statsString.toString();
    }
}
