package abilities;

/**
 * Base class for all abilities in the game, this is an abstract class and will be used to create all abilities in the game.
 * @author Martin Roos Eriksson
 */
public abstract class BaseAbility {


    /** The name of the ability. */
    private final String name;

    /**
     * Constructor for the BaseAbility class.
     */
    public BaseAbility(String name) {
        this.name = name;
    }

    /**
     * Method that returns the name of the ability
     * @return the name of the ability.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that executes the ability.
     */
    public abstract int execute(int target, int charLevel);
}
