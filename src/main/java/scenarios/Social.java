package scenarios;

import character.Hero;
import creator.Mission;
import gears.Consumables;
import support.Constants;
import support.Output;
import support.Randomizer;
import support.Validation;

import java.util.Scanner;

/**
 * The social class is a scenario that contains the encounters for a social encounter.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 */
public class Social implements Encounter {

    /**
     * The overarching mission for the scenario.
     */
    private Mission mission;
    private Hero hero;
    private Scanner scanner;

    /**
     * Constructor for the social class.
     * @param mission the mission for the social.
     * @param hero the hero for the social.
     * @param scanner the input scanner.
     */
    public Social(Mission mission, Hero hero, Scanner scanner) {
        this.mission = mission;
        this.hero = hero;
        this.scanner = scanner;
    }

    /**
     * The encounter for the injured guardsman.
     */
    private final Runnable injuredGuardsmanEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                            You notice in the corner of your eye, an injured guardsman from the village, he is lying on the ground, and he seems to be in pain.
                            As you approach him, he looks at you with a pained expression, and asks for your help.
                            """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Ignore the guardsman.");
            boolean altActionAvailable_1 = false;
            boolean altActionAvailable_2 = false;
            if (hero.getConsumables().stream().anyMatch(consumable -> consumable.getName().toLowerCase().contains("healing"))) {
                altActionAvailable_1 = true;
                System.out.println("2. Give the guardsman one of your healing potions.");
            } else {
                System.out.printf("%s2. Give the guardsman one of your healing potions. (No healing potion in inventory)%s\n",
                        Constants.COLOR_GRAY, Constants.COLOR_RESET);
            }

            if (hero.getCharacterClass().equals(Constants.CLASS_PALADIN)) {
                altActionAvailable_2 = true;
                System.out.println("3. Use magic to heal the guardsman.");
            } else {
                System.out.printf("%s3. Use magic to heal the guardsman. (Requires paladin class)%s\n",
                        Constants.COLOR_GRAY, Constants.COLOR_RESET);
            }
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    System.out.println("You ignore the guardsman and continue on your way.");
                    proceed = true;
                }
                case 2 -> {
                    if (altActionAvailable_1) {
                        System.out.println("You give the guardsman one of your healing potions, and he thanks you.");
                        hero.removeConsumable(
                                hero.getConsumables()
                                        .stream()
                                        .filter(consumable ->
                                                consumable
                                                        .getName()
                                                        .toLowerCase()
                                                        .contains("healing")
                                        ).toList().get(0));
                    } else {
                        System.out.println("You do not have any healing potions to give to the guardsman.");
                    }
                    proceed = true;
                }
                case 3 -> {
                    if (altActionAvailable_2) {
                        System.out.println("You use your magic to heal the guardsman, and he thanks you.");
                    } else {
                        System.out.println("You do not have the required class to use magic to heal the guardsman.");
                    }
                    proceed = true;
                }
                default -> Output.printInvalidChoiceMessage();
            }
        }
    };

    /**
     * The encounter for the merchant.
     */
    private final Runnable merchantEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                            You come across a trader followed with a band of caravan guards, who is willing to trade with you, he looks at you with a smile, and offers you a deal.
                            
                            "Hello there, can I interest you in my wares?"
                            """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Ignore the merchant.");
            System.out.println("2. Trade with the merchant.");
            System.out.println("3. Attempt to rob the merchant (Dexterity Check)");
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    // Proceed.
                    System.out.println("You ignore the merchant and continue on your way.");
                    proceed = true;
                }
                case 2 -> {
                    // Create new shop.
                    Shop shop = new Shop(Constants.DIFFICULTY_EASY, scanner, hero);
                    shop.generateShop();
                    // Apply discount.
                    shop.setDiscountModifier(0.7);
                    shop.execute();
                    System.out.println("You finish trading and continue on your way.");
                    proceed = true;
                }
                case 3 -> {
                    // Roll chance for success.
                    int bonus = ((hero.getStats().getStat(Constants.STAT_DEXTERITY) - 10) / 2);
                    if ((Randomizer.rollD20() + bonus) < 12) {
                        System.out.println("You attempt to rob the merchant, but he catches you and calls on his guards.");
                        hero.reduceHealth(Randomizer.rollD6(1));
                    } else {
                        // Roll amount of gold to steal
                        int gold = Randomizer.rollD20(10);
                        hero.addGold(gold);
                        System.out.printf("You successfully rob the merchant of %s%d%s gold!\n",
                                Constants.COLOR_YELLOW,
                                gold,
                                Constants.COLOR_RESET);
                    }
                    proceed = true;
                }
                default -> Output.printInvalidChoiceMessage();
            }
        }
    };

    /**
     * The encounter for the survivors encountered by the player..
     */
    private final Runnable survivorEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                            You come across a small group of survivors, they are huddled together, and they look at you with a mix of fear and hope.
                            They ask you for your help, and they offer you a reward if you help them.
                            """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Ignore the survivors.");
            System.out.println("2. Help the survivors.");
            System.out.println("3. P̴U̶T̶ ̸T̶H̸E̷M̶ ̷O̷U̶T̴ ̸O̸F̷ ̷T̸H̵E̶I̷R̷ ̶M̵I̴S̸E̶R̶Y̶");
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    System.out.println("You ignore the survivors and continue on your way.");
                    proceed = true;
                }
                case 2 -> {
                    System.out.println("You help the survivors, and they thank you for your kindness.");
                    hero.addGold(Randomizer.rollD20(10));
                    if (hero.getCharacterClass().equals(Constants.CLASS_PALADIN) || hero.getCharacterClass().equals(Constants.CLASS_CLERIC)) {
                        hero.applyHealing(100);
                        System.out.println("You feel a sense of divine power, and you feel rejuvenated, A divine gift from your patron.");
                        int gold = (Randomizer.rollD20(10));
                        hero.addGold(gold);
                        System.out.printf("You are fully healed and receive %s%d%s gold!\n",
                                Constants.COLOR_YELLOW,
                                gold,
                                Constants.COLOR_RESET);
                    }
                }
                case 3 -> {
                System.out.println("You decide to put the survivors out of their misery, and you kill them all...");
                if(hero.getCharacterClass().equals(Constants.CLASS_WARLOCK)){
                    System.out.println("You feel a sense of power, and you feel rejuvenated, A dark gift from your patron.");
                    int gold = (Randomizer.rollD20(10));
                    hero.addGold(gold);
                    System.out.printf("You are fully healed and receive %s%d%s gold!\n",
                            Constants.COLOR_YELLOW,
                            gold,
                            Constants.COLOR_RESET);
                }
                else{
                    int gold = (Randomizer.rollD20(10));
                    hero.addGold(gold);
                    System.out.printf("You search through their remains and receive %s%d%s gold!\n Who cares about morals when there is gold involved?\n",
                            Constants.COLOR_YELLOW,
                            gold,
                            Constants.COLOR_RESET);
                }
                    proceed = true;
                }
                default -> Output.printInvalidChoiceMessage();
            }
        }
    };

    /**
     * The encounter for the toll.
     */
    private final Runnable tollEncounter = () -> {
        boolean proceed = false;
        System.out.println("""
                You come across a group of bandits, they are not hostile, but they are blocking the path and tells you that you need to pay a toll to pass.
                They look at you with a mix of greed and malice, and they offer you a deal.
                Money for passage.
                """);
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Pay the toll. (20g)");
            System.out.println("2. Try to sneak past the bandits.(Dexterity Check)");
            System.out.println("3. Attack the bandits.(Strength Check)");
            if (hero.getClass().toString() == "barbarian")
                System.out.println("4. Intimidate the bandits.");
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {

                    System.out.println("You pay the toll and continue on your way.");
                    hero.addGold(-20);
                    proceed = true;
                }
                case 2 -> {
                    int bonus = ((hero.getStats().getStat(Constants.STAT_DEXTERITY) - 10) / 2);
                    int danger = Randomizer.rollD20(1);
                    if (danger + bonus <= 14) {
                        System.out.println("You rolled = " + danger + " + " + bonus + " = " + (danger + bonus));
                        System.out.println("You sneak past the bandits, and they don't notice you.");
                    } else {
                        System.out.println("You rolled = " + danger + " + " + bonus + " = " + (danger + bonus));
                        System.out.println("You try to sneak past the bandits, but they notice you and attack you!");
                        hero.reduceHealth(Randomizer.rollD6(1));
                    }
                    proceed = true;
                }
                case 3 -> {
                    int bonus = ((hero.getStats().getStat(Constants.STAT_STRENGTH)- 10) / 2);
                    int danger = Randomizer.rollD20(1);
                    if (danger + bonus <= 14) {
                        System.out.println("You rolled = " + danger + " + " + bonus + " = " + (danger + bonus));
                        System.out.println("You attack the bandits, and they fight back!");
                        System.out.println("You are injured during the fight, but manage to run past them in the chaos");
                        hero.reduceHealth(Randomizer.rollD6(2));
                    } else {
                        System.out.println("You rolled = " + danger + " + " + bonus + " = " + (danger + bonus));
                        System.out.println("You strike at their leader, and quickly dispatch him, the rest of the bandits flee in terror.");
                        hero.reduceHealth(Randomizer.rollD6(2));
                    }
                    proceed = true;

                }
                case 4 -> {
                    System.out.println("You let out a furious roar, slamming your weapon against the ground and the bandits flee in terror.");
                    proceed = true;
                }
                default -> Output.printInvalidChoiceMessage();
            }
        }
    };

    /**
     * The encounter for the ghost.
     */
    private final Runnable ghostEncounter = () -> {
        System.out.println("""
                You enter an abandoned shop, but as you search through the shelves, you hear a voice behind you.
                You turn around and see a ghostly figure, who looks at you with a mix of sadness and anger.
                "Thou hast to pay for thine items, or I shall curse thee!"
                """);
        boolean proceed = false;
        while (!proceed) {
            Output.printPromptHeader("What do you do?");
            System.out.println("1. Pay the ghost. (20g)");
            System.out.println("2. Attack the ghost.");
            System.out.println("3. Run away.");
            if(hero.getCharacterClass().equals(Constants.CLASS_PALADIN))
                System.out.println("4. Use magic to banish the ghost.");
            switch (Validation.validateInput(scanner.nextLine())) {
                case 1 -> {
                    System.out.println("You pay the ghost and it fades away.");
                    hero.addGold(-20);
                    hero.addConsumable(new Consumables("Healing Potion", 1, 1));
                    hero.addConsumable(new Consumables("Mana Potion", 1, 1));
                    proceed = true;
                }
                case 2 -> {
                    System.out.println("You attack the ghost, but your weapon passes through it, and it curses you.");
                    hero.reduceHealth(Randomizer.rollD6(2));
                    proceed = true;
                }
                case 3 -> {
                    System.out.println("You run away from the ghost, leaving the supplies behind and it fades away.");
                    proceed = true;
                }
                case 4 -> {
                    if(hero.getCharacterClass().equals(Constants.CLASS_PALADIN)){
                        System.out.println("You use your magic to banish the ghost, and it fades away.");
                        hero.addConsumable(new Consumables("Healing Potion", 1, 1));
                        hero.addConsumable(new Consumables("Mana Potion", 1, 1));
                        proceed = true;
                    }
                }
                default -> Output.printInvalidChoiceMessage();
            }
        }
    };

    /**
     * Executes the encounters for the social.
     */
    public void execute() {
        executeEncounter(mission.getDifficulty());
    }

    /**
     *  Executes the encounter for the social.
     * @param encounter the difficulty of the encounter, in this case it will still yield the same result, but it might be changed.
     */
    private void executeEncounter(String encounter){
        switch (encounter) {
            case Constants.DIFFICULTY_EASY -> generateEncountersForMissionEasy();
            case Constants.DIFFICULTY_MEDIUM -> generateEncountersForMissionMedium();
            case Constants.DIFFICULTY_HARD -> generateEncountersForMissionHard();
            default -> System.out.println("No encounter");
        }
    }

    /**
     * Generates the encounters for the easy difficulty.
     */
    private void generateEncountersForMissionEasy() {
        int randomEncounter = Randomizer.rollD4();
        switch (randomEncounter) {
            case 1, 2 -> merchantEncounter.run();
            case 3, 4 -> injuredGuardsmanEncounter.run();
            default -> System.out.println("You encounter nothing unusual.");
        }
    }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        int randomEncounter = Randomizer.rollD6(1);
        switch (randomEncounter) {
            case 1, 2, 3 ->
                    merchantEncounter.run();
            case 4, 5, 6 ->
                    survivorEncounter.run();

            default -> System.out.println("You encounter nothing unusual.");
        }
    }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        int randomEncounter = Randomizer.rollD6(1);
        switch (randomEncounter) {
            case 1, 2, 3 -> tollEncounter.run();
            case 4, 5, 6 -> ghostEncounter.run();
            default -> System.out.println("You encounter nothing unusual.");
        }
    }
}
