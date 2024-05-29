package scenarios;

import character.Hero;
import gears.Armor;
import gears.Consumables;
import gears.Gear;
import gears.Weapons;
import support.Constants;
import support.GearHandler;
import support.Output;
import support.Randomizer;
import support.Validation;

import java.util.List;
import java.util.Scanner;

/**
 * Shop class that represents a shop in the game. Handles shop displaying inventory and purchases.
 * Implements the Encounter interface to allow for execution in the game engine.
 * @author Emil Jönsson
 */
public class Shop implements Encounter{

    private final Scanner scanner;
    private final Hero hero;
    private final double priceModifier;
    private double discountModifier = 1;
    private List<Gear> armor;
    private List<Gear> weapon;
    private List<Gear> consumable;

    /**
     * Constructor for the shop scenario.
     * @param difficulty the difficulty of the game, affects shop prices.
     * @param scanner the scanner object to handle input.
     * @param hero the hero object to handle purchases and gold.
     */
    public Shop(final String difficulty, final Scanner scanner, final Hero hero) {
        this.scanner = scanner;
        this.hero = hero;
        priceModifier = Constants.VALUE_SHOP_PRICE_BASE_MODIFIER +
                Constants.DIFFICULTIES.indexOf(difficulty) * Constants.VALUE_SHOP_PRICE_DIFFICULTY_MODIFIER;
    }

    /**
     * Execute the shop scenario.
     * Will not refresh the shop inventory, if desired, call {@link #generateShop()} before execute.
     */
    @Override
    public void execute() {
        Output.printSuccessMessage("Welcome to the shop!");
        displayShop();
    }

    /**
     * Generate a new shop with a random size and inventory.
     */
    public void generateShop() {
        armor = Randomizer.selectRandom(GearHandler.getInstance().getArmorSet(), Randomizer.rollShopSize());
        weapon = Randomizer.selectRandom(GearHandler.getInstance().getWeaponSet(), Randomizer.rollShopSize());
        consumable = Randomizer.selectRandom(GearHandler.getInstance().getConsumableSet(), Randomizer.rollShopSize());
    }

    /**
     * Set the discount modifier for the shop. Value is multiplied with the current discount modifier. Defaults to 1.
     * @param newModifier the new discount modifier as a double decimal value i.e. 0.75 = 25% discount.
     */
    public void setDiscountModifier(double newModifier) {
        this.discountModifier = discountModifier * newModifier;
    }

    /**
     * Display shop menu to user and handle user input.
     */
    private void displayShop() {
        boolean proceed = false;
        int input;
        while (!proceed) {
            Output.printPromptHeader("What would you like to buy?");
            System.out.println("1. Armor");
            System.out.println("2. Weapons");
            System.out.println("3. Consumables");
            System.out.println("0. Exit");
            Output.printEnterNumberMessage();

            input = Validation.validateInput(scanner.nextLine());
            switch (input) {
                case 1 -> displayGear(armor, Constants.GEAR_TYPE_ARMOR);
                case 2 -> displayGear(weapon, Constants.GEAR_TYPE_WEAPON);
                case 3 -> displayGear(consumable, Constants.GEAR_TYPE_CONSUMABLE);
                case 0 -> proceed = true;
                default -> Output.printInvalidChoiceMessage();
            }
        }
    }

    /**
     * Calculate the price of a gear item based on the base price and the current price and discount modifiers.
     * @param basePrice the base price of the gear item.
     * @return the calculated price as an integer value.
     */
    private int calculatePrice(int basePrice) {
        return (int) (basePrice * priceModifier * discountModifier);
    }

    /**
     * Present selected gear category to user and handles purchases.
     * @param gearList list of gear to display.
     */
    private void displayGear(List<Gear> gearList, String gearCategory) {
        int input;
        Gear selectedGear;

        boolean proceed = false;
        // Loops until user selects to go back.
        while (!proceed) {
            System.out.printf("\nYou have %d gold.", hero.getGold());
            // Dialog based on gear category.
            switch (gearCategory) {
                case Constants.GEAR_TYPE_ARMOR -> {
                    System.out.printf("\nYour current armor: %s (%d Power)",
                            hero.getEquippedArmor().getName(),
                            hero.getEquippedArmor().getValue());
                    Output.printPromptHeader("Select a set of Armor to buy:");
                }
                case Constants.GEAR_TYPE_WEAPON -> {
                    System.out.printf("\nYour current weapon: %s (%d Power)",
                            hero.getEquippedWeapon().getName(),
                            hero.getEquippedWeapon().getValue());
                    Output.printPromptHeader("Select a Weapon to buy:");
                }
                case Constants.GEAR_TYPE_CONSUMABLE -> Output.printPromptHeader("Select a Consumable to buy:");
            }
            // Print available gear.
            for (int i = 0; i < gearList.size(); i++) {
                Gear gear = gearList.get(i);
                System.out.printf("%d. %s (%d Power) (%s%d Gold%s)\n", i + 1,
                        gear.getName(),
                        gear.getValue(),
                        Constants.COLOR_YELLOW,
                        calculatePrice(gear.getCost()),
                        Constants.COLOR_RESET
                );
            }
            System.out.println("0. Back");
            Output.printEnterNumberMessage();
            input = Validation.validateInput(scanner.nextLine());

            // Checks if user wants to go back.
            if (input == 0) {
                proceed = true;
            } else {
                // Checks if user input is a valid option.
                if (input < 1 || input > gearList.size()) {
                    Output.printInvalidChoiceMessage();
                } else {
                    selectedGear = gearList.get(input - 1);

                    // Checks if user has enough gold to purchase the selected gear.
                    if (!hero.addGold(-calculatePrice(selectedGear.getCost()))) {
                        Output.printErrorMessage("You do not have enough gold to purchase " + selectedGear.getName());
                    } else {
                        Output.printSuccessMessage("You have purchased " +
                                selectedGear.getName() +
                                " (-" + calculatePrice(selectedGear.getCost()) + " gold).");

                        // Removes the purchased gear from the shop inventory.
                        gearList.remove(input - 1);
                        Gear oldGear = null;
                        // Equips the gear if it is armor or weapon, otherwise adds it to consumables.
                        switch (selectedGear.getGearCategory()) {
                            case Constants.GEAR_TYPE_ARMOR -> {
                                oldGear = hero.setEquippedArmor((Armor) selectedGear);
                                armor.add(oldGear);
                            }
                            case Constants.GEAR_TYPE_WEAPON -> {
                                oldGear = hero.setEquippedWeapon((Weapons) selectedGear);
                                weapon.add(oldGear);
                            }
                            case Constants.GEAR_TYPE_CONSUMABLE -> hero.addConsumable((Consumables) selectedGear);
                        }

                        // Checks if the purchased gear was armor or weapon.
                        // If true, the replaced gear is added back to the shop inventory and sold for a reduced price.
                        if (oldGear != null) {
                            int sellPrice = (int) (calculatePrice(oldGear.getCost()) * Constants.VALUE_SHOP_SELL_BASE_MODIFIER);
                            hero.addGold(sellPrice);
                            Output.printSuccessMessage("You sold " + oldGear.getName() + " for " + sellPrice + " gold.");
                        }
                    }
                }
                // End of loop
            }
        }
    }
}
