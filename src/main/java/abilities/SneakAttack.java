package abilities;

import support.Randomizer;

/**
 * SneakAttack ability for the Rogue class, this ability is used to deal damage to a single target.
 * The damage is calculated by rolling a d10 for every level the character has - will be changed later.'
 * @author Martin Roos Eriksson
 */
public class SneakAttack extends BaseAbility {

    /**
     * Constructor for the SneakAttack class.
     */
    public SneakAttack() {
        super("SneakAttack", 10);
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

    /**
     * Method that executes the ability.
     * @param target the target of the ability.
     * @param charLevel the level of the character using the ability.
     */
    public int execute(int target, int charLevel) {
        int targets = getTargets();
        int damage = damageCalc(charLevel);
        return damage;
    }

}
