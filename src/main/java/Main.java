/**
 * Main staring point of the game.
 * @author Emil Jönsson
 */
public class Main {

    /**
     * Main method to start the game.
     * @param args command line arguments.
     */
    public static void main(String... args) {

        GameEngine engine = new GameEngine();
        engine.init();
    }
}
