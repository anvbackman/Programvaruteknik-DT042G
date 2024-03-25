package character;

import gears.Armor;
import gears.Consumables;
import gears.Weapons;
import support.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a character in the game.
 * @author Emil Jönsson
 */
public class Hero {

    private final String name;
    private final StatSheet stats;
    private Armor equippedArmor;
    private Weapons equippedWeapon;
    private List<Consumables> consumables;
    private int gold;

    /**
     * Constructor for a character.
     * @param statSheet the stat sheet for the character.
     * @param name the name of the character.
     */
    public Hero(final StatSheet statSheet, final String name) {
        this.name = name;
        this.stats = statSheet;
        this.gold = Constants.VALUE_CHARACTER_STARTING_GOLD;
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


}
