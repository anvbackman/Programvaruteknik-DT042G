import abilities.BaseAbility;
import character.Hero;
import enemies.Enemies;
import support.Constants;
import support.Output;
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
     * The amount of actions the player has.
     */
    private int actions;
    private int reward;

    /**
     * The enemies list, retrieve enemies, stats etc..
     */
    private final ArrayList<Enemies> enemies;

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
        this.actions = Constants.VALUE_CHARACTER_COMBAT_ACTIONS;
    }

    /**
     * Returns the health value of the hero.
     * @return the health value of the hero.
     */
    private int getHealth(){
        return hero.getHealth();
    }

    /**
     * Returns the mana value of the hero.
     * @return the mana value of the hero.
     */
    private int getMana(){
        return this.hero.getManaPool();
    }

    /**
     * Returns the list of abilities the hero has.
     * @return the list of abilities the hero has.
     */
    private List<BaseAbility> getAbilities(){
        return List.of(this.hero.getAbility());
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
    public void startCombat() {
        System.out.println("Combat has started!");
        // Decides initial value of isPlayerTurn
        determineReward();
        rollInitiative();
        System.out.println("You are facing " + enemies.size() + " enemies!");
        while (!enemies.isEmpty() && !isDefeated) {
            if(isPlayerTurn){
                playerTurn();
            } else {
                enemiesTurn();
            }
        }
        if (!isDefeated){
            System.out.println("You have defeated all the enemies!");
            System.out.println("You have earned " + reward + " gold!");
            hero.addGold(reward);
        }
    }

    /**
     * Rolls initiative to see who goes first.
     */
    private void rollInitiative(){
        int heroInitiative = Randomizer.rollD20();
        int enemiesInitiative = Randomizer.rollD20();

        if (heroInitiative == enemiesInitiative){ // Re-roll if initiative is the same
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

    /**
     * The player turn method. It will prompt the player to choose an action.
     */
    private void playerTurn(){
        if(actions >= 1) {
            System.out.println("It is your turn!");
            System.out.printf("%s%d %s%s | %s%d/%d%s Health | %s%d/%d%s Mana | %s%d/%d%s XP\n",
                    Constants.COLOR_PURPLE,
                    actions, actions > 1 ? "Actions" : "Action",
                    Constants.COLOR_RESET,

                    Constants.COLOR_RED,
                    getHealth(), hero.getMaxHealth(),
                    Constants.COLOR_RESET,

                    Constants.COLOR_BLUE,
                    getMana(), hero.getMaxMana(),
                    Constants.COLOR_RESET,

                    Constants.COLOR_GREEN,
                    hero.getStats().getExperience(), hero.getStats().calculateNextLevelExperience(),
                    Constants.COLOR_RESET
                    );
            System.out.println("What would you like to do?");
            System.out.println("1. Attack");
            System.out.println("2. Ability");
            System.out.println("3. Use consumable item");
            System.out.println("4. Run");
            Output.printEnterNumberMessage();
            int choice = Validation.validateInput(scanner.nextLine());

            switch (choice) {
                case 1 -> attack();
                case 2 -> castAbility();
                case 3 -> useItem();
                case 4 -> run();
                default -> Output.printInvalidChoiceMessage();
            }
        } else {
            SwapTurn();
        }
    }

    /**
     * Swaps the turn between player and enemies.
     */
    public void SwapTurn() {
        actions = Constants.VALUE_CHARACTER_COMBAT_ACTIONS;
        isPlayerTurn = !isPlayerTurn;
    }

    /**
     * The enemies turn method. It will loop through all enemies and attack the hero.
     */
    private void enemiesTurn() {
        System.out.println("It is the enemies turn!");
        for (Enemies enemy : enemies){
            int damage = Output.printEnemyAttackCombatLog(enemy, hero);
            hero.adjustHealth(-damage);
            if (getHealth() <= 0){
                declareDefeat();
            }
        }
        // Swap turn back to player after enemies have attacked.
        SwapTurn();
    }

    /**
     * Prompt the player to choose an enemy to attack, then attack that enemy.
     */
    private void attack() {
        // Prompt the player to choose an enemy to attack
        Enemies target = chooseTarget();
        if (target == null) {
            // Cancel the attack if the player chose to go back
            return;
        }

        // Reduce the player's actions by 1
        actions--;

        // Calculate the damage dealt by the player's attack
        int damage = Output.printHeroAttackCombatLog(target, hero);
        target.takeDamage(damage);
        if (target.isDead()){
            removeEnemy(target);
        }
    }

    /**
     * Prompt the player to choose an enemy to use an ability on and the ability to use.
     */
    private void castAbility() {
        int input;
        boolean proceed = false;
        BaseAbility ability = null;
        BaseAbility temp;

        // Prompt the player to choose an ability to use
        while (!proceed) {
            Output.printPromptHeader("Choose an ability to use!");
            for (int i = 0; i < getAbilities().size(); i++) {
                temp = getAbilities().get(i);
                // Lists available abilities
                System.out.printf("%d. %s (-%d Mana)\n",
                        i + 1, // Input number
                        temp.getName(), // Ability name
                        temp.getCost() // Mana cost
                );
            }
            System.out.println("0. Back");
            Output.printEnterNumberMessage();

            input = Validation.validateInput(scanner.nextLine());
            switch (input) {
                case -1 -> Output.printInvalidChoiceMessage(); // Invalid input
                case 0 -> {
                    return; // Go back
                }
                default -> {
                    if (input < 1 || input > getAbilities().size()) {
                        Output.printInvalidChoiceMessage(); // Input out of range
                    } else {
                        ability = getAbilities().get(input - 1);
                        // Check if the player has enough mana to use the ability
                        if (getMana() < ability.getCost()) {
                            System.out.println("Not enough mana!");
                        } else {
                            proceed = true;
                        }
                    }
                }
            }
        }

        // Prompt the player to choose an enemy to target with the ability
        Enemies target = chooseTarget();
        if (target == null) {
            // Cancel the ability if the player chose to go back
            return;
        }

        // Reduce the player's actions by 1
        actions--;
        int damage = Output.printHeroAbilityCombatLog(target, hero, ability);
        target.takeDamage(damage);
        hero.adjustMana(-ability.getCost());

        // Remove the enemy from the list if it is dead
        if (target.isDead()) {
            removeEnemy(target);
        }
    }

    /**
     * Prompt the player to choose an enemy to attack or target with an ability.
     * @return the enemy the player chose to attack, or null if the player chose to go back.
     */
    private Enemies chooseTarget() {
        int input;
        while (true) {
            Output.printPromptHeader("Choose an enemy to attack!");
            for (int i = 0; i < enemies.size(); i++){
                System.out.println((i + 1) + ". " + enemies.get(i).getType() + " HP: " + enemies.get(i).getHealth());
            }
            System.out.println("0. Back");
            Output.printEnterNumberMessage();
            input = Validation.validateInput(scanner.nextLine());
            switch (input) {
                case -1 -> Output.printInvalidChoiceMessage();
                case 0 -> {
                    return null;
                }
                default -> {
                    if (input < 1 || input > enemies.size()){
                        Output.printInvalidChoiceMessage();
                    } else {
                        return enemies.get(input - 1);
                    }
                }
            }
        }
    }

    /**
     * Uses a consumable item from the inventory, does not consume an action if the item is not used.
     */
    private void useItem() {
        if (hero.useConsumable(true)) {
            actions--;
        }
    }

    /**
     * Removes an enemy from the list of enemies.
     * @param enemy the enemy to remove.
     */
    private void removeEnemy(Enemies enemy) {
        hero.getStats().addExperience(enemy.getDamage() * 4);
        enemies.remove(enemy);
    }

    /**
     * Attempts to run away from combat, consumes an action.
     * If the player is fighting the final boss, they cannot run away and action is not consumed.
     */
    private void run(){
        if (enemies.stream().anyMatch(enemies -> enemies.getBossTier() == Constants.VALUE_FINAL_BOSS_TIER)) {
            System.out.println("You can't run away from the final boss!");
            return;
        }
        actions--;
        if (Randomizer.rollD20() > Constants.VALUE_COMBAT_ESCAPE_CHANCE) {
            System.out.println("You run away!");
            isDefeated = true;
        } else {
            System.out.println("Escape failed! You are still in combat!");
        }
    }

    private void determineReward(){
        reward = 0;
        for (Enemies enemy : enemies){
            if (enemy.getBossTier() == Constants.VALUE_FINAL_BOSS_TIER)
                reward += Randomizer.rollD20(25);
            else if (enemy.getBossTier() == Constants.VALUE_MINI_BOSS_TIER)
                reward += Randomizer.rollD20(10);
            else if (enemy.getBossTier() == 0) {
                reward += Randomizer.rollD20(2);
            }
        }
    }
}
