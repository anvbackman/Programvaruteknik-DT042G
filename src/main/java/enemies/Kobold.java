package enemies;

import support.Randomizer;

/**
 * Kobold class is a subclass of the Enemies class
 * It contains the attributes and methods specific to the Kobold enemy
 * It also contains the ability to perform special abilities.
 * @author Martin Roos Eriksson
 */
public class Kobold extends Enemies {

    private Boolean isMiniBoss;

    /**
     * Kobold constructor that takes in an EnemyAbility object
     */
    public Kobold() {
        super("Kobold", 0, 0, 0, false); // Initialize with default values

        if (isMiniBoss) {
            this.type = "Kobold Boss";
            this.health = Randomizer.rollD6(8);
            this.damage = Randomizer.rollD4(4);
            this.armor = Randomizer.rollD4(2);
        } else {
            this.type = "Kobold";
            this.health = Randomizer.rollD6(4);
            this.damage = Randomizer.rollD4(2);
            this.armor = Randomizer.rollD4(1);
        }

        this.ability = new EnemySpecial();

    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     */
    protected void ThrowWaspNest() {
        System.out.println("Kobold is throwing a wasp nest at you!");
        int ogDamage = damage;
        damage += Randomizer.rollD4(3);
        attack(damage);
        setDamage(ogDamage);
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     */
    public void doAbility() {
        ThrowWaspNest();
        System.out.println(type + " used special ability");


    }

    /**
     * Method that will set the health of the Kobold object to 0 and print out that the Kobold has died
     */
    public void Death() {
        System.out.println(type + " has died");

    }

    /**
     * Method that will take in an int value and set the health of the Kobold object to that value
     * @param health int value that will be set to the health of the Kobold object
     */
    public void setHealth(int health) {
        this.health = health;

    }

    /**
     * Method that will take in an int value and set the damage of the Kobold object to that value
     * @param damage int value that will be set to the damage of the Kobold object
     */
    public void setDamage(int damage) {
        this.damage = damage;

    }

    /**
     * Method that will take in an int value and set the armor of the Kobold object to that value
     * @param armor int value that will be set to the armor of the Kobold object
     */
    public void setArmor(int armor) {
        this.armor = armor;

    }

    /**
     * Method that will return the health of the Kobold object
     * @return int value that represents the health of the Kobold object
     */
    public int getHealth() {
        return health;

    }

    /**
     * Method that will return the damage of the Kobold object
     * @return int value that represents the damage of the Kobold object
     */
    public int getDamage() {
        return damage;

    }

    /**
     * Method that will return the armor of the Kobold object
     * @return int value that represents the armor of the Kobold object
     */
    public int getArmor() {
        return armor;

    }

    /**
     * Method that will return the type of the Kobold object
     * @return String value that represents the type of the Kobold object, for example archer, warrior, etc.
     */
    public String getType() {
        return type;

    }

    /**
     * Method that will take in an int value and set the damage of the Kobold object to that value
     * @param dmg int value that will be set to the damage of the Kobold object
     */
    public void attack(int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg + Randomizer.rollD4(1);

    }

}
