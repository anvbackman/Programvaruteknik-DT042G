package support;

import gears.JsonLoader;

import java.util.HashMap;
import java.util.Objects;

/**
 * The GearHandler class. Used to retrieve gear values from the gearlist.json file.
 * Singleton class to ensure only one instance is created.
 * @author Emil Jönsson
 */
public final class GearHandler {

    private static final GearHandler INSTANCE = new GearHandler();
    private final HashMap<String, Integer> armor;
    private final HashMap<String, Integer> weapon;
    private final HashMap<String, Integer> consumable;

    /**
     * Loads gear values from the gearlist.json file.
     */
    private GearHandler() {
        String url = Objects.requireNonNull(getClass().getResource("/gearlist.json")).getPath();
        JsonLoader json = new JsonLoader(url);

        armor = json.getObject(Constants.GEAR_TYPE_ARMOR);
        weapon = json.getObject(Constants.GEAR_TYPE_WEAPON);
        consumable = json.getObject(Constants.GEAR_TYPE_CONSUMABLE);
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
     * @return the armor set as a HashMap.
     */
    public HashMap<String, Integer> getArmorSet() {
        return armor;
    }

    /**
     * Get the all weapons from the gearlist.json file.
     * @return the weapon set as a HashMap.
     */
    public HashMap<String, Integer> getWeaponSet() {
        return weapon;
    }

    /**
     * Get the all consumables from the gearlist.json file.
     * @return the consumable set as a HashMap.
     */
    public HashMap<String, Integer> getConsumableSet() {
        return consumable;
    }
}
