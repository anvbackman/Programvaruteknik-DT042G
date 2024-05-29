package enemies;

import support.Randomizer;

/**
 * Boblin class is a subclass of the Enemies class and represents a boss enemy
 * @author Martin Eriksson
 */
public class Skara extends Enemies {

    public Skara(final int bossTier) {
        super("Skara, The Braille Script", 0, 0, 0, bossTier);
        this.health = Randomizer.rollD10(8);
        this.damage = Randomizer.rollD6(4);
        this.armor = Randomizer.rollD6(4);
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     */
    protected void WaspGrenade() {
        System.out.println("Skara used Wasp Grenade");
        int ogDamage = damage;
        damage += Randomizer.rollD4(2);
        attack(damage);
        setDamage(ogDamage);
    }



    public void doAbility() {
        WaspGrenade();
        System.out.println(type + " used special ability");
    }

    /**
     * Method that will set the health of the Skara object to 0 and print out that the Skara has died
     */
    public void Death() {
        System.out.println(type + " has died");

    }


    /**
     * Method that will take in an int value and set the health of the Skara object to that value
     * @param health int value that will be set to the health of the Skara object
     */
    public void setHealth(int health) {
        this.health = health;

    }

    /**
     * Method that will take in an int value and set the damage of the Skara object to that value
     * @param damage int value that will be set to the damage of the Skara object
     */
    public void setDamage(int damage) {
        this.damage = damage;

    }

    /**
     * Method that will take in an int value and set the armor of the Skara object to that value
     * @param armor int value that will be set to the armor of the Skara object
     */
    public void setArmor(final int armor) {
        this.armor = armor;

    }

    /**
     * Method that will return the health of the Skara object
     * @return int value that represents the health of the Skara object
     */
    public int getHealth() {
        return health;

    }

    /**
     * Method that will return the damage of the Skara object
     * @return int value that represents the damage of the Skara object
     */
    public int getDamage() {
        return damage;

    }

    /**
     * Method that will return the armor of the Skara object
     * @return int value that represents the armor of the Skara object
     */
    public int getArmor() {
        return armor;

    }

    /**
     * Method that will return the type of the Skara object
     * @return String value that represents the type of the Skara object, for example archer, warrior, etc.
     */
    public String getType() {
        return type;

    }

    /**
     * Method that will take in an int value and set the damage of the Skara object to that value
     * @param dmg int value that will be set to the damage of the Skara object
     */
    public void attack(final int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg + Randomizer.rollD6(2);

    }
}
