/**
 * A class to represent a character's stat sheet.
 * @author Emil Jönsson
 */
public class StatSheet {

    /**
     * Re-rolls a selected stat.
     * @param stat the stat to re-roll.
     * @return true if the stat is valid, false if not.
     */
    public boolean reRollSelected(String stat) {
        return false;
    }

    /**
     * Returns the amount of re-rolls left.
     * @return the amount of re-rolls left as integer value.
     */
    public int getReRollAmount() {
        return 0;
    }

    /**
     * Returns the value of a stat.
     * @param stat the stat name to get the value of.
     * @return the value of the stat as integer value or -1 if the stat is unassigned.
     */
    public int getStat(String stat) {
        return 0;
    }

    /**
     * Sets the value of a selected stat.
     * @param stat the stat to set the value of.
     * @param value the value to set the stat to.
     * @return true if the stat is valid, false if not.
     */
    public boolean setStat(String stat, int value) {
        return false;
    }
}
