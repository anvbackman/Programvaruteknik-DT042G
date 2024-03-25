package support;

import gears.Armor;
import gears.Consumables;
import gears.Gear;
import gears.JsonLoader;
import gears.Weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The GearHandler class. Used to retrieve gear values from the gearlist.json file.
 * Singleton class to ensure only one instance is created.
 * @author Emil Jönsson
 */
public final class GearHandler {

    private static final GearHandler INSTANCE = new GearHandler();
    private final List<Gear> armor;
    private final List<Gear> weapon;
    private final List<Gear> consumable;

    /**
     * Loads gear values from the gearlist.json file.
     */
    private GearHandler() {
        String url = Objects.requireNonNull(getClass().getResource("/gearlist.json")).getPath();
        JsonLoader json = new JsonLoader(url);

        armor = new ArrayList<>();
        weapon = new ArrayList<>();
        consumable = new ArrayList<>();

        json.getTypes(Constants.GEAR_TYPE_ARMOR).forEach(type -> armor.add(
                new Armor(
                        type,
                        json.getValue(Constants.GEAR_TYPE_ARMOR, type),
                        json.getCost(Constants.GEAR_TYPE_ARMOR, type))
                )
        );
        json.getTypes(Constants.GEAR_TYPE_WEAPON).forEach(type -> weapon.add(
                new Weapons(
                        type,
                        json.getValue(Constants.GEAR_TYPE_WEAPON, type),
                        json.getCost(Constants.GEAR_TYPE_WEAPON, type))
                )
        );
        json.getTypes(Constants.GEAR_TYPE_CONSUMABLE).forEach(type -> consumable.add(
                new Consumables(
                        type,
                        json.getValue(Constants.GEAR_TYPE_CONSUMABLE, type),
                        json.getCost(Constants.GEAR_TYPE_CONSUMABLE, type))
                )
        );
    }

    /**
     * Get the instance of the GearHandler class.
     * @return the instance of the GearHandler class.
     */
    public static GearHandler getInstance()
    {
        return INSTANCE;
    }

    /**
     * Get the all armor from the gearlist.json file.
     *
     * @return the armor set as a HashMap.
     */
    public List<Gear> getArmorSet() {
        return armor;
    }

    /**
     * Get the all weapons from the gearlist.json file.
     *
     * @return the weapon set as a HashMap.
     */
    public List<Gear> getWeaponSet() {
        return weapon;
    }

    /**
     * Get the all consumables from the gearlist.json file.
     *
     * @return the consumable set as a HashMap.
     */
    public List<Gear> getConsumableSet() {
        return consumable;
    }
}
