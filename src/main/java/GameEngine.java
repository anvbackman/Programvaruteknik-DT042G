import support.Constants;
import support.Validation;

import java.util.Scanner;

public class GameEngine {

    private final Scanner scanner;
    private Mission mission;
    private Character character;

    public GameEngine() {
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        System.out.println("Game Engine initialized.");
        CharacterCreator characterCreator = new CharacterCreator();

        int input;
        boolean proceed = false;

        while (!proceed) {
            System.out.println("1. Create a character");
            System.out.println("2. Exit");
            input = Validation.validateInput(scanner.nextLine());
            switch (input) {
                case 1 -> {
                    this.character = characterCreator.createCharacter();
                    proceed = true;
                }
                case 2 -> {
                    return;
                }
                default -> System.out.printf("%sInvalid choice.%s\n",
                        Constants.COLOR_RED, Constants.COLOR_RESET);
            }
        }

        proceed = false;
        while (!proceed) {
            System.out.println("Choose a mission length:");
            System.out.println("1. Short");
            System.out.println("2. Medium");
            System.out.println("3. Long");
            System.out.printf("%sEnter a number: %s", Constants.COLOR_YELLOW, Constants.COLOR_RESET);

            input = Validation.validateInput(scanner.nextLine());
            proceed = true;

            switch (input) {
                case 1 -> this.mission = new Mission(1, Constants.VALUE_MISSION_LENGTH_SHORT);
                case 2 -> this.mission = new Mission(2, Constants.VALUE_MISSION_LENGTH_MEDIUM);
                case 3 -> this.mission = new Mission(3, Constants.VALUE_MISSION_LENGTH_LONG);
                default -> {
                    System.out.printf("%sInvalid choice.%s\n",
                            Constants.COLOR_RED, Constants.COLOR_RESET);
                    proceed = false;
                }
            }
        }
        this.mission.generateMission();
    }
}
