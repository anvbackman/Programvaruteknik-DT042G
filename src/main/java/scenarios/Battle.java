package scenarios;

import character.Hero;
import creator.Mission;
import enemies.Enemies;
import enemies.Goblin;
import enemies.Kobold;
import enemies.Zombie;
import support.Constants;
import support.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The battle class is a scenario that contains the encounters for a battle.
 * It contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 */
public class Battle implements Encounter {

    /**
     * The overarching mission for the scenario.
     */
    private final Mission mission;
    private final List<Enemies> enemies;
    private final int bossTier;

    /**
     * Constructor for the battle class.
     * @param mission the mission for the battle.
     */
    public Battle(final Mission mission, final Hero hero, final Scanner scanner, final int bossTier) {
        this.mission = mission;
        this.bossTier = bossTier;
        enemies = new ArrayList<>();
    }

    /**
     * Executes the encounters for the battle.
     */
    public void execute() {
        executeEncounter(mission.getDifficulty());
    }

    /**
     * Executes the encounter for the battle.
     * @param encounter the difficulty of the encounter.
     */
    private void executeEncounter(final String encounter){
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
        int randomEncounter = Randomizer.rollD4(1);
        switch (randomEncounter) {
            case 1 -> {
                System.out.println("""
                        You arrive at a clearing in the woods
                        A moment of calmness overcomes you, as you see a deer walking in to the clearing
                        Suddenly, you hear a rustling in the bushes and the deer falls over, with an arrow in their neck
                        You are ambushed by a group of goblins!
                        """);
                int GoblinAmount = Randomizer.rollD4(1);
                for(int i = 0; i < GoblinAmount; i++){
                    enemies.add(new Goblin(bossTier));
                }
            }

            case 2 -> {
                System.out.println("""
                        Treading through the murky swamp, you come across a small camp of Goblins
                        They seem to be preparing for something, and you can see a few of them sharpening their weapons
                        You are ambushed by a group of Goblins!
                        """);
                int GoblinAmount = Randomizer.rollD4(1);
                for(int i = 0; i < GoblinAmount; i++) {
                    enemies.add(new Goblin(bossTier));
                }
            }
            case 3 -> {
                System.out.println("""
                        You come across a small group of goblins, who are arguing over a shiny object
                        They seem to be distracted, offering you to draw first blood.
                        Roll Initiative!
                        """);
                int GoblinAmount = Randomizer.rollD4(1);
                for(int i = 0; i < GoblinAmount; i++) {
                    enemies.add(new Goblin(bossTier));
                }
            }
            case 4 -> {
                System.out.println("""
                        You come across a cottage,
                        as you approach the scent of blood fills the air as you approach the beaten path around the cottage
                        You can see a group of goblins feasting around the fire outside, celebrating their new home
                        As you hear a snap under your boot, and the goblins turn around to see you
                        Roll Initiative!
                        """);
                int GoblinAmount = Randomizer.rollD4(1);
                for(int i = 0; i < GoblinAmount; i++) {
                    enemies.add(new Goblin(bossTier));
                }

            }
            default -> System.out.println("You encounter nothing unusual.");
        }
    }

