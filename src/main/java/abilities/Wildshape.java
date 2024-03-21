package abilities;

import support.Randomizer;

/**
 * Wildshape ability for the Druid class, this ability is used to deal damage to a single target.
 * The damage is calculated by rolling a d10 for every level the character has.
 * @author Martin Roos Eriksson
 */
public class Wildshape extends BaseAbility{

    /**
     * Constructor for the Wildshape class.
     */
    public Wildshape() {
        super("Wildshape");
    }

    /**
     * Method that calculates the damage of the ability.
     * @param CharLevel the level of the character using the ability.
     * @return the damage dealt by the ability.
     */
    private int damageCalc(int CharLevel) {

        int result = Randomizer.rollD10(CharLevel);
        return result;
    }

    /**
     * Method that returns the number of targets the ability can hit.
     * @return the number of targets the ability can hit.
     */
    private int getTargets(){
        return 1;
    }

    /**
     *  Method that executes the ability.
     * @param target the target of the ability.
     * @param charLevel the level of the character using the ability.
     */
    public void execute(int target, int charLevel) {
        int targets = getTargets();
    }
}
