package abilities;

import support.Constants;
import support.Randomizer;

/**
 * SacredFlames class is a subclass of BaseAbility and will be used to create the SacredFlames ability in the game
 * @author Martin Roos Eriksson
 */
public class SacredFlames extends BaseAbility {

    /**
     * Constructor for the SacredFlames class.
     */
    public SacredFlames() {
        super("SacredFlames", Constants.COST_ABILITY_MEDIUM);
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
