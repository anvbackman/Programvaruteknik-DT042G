package abilities;

/**
 * Base class for all abilities in the game,
 * this is an abstract class and will be used to create all abilities in the game.
 * @author Martin Roos Eriksson
 */
public abstract class BaseAbility {


    /** The name of the ability. */
    private final String name;

    /** The cost of the ability. */
    private final int cost;

    /**
     * Constructor for the BaseAbility class.
     * @param abilityName the name of the ability.
     * @param cost the cost of the ability.
     */
    public BaseAbility(final String abilityName, final int cost) {
        this.name = abilityName;
        this.cost = cost;
    }

    /**
     * Returns the name of the ability.
     * @return the name of the ability.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the cost of the ability.
     * @return the cost of the ability.
     */
    public final int getCost() {
        return cost;
    }

    /**
     * Calculates the damage of the ability.
     * @param charLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    public abstract int damageCalc(final int charLevel);

    /**
     * Returns the number of targets the ability can hit.
     * @return the number of targets the ability can hit.
     */
    public abstract int getTargets();

    /**
     * Method that executes the ability.
     * @param target the target of the ability.
     * @param charLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    public abstract int execute(int target, int charLevel);
}
