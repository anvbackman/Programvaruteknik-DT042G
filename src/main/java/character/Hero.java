package character;

import abilities.BaseAbility;
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

    /** The name of the character. */
    private final String name;

    /** The stat sheet of the character. */
    private final StatSheet stats;

    /** The character class of the character. */
    private final String characterClass;

    /** The currently equipped armor of the character. */
    private Armor equippedArmor;

    /** The currently equipped weapon of the character. */
    private Weapons equippedWeapon;

    /** The consumables the character has. */
    private final List<Consumables> consumables;

    /** The gold the character has. */
    private int gold;

    /** The health of the character. */
    private int health;

    /** The maximum health of the character. */
    private int maxHealth;

    /** The mana pool of the character. */
    private int manaPool;

    /** The maximum mana of the character. */
    private int maxMana;

    /** The ability of the character. */
    private BaseAbility ability;

    /**
     * Constructor for a character.
     * @param statSheet the stat sheet for the character.
     * @param heroName the name of the character.
     * @param heroClass the class of the character.
     */
    public Hero(
            final StatSheet statSheet,
            final String heroName,
            final String heroClass
    ) {
        this.name = heroName;
        this.stats = statSheet;
        this.characterClass = heroClass;
        this.gold = Constants.VALUE_CHARACTER_STARTING_GOLD;
        this.getMaxHealth();
        this.health = maxHealth;
        this.getMaxMana();
        this.manaPool = maxMana;
        this.equippedWeapon = new Weapons(
                Constants.PLAYER_STARTING_WEAPON, 1, 0);
        this.equippedArmor = new Armor(
                Constants.PLAYER_STARTING_ARMOR, 1, 0);
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
     * Returns the defense of the character.
     * @return the defense value of the character.
     */
    public int getDefense() {
        return equippedArmor.getValue();
    }

    /**
     * Returns the attack of the character.
     * @return the attack value of the character.
     */
    public int getAttack() {
        return equippedWeapon.getValue() +
                stats.getStat(Constants.STAT_STRENGTH);
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
    public Armor setEquippedArmor(final Armor armor) {
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
    public Weapons setEquippedWeapon(final Weapons weapon) {
        Weapons oldWeapon = equippedWeapon;
        this.equippedWeapon = weapon;
        return oldWeapon;
    }

    /**
     * Opens the menu to use a consumable.
     * @param inCombat true if the character is in combat, else false.
     * @return true if the consumable was used, else false.
     */
    public boolean useConsumable(final boolean inCombat) {

        if (consumables.isEmpty()) {
            Output.printErrorMessage("You have no consumables.");
            return false;
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
                    return false;
                }
                case -1 -> Output.printInvalidChoiceMessage();
                default -> {
                    Consumables consumable = consumables.get(input - 1);
                    if (consumable.getName().contains(Constants.CONSUMABLE_TYPE_HEALTH)) {
                        System.out.println("You used " + consumable.getName()
                                + " and healed for " + consumable.getValue() + " HP.");
                        this.adjustHealth(consumable.getValue());
                    } else if (consumable.getName().contains(Constants.CONSUMABLE_TYPE_MANA)) {
                        System.out.println("You used " + consumable.getName() +
                                " and restored " + consumable.getValue() + " MP.");
                        this.adjustMana(consumable.getValue());
                    }
                    consumables.remove(consumable);
                    if (consumables.isEmpty() || inCombat) {
                        return true;
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
     * @param amount the amount of gold to add or subtract.
     * @return true if the gold amount was successfully modified, else false.
     */
    public boolean addGold(final int amount) {
        if (this.gold + amount < 0) {
            return false;
        }
        this.gold += amount;
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
     * Retrieves the current maximum health of the character.
     * @return the maximum health of the character.
     */
    public int getMaxHealth() {
        this.maxHealth = Constants.VALUE_CHARACTER_STARTING_HEALTH + stats.getStat(Constants.STAT_CONSTITUTION);
        return maxHealth;
    }

    /**
     * Applies healing to the character.
     * @param amount the amount of healing to apply.
     */
    public void adjustHealth(final int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    /**
     * Returns the mana pool of the character.
     * @return the current mana pool of the character.
     */
    public int getManaPool() {
        return manaPool;
    }

    /**
     * Retrieves the current maximum mana of the character.
     * @return the maximum mana of the character.
     */
    public int getMaxMana() {
        this.maxMana = Constants.VALUE_CHARACTER_STARTING_MANA + stats.getStat(Constants.STAT_INTELLIGENCE);
        return maxMana;
    }

    /**
     * Adds mana to the character's mana pool.
     *
     * @param mana the amount of mana to add.
     */
    public void adjustMana(final int mana) {
        manaPool += mana;
        if (manaPool > maxMana) {
            manaPool = maxMana;
        }
    }

    /**
     * Returns the character class of the character.
     * @return the character class string identifier of the character.
     */
    public String getCharacterClass() {
        return characterClass;
    }

    /**
     * Returns the ability of the character.
     * @return the ability object of the character.
     */
    public BaseAbility getAbility() {
        return ability;
    }

    /**
     * Sets the ability of the character.
     * @param heroAbility the ability to assign to the character.
     */
    public void setAbility(final BaseAbility heroAbility) {
        this.ability = heroAbility;
    }

    /**
     * Returns the level of the character from the stat sheet.
     * @return the level of the character.
     */
    public int getLevel() {
        return stats.getLevel();
    }
}
