package abilities;

import support.Constants;
import support.Randomizer;

/**
 * SneakAttack ability for the Rogue class.
 * The damage is calculated by rolling a d10 for every level.
 * @author Martin Roos Eriksson
 */
public class SneakAttack extends BaseAbility {

    /**
     * Constructor for the SneakAttack class.
     */
    public SneakAttack() {
        super("SneakAttack", Constants.COST_ABILITY_LOW);
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
