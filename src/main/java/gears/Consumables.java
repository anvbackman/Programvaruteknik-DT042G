package gears;

/**
 * Consumables class that extends Gear
 * Represents a consumable in the game
 * @author Andreas Backman
 */
public class Consumables extends Gear {

    /**
     * Constructor for Consumables
     * @param name the type of consumable
     * @param value the value of the consumable
     * @param cost the cost of the consumable
     */
    public Consumables(final String name, final int value, final int cost) {
        super(name, "consumables", value, cost);
    }
}