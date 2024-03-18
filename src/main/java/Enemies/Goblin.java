package Enemies;

/**
 * Goblin class that extends the Enemies class and implements the EnemyAbility interface
 * This class will be used to create a Goblin enemy with the ability to call for reinforcements
 * and use a special ability, which is implemented in the EnemySpecial class
 * since Enemies is abstract this will use the template pattern
 */
public class Goblin extends Enemies{

    /**
     * Goblin constructor that takes in an EnemyAbility object
     */
    EnemyAbility ability;

    /**
     * Goblin constructor that takes in an EnemyAbility object
     * @param ability EnemyAbility object that will be used to give the Goblin a special ability
     */
    public Goblin(EnemyAbility ability){
        super("Goblin", 10, 4, 1); // will use dice in the future
        this.ability = new EnemySpecial();
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     */
    public void doAbility() {
        ability.performAbility();
        System.out.println(type + " used special ability");
    }

    /**
     * Method that will call the reinforce method from the Goblin class and create a new Goblin object
     */
    protected void reinforce() {
        System.out.println("Goblin has called for reinforcements");
        new Goblin(ability);
    }

    /**
     * Method that will take in an int value and subtract it from the health of the Goblin object
     * @param damage int value that will be subtracted from the health of the Goblin object
     */
    public void takeDamage(int damage) {
        getHealth();
        health -= damage;
        System.out.println(type + " took " + damage + " damage");
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
     * Method that will take in an int value and set the damage of the Goblin object to that value
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
    public void attack(int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg;
    }


}
