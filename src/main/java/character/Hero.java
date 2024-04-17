package character;

import gears.Armor;
import gears.Consumables;
import gears.Weapons;
import support.Constants;
import support.Output;
import support.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class to represent a character in the game.
 * @author Emil Jönsson
 */
public class Hero {

    private final String name;
    private final StatSheet stats;
    private final String characterClass;
    private Armor equippedArmor;
    private Weapons equippedWeapon;
    private final List<Consumables> consumables;
    private int gold;
    private int health;
    private int manaPool;

    /**
     * Constructor for a character.
     * @param statSheet the stat sheet for the character.
     * @param name the name of the character.
     */
    public Hero(final StatSheet statSheet, final String name, final String characterClass) {
        this.name = name;
        this.stats = statSheet;
        this.characterClass = characterClass;
        this.gold = Constants.VALUE_CHARACTER_STARTING_GOLD;
        this.health = Constants.VALUE_CHARACTER_STARTING_HEALTH;
        this.manaPool = Constants.VALUE_CHARACTER_STARTING_MANA;
        this.equippedWeapon = new Weapons(Constants.PLAYER_STARTING_WEAPON, 1, 0);
        this.equippedArmor = new Armor(Constants.PLAYER_STARTING_ARMOR, 1, 0);
        this.consumables = new ArrayList<>();
    }

    /**
     * Returns the name of the character.
     * @return the name of the character as String value.
     */
    public String getName() {
        return name;
    }

    /**
     * Displays the inventory of the character.
     */
    public void showInventory() {
        System.out.printf("\n[Weapon] %s | %d Power\n",
                equippedWeapon.getName(), equippedWeapon.getValue());
        System.out.printf("[Armor] %s | %d Power\n",
                equippedArmor.getName(), equippedArmor.getValue());
        System.out.printf("[Gold] %d\n", gold);
        System.out.print("[Consumables] ");
        for (Consumables consumable : consumables) {
            System.out.print(consumable.getName() + " ");
            if (consumables.indexOf(consumable) != consumables.size() - 1) {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    /**
     * Returns the stat sheet of the character.
     * @return the stat sheet object of the character.
     */
    public StatSheet getStats() {
        return stats;
    }


    /**
     * Returns the equipped armor of the character.
     * @return the currently equipped armor.
     */
    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    /**
     * Sets the equipped armor of the character.
     * @param armor the armor to equip.
     * @return the armor that was previously equipped.
     */
    public Armor setEquippedArmor(Armor armor) {
        Armor oldArmor = equippedArmor;
        this.equippedArmor = armor;
        return oldArmor;
    }

    /**
     * Returns the equipped weapon of the character.
     * @return the currently equipped weapon.
     */
    public Weapons getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Sets the equipped weapon of the character.
     * @param weapon the weapon to equip.
     * @return the weapon that was previously equipped.
     */
    public Weapons setEquippedWeapon(Weapons weapon) {
        Weapons oldWeapon = equippedWeapon;
        this.equippedWeapon = weapon;
        return oldWeapon;
    }

    /**
     * Opens the menu to use a consumable.
     */
    public void openUseConsumableMenu() {

        if (consumables.isEmpty()) {
            Output.printErrorMessage("You have no consumables.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            System.out.println("Select a consumable to use:");
            for (int i = 0; i < consumables.size(); i++) {
                System.out.printf("%d. %s (%d %s)\n",
                        i + 1,
                        consumables.get(i).getName(),
                        consumables.get(i).getValue(),
                        consumables.get(i).getName().contains(Constants.CONSUMABLE_TYPE_HEALTH) ? "HP" : "MP");
            }
            System.out.println("0. Back");
            Output.printEnterNumberMessage();
            input = Validation.validateInput(scanner.nextLine());

            switch (input) {
                case 0 -> {
                    return;
                }
                case -1 -> Output.printInvalidChoiceMessage();
                default -> {
                    Consumables consumable = consumables.get(input - 1);
                    if (consumable.getName().contains(Constants.CONSUMABLE_TYPE_HEALTH)) {
                        System.out.println("You used " + consumable.getName() +
                                " and healed for " + consumable.getValue() + " HP.");
                        this.applyHealing(consumable.getValue());
                    } else if (consumable.getName().contains(Constants.CONSUMABLE_TYPE_MANA)) {
                        System.out.println("You used " + consumable.getName() +
                                " and restored " + consumable.getValue() + " MP.");
                        this.addMana(consumable.getValue());
                    }
                    consumables.remove(consumable);
                    if (consumables.isEmpty()) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * Returns the consumables of the character.
     * @return list of consumables the character has.
     */
    public List<Consumables> getConsumables() {
        return consumables;
    }

    /**
     * Adds a consumable to the character's inventory.
     * @param consumable the consumable to add.
     */
    public void addConsumable(Consumables consumable) {
        consumables.add(consumable);
    }

    /**
     * Removes a consumable from the character's inventory.
     * @param consumable the consumable to remove.
     */
    public void removeConsumable(Consumables consumable) {
        consumables.remove(consumable);
    }

    /**
     * Returns the gold-amount of the character.
     * @return the current amount of gold the character has.
     */
    public int getGold() {
        return gold;
    }

    /**
     * Modifies the gold-amount of the character.
     * @param gold the amount of gold to add or subtract.
     * @return true if the gold amount was successfully modified, else false.
     */
    public boolean addGold(int gold) {
        if (this.gold + gold < 0) {
            return false;
        }
        this.gold += gold;
        return true;
    }

    /**
     * Returns the health of the character.
     * @return the current health of the character.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Applies healing to the character.
     * @param healing the amount of healing to apply.
     * @return the new health value of the character.
     */
    public int applyHealing(int healing) {
        health += healing;
        if (health > Constants.VALUE_CHARACTER_STARTING_HEALTH) {
            health = Constants.VALUE_CHARACTER_STARTING_HEALTH;
        }
        return health;
    }

    /**
     * Reduces the health of the character.
     * @param damage the amount of damage to reduce the health by.
     * @return the new health value of the character.
     */
    public int reduceHealth(int damage) {
        health -= damage;
        return health;
    }

    /**
     * Returns the mana pool of the character.
     * @return the current mana pool of the character.
     */
    public int getManaPool() {
        return manaPool;
    }

    /**
     * Adds mana to the character's mana pool.
     * @param mana the amount of mana to add.
     * @return the new mana pool value of the character.
     */
    public int addMana(int mana) {
        manaPool += mana;
        if (manaPool > Constants.VALUE_CHARACTER_STARTING_MANA) {
            manaPool = Constants.VALUE_CHARACTER_STARTING_MANA;
        }
        return manaPool;
    }

    /**
     * Reduces the mana pool of the character.
     * @param mana the amount of mana to reduce the mana pool by.
     */
    public int reduceMana(int mana) {
        manaPool -= mana;
        return manaPool;
    }

    /**
     * Returns the character class of the character.
     * @return the character class string identifier of the character.
     */
    public String getCharacterClass() {
        return characterClass;
    }
}
