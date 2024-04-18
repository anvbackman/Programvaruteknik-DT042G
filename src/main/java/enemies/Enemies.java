package enemies;

/**
 * Enemies class is the parent class for all enemies in the game
 * It contains the basic attributes and methods that all enemies will have
 * It also contains the ability to perform special abilities.
 * @author Martin Roos Eriksson
 */
public abstract class Enemies {

    private final int bossTier;
    /**
     * ability is an instance of the EnemyAbility interface
     */
    EnemyAbility ability;

    /**
     * type is a string that represents the type of enemy
     */
    String type;

    /**
     * health is an int that represents the health of the enemy
     */
    int health;

    /**
     * damage is an int that represents the damage of the enemy
     */
    int damage;

    /**
     * armor is an int that represents the armor of the enemy
     */
    int armor;

    /**
     * Constructor for the Enemies class, using the type, health, damage, and armor of the enemy
     * @param type specifies the type of enemy
     * @param health specifies the health of the enemy
     * @param damage specifies the damage of the enemy
     * @param armor specifies the armor of the enemy
     * @param bossTier specifies the tier of the boss, 1 for mini-boss, 2 for boss
     */
    public Enemies(String type, int health, int damage, int armor, int bossTier) {
        this.type = type;
        this.health = health;
        this.damage = damage;
        this.armor = armor;
        this.bossTier = bossTier;
    }

    /**
     * Method that will set the health of the enemy object
     * @param health int value that will be set to the health of the enemy object
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method that will set the damage of the enemy object
     * @param damage int value that will be set to the damage of the enemy object
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Method that will set the armor of the enemy object
     * @param armor int value that will be set to the armor of the enemy object
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * Method that will return the health of the enemy object
     * @return int value that represents the health of the enemy object
     */
    public int getHealth() {
        if(health <= 0) {
            health = 0;
            Death();
        }
        return health;
    }

    /**
     * Method that will return the damage of the enemy object
     * @return int value that represents the damage of the enemy object
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Method that will return the armor of the enemy object
     * @return int value that represents the armor of the enemy object
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Method that will return the type of the enemy object
     * @return String value that represents the type of the enemy object, for example archer, warrior, etc.
     */
    public String getType() {
        return type;
    }

    /**
     * Method that will make the enemy object attack
     * @param dmg int value that represents the damage that the enemy object will do
     */
    public void attack(int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg;
    }

    /**
     * Method that will make the enemy object perform a special ability
     */
    public void doAbility() {
        ability.performAbility();
        System.out.println(type + " used special ability");
    }

    /**
     * Method that will make the enemy object take damage
     * @param damage int value that represents the damage that the enemy object will take
     */
    public void takeDamage(int damage) {
        health -= damage;
    }

    /**
     * Method that will make the enemy object die
     */
    public void Death() {
        System.out.println(type + " has died");
    }

    /**
     * Placeholder, will be used to confirm an enemy is defeated.
     * @return true if the enemy is dead, false if the enemy is still alive
     */
    public boolean isDead() {
        return getHealth() == 0;
    }

    /**
     * Retrieves the boss tier of the enemy.
     * @return the boss tier of the enemy. 1 for mini-boss, 2 for boss.
     */
    public int getBossTier() {
        return bossTier;
    }
}
