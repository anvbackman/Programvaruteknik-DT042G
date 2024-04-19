import enemies.Enemies;
import enemies.Goblin;
import enemies.Kobold;
import enemies.Zombie;
import org.junit.Test;
import support.Constants;

import static org.junit.Assert.assertEquals;

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
        enemy.setDamage(20);
        assertEquals(20, enemy.getDamage());
    }

    @Test
    public void testAbility() {
        // Test ability execution
        Enemies enemy = new Goblin(0);
        enemy.doAbility(); // Assuming the default ability is being executed
        // Add assertions to verify the outcome of the ability execution
    }

    @Test
    public void testTakeDamage() {
        // Test damage calculation
        Enemies enemy = new Goblin(0);
        int base = enemy.getHealth();
        int expected = base - 5;
        enemy.takeDamage(5);
        assertEquals(expected, enemy.getHealth());
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
        Enemies enemy = new Goblin(Constants.VALUE_MINI_BOSS_TIER);
        assertEquals("Goblin Boss", enemy.getType());
    }


}
