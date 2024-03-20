package abilities;

/**
 * Base class for all abilities in the game, this is an abstract class and will be used to create all abilities in the game.
 * @author Martin Roos Eriksson
 */
public abstract class BaseAbility {

    /**
     * The name of the ability.
     */
    private final String name;

    /**
     * The description of the ability.
     */
    private final String description;

    /**
     * Constructor for the BaseAbility class.
     * @param name the name of the ability.
     * @param description the description of the ability.
     */
    public BaseAbility(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Method that will return the name of the ability.
     * @return the name of the ability.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that will return the description of the ability.
     * @return the description of the ability.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that will execute the ability.
     */
    public abstract void execute();

}
