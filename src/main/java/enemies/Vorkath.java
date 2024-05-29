package enemies;

import support.Randomizer;

/**
 * Vorkath class is a subclass of the Enemies class and represents a boss enemy
 * @author Andreas Backman
 */
public class Vorkath extends Enemies {

    public Vorkath(final int bossTier) {
        super("Vorkath the Soul Devourer", 0, 0, 0, bossTier);
        this.health = Randomizer.rollD10(12);
        this.damage = Randomizer.rollD6(6);
        this.armor = Randomizer.rollD6(6);
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     */
    protected void devour() {
        System.out.println("Vorkath the Soul Devourer, used devour!");
        int ogDamage = damage;
        damage += Randomizer.rollD4(3);
        attack(damage);
        setDamage(ogDamage);
    }



    public void doAbility() {
        devour();
        System.out.println(type + " used special ability");
    }

    /**
     * Method that will set the health of the Vorkath object to 0 and print out that the Vorkath has died
     */
    public void Death() {
        System.out.println(type + " has died");

    }


    /**
     * Method that will take in an int value and set the health of the Vorkath object to that value
     * @param health int value that will be set to the health of the Vorkath object
     */
    public void setHealth(final int health) {
        this.health = health;

    }

    /**
     * Method that will take in an int value and set the damage of the Vorkath object to that value
     * @param damage int value that will be set to the damage of the Vorkath object
     */
    public void setDamage(final int damage) {
        this.damage = damage;

    }

    /**
     * Method that will take in an int value and set the armor of the Vorkath object to that value
     * @param armor int value that will be set to the armor of the Vorkath object
     */
    public void setArmor(final int armor) {
        this.armor = armor;

    }

    /**
     * Method that will return the health of the Vorkath object
     * @return int value that represents the health of the Vorkath object
     */
    public int getHealth() {
        return health;

    }

    /**
     * Method that will return the damage of the Vorkath object
     * @return int value that represents the damage of the Vorkath object
     */
    public int getDamage() {
        return damage;

    }

    /**
     * Method that will return the armor of the Vorkath object
     * @return int value that represents the armor of the Vorkath object
     */
    public int getArmor() {
        return armor;

    }

    /**
     * Method that will return the type of the Vorkath object
     * @return String value that represents the type of the Vorkath object, for example archer, warrior, etc.
     */
    public String getType() {
        return type;

    }

    /**
     * Method that will take in an int value and set the damage of the Vorkath object to that value
     * @param dmg int value that will be set to the damage of the Vorkath object
     */
    public void attack(int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg + Randomizer.rollD6(2);

    }
}
