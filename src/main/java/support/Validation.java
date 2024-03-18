package support;

/**
 * Class for validation methods.
 * @author Emil Jönsson
 */
public class Validation {

    /**
     * Validates the input from the user.
     * @param input the input to validate.
     * @return the input as an integer or -1 if the input is invalid.
     */
    public static int validateInput(String input) {
        int index = -1;
        if (!input.isEmpty()) {
            try {
                index = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.printf("%sPlease enter a number.%s\n",
                        Constants.COLOR_RED, Constants.COLOR_RESET);
                return -1;
            }
        } else {
            System.out.printf("%sPlease enter a number.%s\n",
                    Constants.COLOR_RED, Constants.COLOR_RESET);
        }
        return index;
    }
}
