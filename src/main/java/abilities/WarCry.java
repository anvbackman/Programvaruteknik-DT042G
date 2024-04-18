package abilities;

import support.Randomizer;

/**
 * WarCry ability for the Barbarian class, this ability is used to deal damage to a single target.
 * The damage is calculated by rolling a d10 for every level the character has - will be changed later..'
 * @author Martin Roos Eriksson
 */
public class WarCry extends BaseAbility {

    /**
     * Constructor for the WarCry class.
     */
    public WarCry() {
        super("WarCry", 15);
    }

    /**
     * Method that calculates the damage of the ability.
     * @param CharLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    public int damageCalc(int CharLevel) {

        return Randomizer.rollD10(CharLevel);

    }

    /**
     * Method that returns the number of targets the ability can hit.
     * @return the number of targets the ability can hit.
     */
    public int getTargets(){
        return 1;
    }

}

