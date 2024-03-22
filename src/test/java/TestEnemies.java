import enemies.Goblin;
import enemies.Kobold;
import enemies.Zombie;
import org.junit.Test;
import enemies.Enemies;

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
        boolean isMiniBoss = false;
        Enemies enemy = new Zombie(isMiniBoss);
        assertEquals("Zombie", enemy.getType());
    }

    @Test
    public void testAttack() {
        // Test attack method
        boolean isMiniBoss = false;

        Enemies enemy = new Goblin(isMiniBoss);
        enemy.attack(20);
        assertEquals(20, enemy.getDamage());
    }

    @Test
    public void testAbility() {
        // Test ability execution
        boolean isMiniBoss = false;
        Enemies enemy = new Goblin(isMiniBoss);
        enemy.doAbility(); // Assuming the default ability is being executed
        // Add assertions to verify the outcome of the ability execution
    }

    @Test
    public void testTakeDamage() {
        // Test damage calculation
        boolean isMiniBoss = false;
        Enemies enemy = new Goblin(isMiniBoss);
        enemy.takeDamage(15);
        assertEquals(0, enemy.getHealth());
    }

    @Test
    public void testDeath() {
        // Test death
        boolean isMiniBoss = false;
        Enemies enemy = new Kobold(isMiniBoss);
        enemy.takeDamage(150); // Assuming this leads to death
        // Add assertions to verify the outcome of death
    }
    
    @Test
    public void testMiniBoss() {
        // Test mini boss creation
        boolean isMiniBoss = true;
        Enemies enemy = new Goblin(isMiniBoss);
        assertEquals("Goblin Boss", enemy.getType());
    }


}
