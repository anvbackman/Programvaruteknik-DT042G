package support;

import character.Hero;
import enemies.Enemies;

/**
 * General calculator class for the game, this class will be used to calculate different values in the game.
 * @author Emil Jönsson
 */
public class Calculator {

    /**
     * Calculates the damage dealt by a hero attack to a specific target.
     * @param hero the hero attacking.
     * @param target the target of the attack.
     * @return the final damage dealt by the hero attack.
     */
    public static int calculateHeroAttackDamage(Hero hero, Enemies target) {
        return Math.max(hero.getAttack() - target.getArmor(), 0);
    }

    /**
     * Calculates the damage dealt by an enemy attack to a target hero.
     * @param enemy the enemy attacking.
     * @param target the target of the attack.
     * @return the final damage dealt by the enemy attack.
     */
    public static int calculateEnemyAttackDamage(Enemies enemy, Hero target) {
        return Math.max(enemy.getDamage() - target.getDefense(), 0);
    }
}
