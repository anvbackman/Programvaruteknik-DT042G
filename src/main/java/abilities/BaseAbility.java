package abilities;

/**
 * Base class for all abilities in the game, this is an abstract class and will be used to create all abilities in the game.
 * @author Martin Roos Eriksson
 */
public abstract class BaseAbility {


    /** The name of the ability. */
    private final String name;

    private final int cost;

    /**
     * Constructor for the BaseAbility class.
     */
    public BaseAbility(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Method that returns the name of the ability
     * @return the name of the ability.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that returns the cost of the ability.
     * @return the cost of the ability.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Method that calculates the damage of the ability.
     * @param CharLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    public abstract int damageCalc(int CharLevel);

    /**
     * Method that returns the number of targets the ability can hit.
     * @return the number of targets the ability can hit.
     */
    public abstract int getTargets();

    public abstract int execute(int target, int charLevel);
}
