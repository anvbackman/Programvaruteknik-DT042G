package gears;

/**
 * Gear class that represents a piece of gear in the game
 * Including Weapons, Armor and Consumables
 * @author Andreas Backman
 */
public class Gear {
    private String name;
    private final String gearCategory;
    private int value;
    private int cost;

    /**
     * Constructor for the Gear class
     * @param name the type of gear
     * @param gearCategory the category of gear
     * @param value the value of gear
     * @param cost the cost of gear
     */
    public Gear(final String name, final String gearCategory, final int value, final int cost) {
        this.name = name;
        this.gearCategory = gearCategory;
        this.value = value;
        this.cost = cost;
    }

    /**
     * Method to get the type of gear
     * @return the type of gear
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the category of gear
     * @return the category of gear
     */
    public String getGearCategory() {
        return gearCategory;
    }

    /**
     * Method to get the value of gear
     * @return the value of gear
     */
    public int getValue() {
        return value;
    }

    /**
     * Method to set the type of gear
     * @param name the type of gear
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Method to set the value of gear
     * @param value the value of gear
     */
    public void setValue(final int value) {
        this.value = value;
    }

    /**
     * Method to get the cost of gear
     * @return the cost of gear
     */
    public int getCost() {
        return cost;
    }

    /**
     * Method to set the cost of gear
     * @param cost the cost of gear
     */
    public void setCost(final int cost) {
        this.cost = cost;
    }
}
