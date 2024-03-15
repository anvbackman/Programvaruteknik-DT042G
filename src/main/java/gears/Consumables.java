package gears;

/**
 * Consumables class that extends Gear
 * Represents a consumable in the game
 * @author Andreas Backman
 */
public class Consumables extends Gear {
    /**
     * Constructor for Consumables
     * @param type the type of consumable
     */
    public Consumables(String type) {
        super(type, "consumables");
    }
}