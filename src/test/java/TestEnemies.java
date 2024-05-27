import enemies.Enemies;
import enemies.Goblin;
import enemies.Kobold;
import enemies.Zombie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Enemies package
 * This class will be used to test the different methods in the Enemies package
 * will be further expanded after ability implementation
 * @author Martin Roos Eriksson
 */
public class TestEnemies {


    @Test
    public void testEnemyCreation() {
        Enemies enemy = new Zombie(0);
        assertEquals("Zombie", enemy.getType());
    }

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

    @Test
    public void testAbility() {
        // Test ability execution
        Enemies enemy = new Goblin(0);
        enemy.doAbility(); // Assuming the default ability is being executed
        assertTrue(((Goblin) enemy).getUsedPoison());

    }

    @Test
    public void testTakeDamage() {
        // Test damage calculation
        Enemies enemy = new Goblin(0);
        enemy.takeDamage(60);
        assertEquals(0, enemy.getHealth());
    }

    @Test
    public void testDeath() {
        // Test death
        Enemies enemy = new Kobold(0);
        enemy.takeDamage(150); // Assuming this leads to death
        // Add assertions to verify the outcome of death
    }
    
    @Test
    public void testMiniBoss() {
        // Test mini boss creation
        Enemies enemy = new Goblin(1);
        assertEquals("Goblin Boss", enemy.getType());
    }

    @Test
    public void testZombie() {
        // Test zombie creation
        Enemies enemy = new Zombie(0);
        assertEquals("Zombie", enemy.getType());
    }

    @Test
    public void testKobold() {
        // Test kobold creation
        Enemies enemy = new Kobold(0);
        assertEquals("Kobold", enemy.getType());
    }


}
