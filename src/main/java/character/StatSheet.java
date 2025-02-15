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

    /** The level of the character. */
    private int level;

    /** The experience of the character. */
    private int experience;

    /** The experience needed to reach the next level. */
    private int nextLevelExperience;

    /** The amount of re-rolls left. */
    private int reRollAmount;

    /** The stats of the character. */
    private final HashMap<String, Integer> stats;

    /** The hero assigned to the stat sheet. */
    private Hero hero;

    /**
     * Constructor for the stat sheet class.
     */
    public StatSheet() {
        reRollAmount = Constants.VALUE_MAX_STAT_REROLLS;
        this.level = 1;
        this.experience = 0;
        calculateNextLevelExperience();
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
     * Sets the hero of the stat sheet.
     * @param character the hero to set.
     */
    public void setHero(Hero character) {
        this.hero = character;
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

    /**
     * Levels up the characters stats by one.
     */
    public void levelUp() {
        level++;
        stats.replaceAll((stat, value) -> value + 1);
        reRollAmount += Constants.VALUE_MAX_STAT_REROLLS;
        hero.adjustHealth(hero.getMaxHealth() / 2);
        hero.adjustMana(hero.getMaxMana() / 2);
        System.out.println("Leveled up! Your stats are now: " + this);
    }

    /**
     * Returns the level of the character.
     * @return the level of the character as integer value.
     */
    public int getLevel() {
        return level;
    }


    /**
     * Adds experience to the character. Progresses the character to the next level if enough experience is gained.
     * @param amount the number of experience points to add.
     */
    public void addExperience(final int amount) {
        System.out.printf("Gained %d experience points.\n", amount);
        this.experience += amount;
        if (this.experience >= nextLevelExperience) {
            this.experience -= nextLevelExperience;
            levelUp();
            calculateNextLevelExperience();
        }
    }

    /**
     * Retrieves number of experience points the character has.
     * @return the experience of the character as integer value.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Retrieves and sets the next level experience threshold.
     * @return the next level experience threshold as integer value.
     */
    public int calculateNextLevelExperience() {
        int levelModifier = Math.min(level, Constants.VALUES_EXPERIENCE_PER_LEVEL.size());
        return nextLevelExperience = Constants.VALUES_EXPERIENCE_PER_LEVEL.get(levelModifier - 1);
    }
}
