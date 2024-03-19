package enemies;

import java.util.Random;


/**
 * Zombie class is a subclass of the Enemies class
 * It contains the attributes and methods specific to the Zombie enemy
 * It also contains the ability to perform special abilities.
 * @author Martin Roos Eriksson
 */
public class Zombie extends Enemies {

    int deathcount = 0; //int to keep track of how many times the zombie has died
    boolean willRevive = false; //boolean to check if the zombie will revive

    /**
     * Zombie constructor that takes in an EnemyAbility object
     */
    public Zombie() {
        super("Zombie", 15, 6, 2);
        this.ability = new EnemySpecial();
        EnemySpecial enemySpecial = bite();
        if (health <= 0) {
            revive();
        }
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     * @return EnemySpecial object
     */
    protected EnemySpecial bite() {
        System.out.println("Zombie lunges at you and bites you");
        infect();
        return null;
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     * This method will be called when the health of the Zombie object is less than or equal to 0
     * and will be called in the Zombie constructor
     */
    private void infect() {
        Random random = new Random();
        int chance = random.nextInt(100);
        if(chance < 50) {
            System.out.println("Zombie has infected you");
        }
    }

    /**
     * Method that will set the health of the Zombie object to 5 and print out that the Zombie has revived
     * This method will be called when the health of the Zombie object is less than or equal to 0
     * and will be called in the Zombie constructor for now, but in the future it will be called in the takeDamage method
     */
    private void revive() {

        if(deathcount < 2) {
            Random rand = new Random();
            int chance = rand.nextInt(100);
            if(chance < 50) {
                willRevive = true;
                setHealth(5);
                System.out.println("Zombie has revived");
                deathcount++;
            } else {
                Death();
            }

        } else {
            Death();
        }
    }

    /**
     * Method that will call the performAbility method from the EnemyAbility interface
     * This method will be called when the health of the Zombie object is less than or equal to 0
     */
    public void doAbility() {
        ability.performAbility();
        System.out.println(type + " used special ability");
    }

    /**
     * Method that will set the health of the Zombie object to 0 and print out that the Zombie has died
     * This method will be called when the health of the Zombie object is less than or equal to 0
     * and will be called in the takeDamage method unless revived
     */
    public void Death() {
        System.out.println(type + " has died");
    }

    /**
     * Method that will take in an int value and set the health of the Zombie object to that value
     * @param health int value that will be set to the health of the Zombie object
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method that will take in an int value and set the damage of the Zombie object to that value
     * @param damage int value that will be set to the damage of the enemy object
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Method that will take in an int value and set the armor of the Zombie object to that value
     * @param armor int value that will be set to the armor of the enemy object
     */
    public void setArmor(int armor) {
        this.armor = armor;

    }

    /**
     * Method that will return the health of the Zombie object
     * @return int value that represents the health of the Zombie object
     */
    public int getHealth() {
        return health;

    }

    /**
     * Method that will return the damage of the Zombie object
     * @return int value that represents the damage of the Zombie object
     */
    public int getDamage() {
        return damage;

    }

    /**
     * Method that will return the armor of the Zombie object
     * @return int value that represents the armor of the Zombie object
     */
    public int getArmor() {
        return armor;

    }

    /**
     * Method that will return the type of the Zombie object
     * @return String value that represents the type of the Zombie object, for example archer, warrior, etc.
     */
    public String getType() {
        return type;

    }

    /**
     * Method that will take in an int value and set the damage of the Zombie object to that value
     * @param dmg int value that represents the damage that the enemy object will do
     */
    public void attack(int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg;

    }

}
