package abilities;

import support.Randomizer;

/**
 * WildBolt ability for the Sorcerer class, this ability is used to deal damage to a single target.
 * The damage is calculated by rolling a d10 for every level the character has.
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
        super("WildBolt", 20);
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
     * Method that generates an element for the ability.
     * @return the element generated.
     */
    private String generateElement() {
        // Choose a random element from the ELEMENTS array
        int index = Randomizer.rollD4(1);
        return ELEMENTS[index];
    }

    /**
     * Method that executes the ability.
     * @param target the target of the ability.
     * @param charLevel the level of the character using the ability.
     */
    public int execute(int target, int charLevel) {
        target = getTargets();
        int damage = damageCalc(charLevel);
        String element = generateElement();
        return damage;
    }

}
