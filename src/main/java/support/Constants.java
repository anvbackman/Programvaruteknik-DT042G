package support;

/**
 * Class for program constants.
 * @author Emil Jönsson
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Color codes.
    public final static String COLOR_RED = "\u001B[31m";
    public final static String COLOR_GREEN = "\u001B[32m";
    public final static String COLOR_YELLOW = "\u001B[33m";
    public final static String COLOR_BLUE = "\u001B[34m";
    public final static String COLOR_PURPLE = "\u001B[35m";
    public final static String COLOR_CYAN = "\u001B[36m";
    public final static String COLOR_RESET = "\u001B[0m";

}
