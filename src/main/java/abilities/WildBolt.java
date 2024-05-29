package abilities;

import support.Constants;
import support.Randomizer;

/**
 * WildBolt ability for the Sorcerer class.
 * The damage is calculated by rolling a d10 for every level.
 */
public class WildBolt extends BaseAbility {

    /**
     * Array of elements that the ability can generate.
     */
    private static final String[] ELEMENTS = {"Fire", "Wind", "Water", "Lightning"};

    /**
     * Constructor for the WildBolt class.
     */
    public WildBolt() {
        super("WildBolt", Constants.COST_ABILITY_MEDIUM);
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

    /**
     * Method that generates an element for the ability.
     * @return the element generated.
     */
    private String generateElement() {
        // Choose a random element from the ELEMENTS array
        int index = Randomizer.rollD4(1);
        return ELEMENTS[index];
    }
}
