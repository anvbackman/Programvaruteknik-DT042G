package enemies;

import support.Constants;
import support.Randomizer;

/**
 * Goblin class that extends the Enemies class and implements the EnemyAbility interface
 * This class will be used to create a Goblin enemy with the ability to call for reinforcements
 * and use a special ability, which is implemented in the EnemySpecial class
 * since Enemies is abstract this will use the template pattern
 * @author Martin Roos Eriksson
 */
public class Goblin extends Enemies {

    private boolean UsedPoison = false;

    private Boolean isMiniBoss;

    /**
     * Goblin constructor that takes in an EnemyAbility object
     *
     */
    public Goblin(final int bossTier) {
        super("Goblin", 0, 0, 0, bossTier); // Initialize with default values

        if (bossTier >= Constants.VALUE_MINI_BOSS_TIER) {
            this.type = "Goblin Boss";
            this.health = Randomizer.rollD10(6);
            this.damage = Randomizer.rollD4(3);
            this.armor = Randomizer.rollD4(3);
        } else {
            this.type = "Goblin";
            this.health = Randomizer.rollD10(2);
            this.damage = Randomizer.rollD4(1);
            this.armor = Randomizer.rollD4(1);
        }

    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     */
    public void doAbility() {
        poison();
        System.out.println(type + " used special ability");
    }

    /**
     * Method that will call the reinforce method from the Goblin class and create a new Goblin object
     */
    protected void poison() {
        System.out.println("Goblin uses a poison attack");
        int ogDamage = damage;
        damage += Randomizer.rollD4(1);
        attack(damage);
        UsedPoison = true;
        setDamage(ogDamage);
    }

    /**
     * Method that will take in an int value and subtract it from the health of the Goblin object
     * @param damage int value that will be subtracted from the health of the Goblin object
     */
    public void takeDamage(final int damage) {
        health -= damage;
        if(health <= 0) {
            Death();
        }
    }

    /**
     * Method that will set the health of the Goblin object to 0 and print out that the Goblin has died
     * This method will be called when the health of the Goblin object is less than or equal to 0
     * and will be called in the takeDamage method
     */
    public void Death() {
        health = 0;
        System.out.println(type + " has died");
    }

    /**
     * Method that will take in an int value and set the health of the Goblin object to that value
     * @param health int value that will be set to the health of the Goblin object
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method that will take in an int value and set the damage of the Goblins attack to that value
     * @param damage int value that will be set to the damage of the Goblin object
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Method that will take in an int value and set the armor of the Goblin object to that value
     * @param armor int value that will be set to the armor of the Goblin object
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * Method that will return the health of the Goblin object
     * @return int value that represents the health of the Goblin objec
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method that will return the damage of the Goblin object
     * @return int value that represents the damage of the Goblin object
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Method that will return the armor of the Goblin object
     * @return int value that represents the armor of the Goblin object
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Method that will return the type of the Goblin object
     * @return String value that represents the type of the Goblin object, for example archer, warrior, etc.
     */
    public String getType() {
        return type;
    }

    /**
     * Method that will take in an int value and set the damage of the Goblin object to that value
     * @param dmg int value that will be set to the damage of the Goblin object
     */
    public void attack(final int dmg) {
        System.out.println(type + " is Attacking");
        damage = damage + Randomizer.rollD4(1);
    }


}
