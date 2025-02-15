package abilities;

import support.Constants;
import support.Randomizer;

/**
 * FireBolt ability for the Wizard class.
 * The damage is calculated by rolling a d10 for every level the character has.
 * @author Martin Roos Eriksson
 */
public class FireBolt extends BaseAbility {

    /**
     * Constructor for the FireBolt class.
     */
    public FireBolt() {
        super("FireBolt", Constants.COST_ABILITY_MEDIUM);
    }

    /**
     * Calculates the damage of the ability.
     * @param charLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    public int damageCalc(int charLevel) {
        return Randomizer.rollD10(charLevel);
    }

    /**
     * Returns the number of targets the ability can hit.
     * @return the number of targets the ability can hit.
     */
    public int getTargets(){
        return 1;
    }

}
