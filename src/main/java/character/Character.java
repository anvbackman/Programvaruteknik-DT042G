package character;

/**
 * A class to represent a character in the game.
 * @author Emil Jönsson
 */
public class Character {

    private final String name;
    private final StatSheet stats;

    /**
     * Constructor for a character.
     * @param statSheet the stat sheet for the character.
     * @param name the name of the character.
     */
    public Character(final StatSheet statSheet, final String name) {
        this.name = name;
        this.stats = statSheet;
    }

    /**
     * Returns the name of the character.
     * @return the name of the character as String value.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the stat sheet of the character.
     * @return the stat sheet object of the character.
     */
    public StatSheet getStats() {
        return stats;
    }
}
