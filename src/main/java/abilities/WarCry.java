package abilities;

import support.Constants;
import support.Randomizer;

/**
 * WarCry ability for the Barbarian class.
 * The damage is calculated by rolling a d10 for every level.
 * @author Martin Roos Eriksson
 */
public class WarCry extends BaseAbility {

    /**
     * Constructor for the WarCry class.
     */
    public WarCry() {
        super("WarCry", Constants.COST_ABILITY_LOW);
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

    /**
     * Method that executes the ability.
     * @param target the target of the ability.
     * @param charLevel the level of the character using the ability.
     */
    public int execute(int target, int charLevel) {
        target = getTargets();
        int damage = damageCalc(charLevel);
        return damage;
    }
}

