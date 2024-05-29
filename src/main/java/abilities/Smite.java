package abilities;

import support.Constants;
import support.Randomizer;

/**
 * Smite ability for the Paladin class.
 * The damage is calculated by rolling a d10 for every level the character has.
 * @author Martin Roos Eriksson
 */
public class Smite extends BaseAbility {

    /**
     * Constructor for the Smite class.
     */
    public Smite() {
            super("Smite", Constants.COST_ABILITY_MEDIUM);
        }

    /**
     * Calculates the damage of the ability.
     * @param charLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    public int damageCalc(final int charLevel) {
        return Randomizer.rollD10(charLevel);
        }

    /**
     * Returns the number of targets the ability can hit.
     * @return the number of targets the ability can hit.
     */
    public int getTargets() {
            return 1;
        }

}
