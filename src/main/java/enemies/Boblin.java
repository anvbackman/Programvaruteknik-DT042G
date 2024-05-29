package enemies;

import support.Randomizer;

/**
 * Boblin class is a subclass of the Enemies class and represents a boss enemy.
 * @author Martin Eriksson
 */
public class Boblin extends Enemies {

    public Boblin(final int bossTier) {
        super("Boblin the Goblin", 0, 0, 0, bossTier);
        this.health = Randomizer.rollD10(6);
        this.damage = Randomizer.rollD6(3);
        this.armor = Randomizer.rollD6(3);
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     */
    protected void fellBlast() {
        System.out.println("Boblin used FellBlase");
        int ogDamage = damage;
        damage += Randomizer.rollD4(2);
        attack(damage);
        setDamage(ogDamage);
    }



    public void doAbility() {
        fellBlast();
        System.out.println(type + " used special ability");
    }

    /**
     * Method that will set the health of the Boblin object to 0 and print out that Boblin has died
     */
    public void Death() {
        System.out.println(type + " has died");
    }


    /**
     * Method that will take in an int value and set the health of the Boblin object to that value
     * @param amount int value that will be set to the health of the Boblin object
     */
    public void setHealth(final int amount) {
        this.health = amount;
    }

    /**
     * Method that will take in an int value and set the damage of the Boblin object to that value
     * @param amount int value that will be set to the damage of the Boblin object
     */
    public void setDamage(final int amount) {
        this.damage = amount;
    }

    /**
     * Method that will take in an int value and set the armor of the Boblin object to that value
     * @param amount int value that will be set to the armor of the Boblin object
     */
    public void setArmor(final int amount) {
        this.armor = amount;
    }

    /**
     * Method that will return the health of the Boblin object
     * @return int value that represents the health of the Boblin object
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method that will return the damage of the Boblin object
     * @return int value that represents the damage of the Boblin object
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Method that will return the armor of the Boblin object
     * @return int value that represents the armor of the Boblin object
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Method that will return the type of the Boblin object
     * @return String value that represents the type of the Boblin object, for example archer, warrior, etc.
     */
    public String getType() {
        return type;
    }

    /**
     * Method that will take in an int value and set the damage of the Boblin object to that value
     * @param dmg int value that will be set to the damage of the Boblin object
     */
    public void attack(final int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg + Randomizer.rollD6(2);

    }
}
