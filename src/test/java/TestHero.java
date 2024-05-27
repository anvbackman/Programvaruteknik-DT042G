import abilities.BaseAbility;
import abilities.Brutalize;
import character.Hero;
import character.StatSheet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.Constants;

/**
 * Test the Hero class.
 * @author Emil Jönsson
 */
public class TestHero {

    private Hero hero;
    @BeforeEach
    public void setUp() {
        hero = new Hero(new StatSheet(), "TestHero", "TestClass");
    }

    /**
     * Test the starting money of the hero
     */
    @Test
    public void testStartMoney() {
        Assertions.assertEquals(Constants.VALUE_CHARACTER_STARTING_GOLD, hero.getGold());
    }

    /**
     * Test adding money to the hero.
     */
    @Test
    public void testAddMoney() {
        int startingGold = hero.getGold();
        int goldToAdd = 100;
        hero.addGold(goldToAdd);
        Assertions.assertEquals(startingGold + goldToAdd, hero.getGold());
    }

    /**
     * Test removing money from the hero.
     */
    @Test
    public void testRemoveMoney() {
        int startingGold = hero.getGold();
        int goldToRemove = -25;
        hero.addGold(goldToRemove);
        Assertions.assertEquals(startingGold + goldToRemove, hero.getGold());
    }

    /**
     * Test removing more money than the hero has.
     */
    @Test
    public void testRemoveMoneyFail() {
        int goldToRemove = hero.getGold() + 10;
        Assertions.assertFalse(hero.addGold(-goldToRemove));
    }
}
