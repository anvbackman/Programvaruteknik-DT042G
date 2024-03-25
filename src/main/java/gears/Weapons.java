package gears;

/**
 * Weapons class that extends Gear
 * Represents a weapon in the game
 * @author Andreas Backman
 */
public class Weapons extends Gear {
    /**
     * Constructor for Weapons
     * @param name the type of weapon
     * @param value the value of the weapon
     * @param cost the cost of the weapon
     */
    public Weapons(String name, int value, int cost) {
        super(name, "weapons", value, cost);
    }
}

