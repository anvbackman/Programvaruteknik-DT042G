package gears;

/**
 * Armor class that extends Gear
 * Represents armor in the game
 * @author Andreas Backman
 */
public class Armor extends Gear {

    /**
     * Constructor for Armor
     * @param name the type of armor
     * @param value the value of the armor
     * @param cost the cost of the armor
     */
    public Armor(final String name, final int value, final int cost) {
        super(name, "armor", value, cost);
    }
}

