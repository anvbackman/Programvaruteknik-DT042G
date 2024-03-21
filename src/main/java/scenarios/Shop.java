package scenarios;

import gears.Armor;
import gears.Consumables;
import gears.Gear;
import gears.Weapons;
import support.Constants;
import support.Output;
import support.Randomizer;
import support.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Shop class that represents a shop in the game. Handles shop displaying inventory and purchases.
 * Implements the Encounter interface to allow for execution in the game engine.
 * @author Emil Jönsson
 */
public class Shop implements Encounter{

    private final Scanner scanner;
    private final double priceModifier;
    private double discountModifier = 1;
    private List<Gear> armor;
    private List<Gear> weapon;
    private List<Gear> consumable;

    /**
     * Constructor for the shop scenario.
     * @param difficulty the difficulty of the game, affects shop prices.
     * @param scanner the scanner object to handle input.
     */
    public Shop(String difficulty, Scanner scanner) {
        this.scanner = scanner;
        priceModifier = Constants.VALUE_SHOP_PRICE_BASE_MODIFIER +
                Constants.DIFFICULTIES.indexOf(difficulty) * Constants.VALUE_SHOP_PRICE_DIFFICULTY_MODIFIER;
    }

    /**
     * Execute the shop scenario.
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
        int shopSize = Randomizer.rollShopSize();
        // TODO preliminary shop inventory until gear is fully implemented
        // TODO randomly generate stat values for gear and add price values.
        armor = new ArrayList<>();
        weapon = new ArrayList<>();
        consumable = new ArrayList<>();

        for (int i = 0; i < shopSize; i++) {
            armor.add(new Armor("Armor"));
            weapon.add(new Weapons("Weapon"));
            consumable.add(new Consumables("Consumable"));
        }
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

            input = Validation.validateInput(scanner.nextLine());
            switch (input) {
                case 1 -> displayGear(armor);
                case 2 -> displayGear(weapon);
                case 3 -> displayGear(consumable);
                case 0 -> proceed = true;
                default -> Output.printInvalidChoiceMessage();
            }
        }
    }

    private int calculatePrice(int basePrice) {
        return (int) (basePrice * priceModifier * discountModifier);
    }

    /**
     * Present selected gear category to user and handle purchase.
     * @param gearList list of gear to display.
     */
    private void displayGear(List<Gear> gearList) {
        int input;
        boolean proceed = false;
        Gear selectedGear;
        while (!proceed) {
            for (int i = 0; i < gearList.size(); i++) {
                Gear gear = gearList.get(i);
                System.out.printf("%d. %s\n", i + 1, gear.getType());
                System.out.printf("\tPrice: %d\n", calculatePrice(10));
            }
            System.out.println("0. Exit");
            input = Validation.validateInput(scanner.nextLine());

            if (input == 0) {
                proceed = true;
            } else

            if (input < 1 || input > gearList.size()) {
                Output.printInvalidChoiceMessage();
            } else {
                selectedGear = gearList.get(input - 1);
                Output.printSuccessMessage("You have purchased " + selectedGear.getType());
                gearList.remove(input - 1);
                proceed = true;
                // TODO implement inventory and money handling.
            }
        }
    }
}