    /**
     * Generates the encounters for the medium difficulty.
     */
    private void generateEncountersForMissionMedium() {
        int randomEncounter = Randomizer.rollD4(1);
        switch (randomEncounter) {
            case 1 -> {
                System.out.println("""
                        The darkness of this dungeon depraves your senses as you stumble in the dark, your hands against the wall, you feel the telltale sign of an unlit torch against the wall
                        as you light the torch the fire illuminates the corridor, as you notice the snarling kobolds around you
                        Roll Initiative!
                        """);
                int KoboldAmount = Randomizer.rollD4(1);
                for(int i = 0; i < KoboldAmount; i++){
                    enemies.add(new Kobold(bossTier));
                }
            }
            case 2 -> {
                System.out.println("""
                        You come across a smithy inside of the dungeon, the forge has gone cold and most of the tools are missing,\s
                        as you investigate the area you hear the door close behind you as you are ambushed by a group of kobolds
                        roll Initiative!""");
                int KoboldAmount = Randomizer.rollD4(1);
                for(int i = 0; i < KoboldAmount; i++) {
                    enemies.add(new Kobold(bossTier));
                }
            }
            case 3 -> {
                System.out.println("""
                        You keep progressing through the dungeon, looking around the several rooms, but without finding anything,at times you can hear the sound of a kobold's laughter, but you never manage to see them
                        As you prepare your weapon, and approach the door at the end of the hallway, you realize the door is locked, but before you can turn around, you feel a stone hit your shoulder, and the kobolds launch their attack!
                        Roll Initiative!
                        """);
                int KoboldAmount = Randomizer.rollD4(1);
                for(int i = 0; i < KoboldAmount; i++) {
                    enemies.add(new Kobold(bossTier));
                }
            }
            case 4 -> {
                System.out.println("""
                        You come across a room with a large table, and a few kobolds are sitting around it, as you approach the room, you can see a map of the area on the table, and the kobolds seem to be discussing something
                        As you approach the table, the kobolds notice you and launch their attack!
                        Roll Initiative!
                        """);
                int KoboldAmount = Randomizer.rollD4(1);
                for(int i = 0; i < KoboldAmount; i++) {
                    enemies.add(new Kobold(bossTier));
                }
            }
            default -> System.out.println("You encounter nothing unusual.");
        }
    }

    /**
     * Generates the encounters for the hard difficulty.
     */
    private void generateEncountersForMissionHard() {
        int randomEncounter = Randomizer.rollD4(1);
        switch (randomEncounter) {
            case 1 -> {
                System.out.println("""
                        You come across a small village, the houses are in ruins, and the streets are empty
                        As you approach the center of the village, you can see a group of zombies feasting on a corpse
                        Roll Initiative!
                        """);

                int ZombieAmount = Randomizer.rollD4(1);
                for(int i = 0; i < ZombieAmount; i++) {
                    enemies.add(new Zombie(bossTier));
                }
            }
            case 2 -> {
                System.out.println("""
                        You come across a small group of zombies, shambling across the desolate town square
                        As you approach the group, you can see a few of them are wearing armor and carrying weapons
                        you manage to avoid most of them by diverting your path to an alley, after a short jaunt around the houses,
                        you hear the windows break and a group of zombies surrounds you, broken glass embedded in their faces
                        Roll Initiative!
                        """);
                int ZombieAmount = Randomizer.rollD4(1);
                for(int i = 0; i < ZombieAmount; i++) {
                    enemies.add(new Zombie(bossTier));
                }
            }
            case 3 -> {
                System.out.println("""
                        You notice a group of survivors escaping through the town gates, but with zombies hot on their trail
                        they do not notice you, and as the survivors closes the gates behind them, you have no option to escape... and the zombies turn to you
                         + You will need to fight your way out of this.
                        Roll Initiative!
                        """);
                int ZombieAmount = Randomizer.rollD4(1);
                for(int i = 0; i < ZombieAmount; i++) {
                    enemies.add(new Zombie(bossTier));
                }
            }
            case 4 -> {
                System.out.println("""
                        You approach The old temple in the center of the village, once a place of worship and peace, now a place of death and despair
                        The clerics had fought bravely, but being outnumbered even the temple fell eventually. As you approach the temple, the corpses of the clerics arise and ambush you.
                        Roll Initiative!
                        """);
                int ZombieAmount = Randomizer.rollD4(1);
                for(int i = 0; i < ZombieAmount; i++) {
                    enemies.add(new Zombie(bossTier));
                }
            }
            default -> System.out.println("You encounter nothing unusual.");
        }
    }

    /**
     * Getter for the enemies in the battle.
     * @return a list of enemies in the battle.
     */
    public List<Enemies> getEnemies() {
        return enemies;
    }
}

