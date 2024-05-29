package abilities;

import support.Constants;
import support.Randomizer;

/**
 * WildShape ability for the Druid class.
 * The damage is calculated by rolling a d10 for every level.
 * @author Martin Roos Eriksson
 */
public class WildShape extends BaseAbility {

    /**
     * Constructor for the WildShape class.
     */
    public WildShape() {
        super("WildShape", Constants.COST_ABILITY_MEDIUM);
    }

    /**
     * Method that calculates the damage of the ability.
     * @param charLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    public int damageCalc(final int charLevel) {
        return Randomizer.rollD10(charLevel);
    }

    /**
     * Method that returns the number of targets the ability can hit.
     * @return the number of targets the ability can hit.
     */
    public int getTargets() {
        return 1;
    }
}
