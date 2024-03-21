package support;

import java.util.List;

/**
 * Container for constant values used throughout the program.
 * @author Emil Jönsson
 */
public final class Constants {

    // -- String identifiers --
    public final static String STAT_STRENGTH = "Strength";
    public final static String STAT_DEXTERITY = "Dexterity";
    public final static String STAT_CONSTITUTION = "Constitution";
    public final static String STAT_INTELLIGENCE = "Intelligence";
    public final static String STAT_WISDOM = "Wisdom";
    // List of above stats.
    public final static List<String> STATS = List.of(
            STAT_STRENGTH,
            STAT_DEXTERITY,
            STAT_CONSTITUTION,
            STAT_INTELLIGENCE,
            STAT_WISDOM
    );
    public final static String DIFFICULTY_EASY = "Easy";
    public final static String DIFFICULTY_MEDIUM = "Medium";
    public final static String DIFFICULTY_HARD = "Hard";
    // List of above difficulties.
    public final static List<String> DIFFICULTIES = List.of(
            DIFFICULTY_EASY,
            DIFFICULTY_MEDIUM,
            DIFFICULTY_HARD
    );
    public final static String GEAR_TYPE_WEAPON = "weapons";
    public final static String GEAR_TYPE_ARMOR = "armor";
    public final static String GEAR_TYPE_CONSUMABLE = "consumables";
    public final static String GEAR_TYPE_ARTIFACT = "artifact";

    // -- Value constants --
    public final static int VALUE_BASE_STAT_DICE_ROLLS = 4;
    public final static int VALUE_MAX_STAT_REROLLS = 3;
    public final static int VALUE_MISSION_LENGTH_SHORT = 3;
    public final static int VALUE_MISSION_LENGTH_MEDIUM = 6;
    public final static int VALUE_MISSION_LENGTH_LONG = 9;
    public final static int VALUE_MISSION_LENGTH_VARIANCE = 3;
    public final static int VALUE_MISSION_FORK_AMOUNT_MIN = 1;
    public final static int VALUE_MISSION_FORK_AMOUNT_MAX = 3;
    public final static int VALUE_MINI_BOSS_ENCOUNTER_CHANCE = 8;
    public final static int VALUE_SHOP_INITIAL_SIZE = 3;
    public final static int VALUE_SHOP_SIZE_VARIANCE = 3;
    public final static int VALUE_SHOP_PRICE_BASE_MODIFIER = 1;
    public final static double VALUE_SHOP_PRICE_DIFFICULTY_MODIFIER = 0.25;
    public final static double VALUE_SHOP_SELL_BASE_MODIFIER = 0.5;

    // -- Color codes --
    public final static String COLOR_RED = "\u001B[31m";
    public final static String COLOR_GREEN = "\u001B[32m";
    public final static String COLOR_RESET = "\u001B[0m";
    public final static String COLOR_BLUE = "\u001B[34m";
    public final static String COLOR_PURPLE = "\u001B[35m";
    public final static String COLOR_YELLOW = "\u001B[33m";
    public final static String COLOR_GRAY = "\u001B[37m";

    // -- Character classes --
    public final static String CLASS_BARBARIAN = "Barbarian";
    public final static String CLASS_BARD = "Bard";
    public final static String CLASS_CLERIC = "Cleric";
    public final static String CLASS_DRUID = "Druid";
    public final static String CLASS_FIGHTER = "Fighter";
    public final static String CLASS_MONK = "Monk";
    public final static String CLASS_PALADIN = "Paladin";
    public final static String CLASS_RANGER = "Ranger";
    public final static String CLASS_ROGUE = "Rogue";
    public final static String CLASS_SORCERER = "Sorcerer";
    public final static String CLASS_WARLOCK = "Warlock";
    // List of above classes.
    public final static List<String> CLASSES = List.of(
            CLASS_BARBARIAN,
            CLASS_BARD,
            CLASS_CLERIC,
            CLASS_DRUID,
            CLASS_FIGHTER,
            CLASS_MONK,
            CLASS_PALADIN,
            CLASS_RANGER,
            CLASS_ROGUE,
            CLASS_SORCERER,
            CLASS_WARLOCK
    );

    // -- Mission types --
    public final static String MISSION_TYPE_COMBAT = "Combat";
    public final static String MISSION_TYPE_SOCIAL = "Social";
    public final static String MISSION_TYPE_REST = "Rest";
    public final static String MISSION_TYPE_PUZZLE = "Puzzle";
    public final static String MISSION_TYPE_MYSTERY = "Mystery";
    // Non-special missions
    public final static List<String> MISSION_TYPES_BASIC = List.of(
            MISSION_TYPE_COMBAT,
            MISSION_TYPE_SOCIAL,
            MISSION_TYPE_REST,
            MISSION_TYPE_PUZZLE,
            MISSION_TYPE_MYSTERY
    );
    // Higher risk missions
    public final static String MISSION_TYPE_MINI_BOSS = "Miniboss";
    public static final String MISSION_TYPE_BOSS = "Boss";
    //
    public final static List<String> MISSION_TYPES_AVAILABLE_AS_MYSTERY = List.of(
            MISSION_TYPE_COMBAT,
            MISSION_TYPE_SOCIAL,
            MISSION_TYPE_REST,
            MISSION_TYPE_PUZZLE,
            MISSION_TYPE_MINI_BOSS
    );
}
