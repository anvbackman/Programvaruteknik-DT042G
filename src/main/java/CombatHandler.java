import character.Hero;
import enemies.Enemies;
import support.Randomizer;
import support.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The combat handler class.
 * Contains the methods for handling combat, such as player turn, enemy turn, attack, abilities, use item and run.
 * It also contains the method for starting the combat.
 * @author Martin Roos Eriksson
 */
public class CombatHandler {

    /**
     * The hero object, retrieve hero, stats etc..
     */
    private final Hero hero;

    /**
     * The initiative for the hero.
     */
    private int heroInitiative;

    /**
     * The initiative for the enemies.
     */
    private int enemiesInitiative;

    /**
     * The attack value for the enemies.
     */
    private final int enemyAttack = 10;

    /**
     * The amount of actions the player has.
     */
    private int actions = 2;

    /**
     * The enemies list, retrieve enemies, stats etc..
     */
    private ArrayList<Enemies> enemies;

    /**
     * The scanner object, used for user input.
     */
    private final Scanner scanner;

    /**
     * The boolean value for if it is the players turn.
     */
    private boolean isPlayerTurn = true;

    /**
     * The boolean value for if the hero is defeated.
     */
    private boolean isDefeated = false;

    /**
     * Constructor for the combat handler class. It takes a hero object and a list of enemies.
     * @param hero the hero object.
     * @param enemies the list of enemies.
     */
    public CombatHandler(Hero hero, List<Enemies> enemies) {
        this.hero = hero;
        this.enemies = new ArrayList<>(enemies);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method that will return the amount of actions the player has.
     * @return int value that represents the amount of actions the player has.
     */
    private int getActions(){
        return actions;
    }

    // Placeholder methods for the hero stats
    private int getHP(){
        return hero.getHealth();
    }

    // Placeholder methods for the hero stats
    private int getMana(){
        return 10;
    }

    // Placeholder methods for the hero stats
    private int getAttack(){
        return 10;
    }

    // Placeholder methods for the hero stats
    private int getDefense(){
        return 10;
    }

    // Placeholder methods for the hero stats
    private int getSpeed(){
        return 10;
    }

    // Placeholder methods for the hero stats
    private int getEnemyHP(){
        return 10;
    }

    /**
     * Method that will declare the hero defeated.
     */
    private void declareDefeat(){
        System.out.println("Your vision darkens... and you feel the chill of death...");
        isDefeated = true;
    }

    /**
     * Starts the combat. It will loop until all enemies are defeated or the hero is defeated.
     * It will call the player turn and the enemies turn.
     * It will also print out the result of the combat.
     * If the hero is defeated, it will print out that the hero has been defeated.
     */
    public void startCombat(){
        System.out.println("Combat has started!");
        rollInitiative(); // this will roll initiative to see who goes first
        System.out.println("You are facing " + enemies.size() + " enemies!");
        while (!enemies.isEmpty() && !isDefeated) {
            if(isPlayerTurn){
                playerTurn();
            } else {
                enemiesTurn();
            }
        }
      if (isDefeated){
          System.out.println("You have been defeated!");
       } else {
          System.out.println("You have defeated all the enemies!");
       }
    }

    /**
     * Rolls initiative to see who goes first.
     */
    private void rollInitiative(){
        int heroInitiative = Randomizer.rollD20();
        int enemiesInitiative = Randomizer.rollD20();
        if (heroInitiative == enemiesInitiative){
            rollInitiative();
        } else {
            if (heroInitiative > enemiesInitiative){
                System.out.println("You have the initiative!");
                isPlayerTurn = true;
            } else {
                System.out.println("The enemies have the initiative!");
                isPlayerTurn = false;
            }
        }
    }



    private void playerTurn(){
        System.out.println("It is your turn!");


        System.out.println("What do you want to do?");
        System.out.println("1. Attack");
        System.out.println("2. Ability");
        System.out.println("3. Use item");
        System.out.println("4. Run");
        int choice = Validation.validateInput(scanner.nextLine());
        if(actions >= 1) {
            switch (choice) {
                case 1 -> attack();
                case 2 -> Abilities();
                case 3 -> useItem();
                case 4 -> run();
                default -> System.out.println("Invalid choice, try again!");
            }

        }else{
            SwapTurn();
        }
    }

    public void SwapTurn(){
        isPlayerTurn = !isPlayerTurn;
    }

    private void enemiesTurn(){
        System.out.println("It is the enemies turn!");
        for (Enemies enemy : enemies){
            // TODO replace with actual attack method
            int damage = Randomizer.rollD6();
            System.out.println("The enemy attacks you for " + damage + " damage!");
            hero.reduceHealth(damage);
            // enemy.attack(1); // this will implement a check to see if the enemy can attack
            if (getHP() <= 0){
                declareDefeat();
            }
        }
        SwapTurn(); // after all enemies have attacked, it will swap the turn back to the player
    }

    private void attack(){
        System.out.println("You attack the enemy!");
        actions--;
        ArrayList<Enemies> remainingEnemies = new ArrayList<>();
        for (Enemies enemy : enemies) {
            //enemy.takeDamage(hero.getAttack());
            if (enemy.isDead()){
                System.out.println("You have defeated an enemy!");
            } else {
                remainingEnemies.add(enemy);
            }
        }
        enemies = remainingEnemies;
    }

    private void Abilities(){
        System.out.println("You use your ability!");
        actions--;
        for (Enemies enemy : enemies){
            //hero.doAbility(enemy); // this will implement a menu where you can choose from your class abilities
            if (enemy.isDead()){
                System.out.println("You have defeated an enemy!");
                enemies.remove(enemy);
            }
        }
    }

    private void useItem(){
        actions--;
        System.out.println("You use an item!");
        // this will implement a menu where you can choose from your inventory
    }

    private void run(){
        actions--;
        System.out.println("You run away!");
        // this will implement a check to see if you can run away
    }



}
