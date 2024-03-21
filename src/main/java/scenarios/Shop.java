package scenarios;

import gears.Gear;
import support.Constants;
import support.Randomizer;

import java.util.List;

public class Shop implements Encounter{

    private List<Gear> shopInventory;
    private int shopSize;
    private double priceModifier;
    private double discountModifier = 1;

    public Shop(String difficulty) {
        priceModifier = Constants.VALUE_SHOP_BASE_PRICE_MODIFIER +
                Constants.DIFFICULTIES.indexOf(difficulty) * Constants.VALUE_SHOP_DIFFICULTY_MODIFIER;

        generateShop();
    }
    @Override
    public void execute() {
    }

    public void generateShop() {
        this.shopSize = Randomizer.rollShopSize();


    }
}
