package gears;

public class Gear {
    private String type;
    private String gearCategory;
    private int value;

    /**
     * Constructor for Gear
     * @param type the type of gear
     * @param gearCategory the category of gear
     */
    public Gear(String type, String gearCategory) {
        this.type = type;
        this.gearCategory = gearCategory;
    }

    /**
     * Method to get the type of gear
     * @return the type of gear
     */
    public String getType() {
        return type;
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
     * @param type the type of gear
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method to set the value of gear
     * @param value the value of gear
     */
    public void setValue(int value) {
        this.value = value;
    }
}
