import enemies.Enemies;
import enemies.Goblin;
import enemies.Kobold;
import enemies.Zombie;
import org.junit.Test;
import support.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Enemies package
 * This class will be used to test the different methods in the Enemies package
 * will be further expanded after ability implementation
 * @author Martin Roos Eriksson
 */
public class TestEnemies {

    /**
     * Test the creation of an enemy
     * This test will create an enemy object and check if the type is correct
     */
    @Test
    public void testEnemyCreation() {
        Enemies enemy = new Zombie(0);
        assertEquals("Zombie", enemy.getType());
    }

    /**
     * Test the attack method
     * This test will create an enemy object and check if the attack method works as intended
     */
    @Test
    public void testAttack() {
        // Test attack method
        Enemies enemy = new Goblin(0);
        int initialDmg = enemy.getDamage();
        enemy.attack(5);
        int newDmg = enemy.getDamage();
        int minDmg = initialDmg + 1;
        int maxDmg = initialDmg + 4;
        assertTrue(newDmg >= minDmg && newDmg <= maxDmg);
    }

    /**
     * Test the ability method
     * This test will create an enemy object and check if the ability method works as intended
     */
    @Test
    public void testAbility() {
        // Test ability execution
        Enemies enemy = new Goblin(0);
        enemy.doAbility(); // Assuming the default ability is being executed
        assertTrue(((Goblin) enemy).getUsedPoison());

    }

    /**
     * Test the takeDamage method
     * This test will create an enemy object and check if the takeDamage method works as intended
     */
    @Test
    public void testTakeDamage() {
        // Test damage calculation
        Enemies enemy = new Goblin(0);
        enemy.takeDamage(60);
        assertEquals(0, enemy.getHealth());
    }

    /**
     * Test the death method
     * This test will create an enemy object and check if the death method works as intended
     */
    @Test
    public void testDeath() {
        // Test death
        Enemies enemy = new Kobold(0);
        enemy.takeDamage(150); // Assuming this leads to death
        // Add assertions to verify the outcome of death
    }

    /**
     * Test the MiniBoss method
     * This test will create an enemy object and check if their type is MiniBoss
     */
    @Test
    public void testMiniBoss() {
        // Test mini boss creation
        Enemies enemy = new Goblin(1);
        assertEquals("Goblin Boss", enemy.getType());
    }

    /**
     * TestZombie method
     * This test will create a zombie object and check if the type is correct
     */
    @Test
    public void testZombie() {
        // Test zombie creation
        Enemies enemy = new Zombie(0);
        assertEquals("Zombie", enemy.getType());
    }

    /**
     * TestKobold method
     * This test will create a kobold object and check if the type is correct
     */
    @Test
    public void testKobold() {
        // Test kobold creation
        Enemies enemy = new Kobold(0);
        assertEquals("Kobold", enemy.getType());
    }


}
