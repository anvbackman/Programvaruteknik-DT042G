package Enemies;

/**
 * EnemySpecial class that implements the EnemyAbility interface
 * This class will be used to create special abilities for the enemies
 * This class will be used in the Goblin, Zombie and Kobold classes
 * @author Martin Roos Eriksson
 */
public class EnemySpecial implements EnemyAbility{

    @Override
    public void performAbility() {
        System.out.println("Special ability used");
    }


}
