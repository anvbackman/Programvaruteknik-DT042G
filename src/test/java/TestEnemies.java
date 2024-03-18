import Enemies.Goblin;
import Enemies.Kobold;
import Enemies.Zombie;
import org.junit.Test;
import static org.junit.Assert.*;
import Enemies.Enemies;

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

        Enemies enemy = new Zombie();
        assertEquals("Zombie", enemy.getType());
    }

    @Test
    public void testAttack() {
        // Test attack method
        Enemies enemy = new Goblin();
        enemy.attack(20);
        assertEquals(20, enemy.getDamage());
    }

    @Test
    public void testAbility() {
        // Test ability execution
        Enemies enemy = new Goblin();
        enemy.doAbility(); // Assuming the default ability is being executed
        // Add assertions to verify the outcome of the ability execution
    }

    @Test
    public void testTakeDamage() {
        // Test damage calculation
        Enemies enemy = new Goblin();
        enemy.takeDamage(15);
        assertEquals(0, enemy.getHealth());
    }

    @Test
    public void testDeath() {
        // Test death
        Enemies enemy = new Kobold();
        enemy.takeDamage(150); // Assuming this leads to death
        // Add assertions to verify the outcome of death
    }

}
