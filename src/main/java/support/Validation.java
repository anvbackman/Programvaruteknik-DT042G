package support;

/**
 * Class for validation methods.
 * @author Emil Jönsson
 */
public class Validation {

    /**
     * Validates input from the user, input of a number value is expected. Other input is rejected.
     * @param input the input to validate.
     * @return the input as an integer, -1 if the input is invalid or empty.
     */
    public static int validateInput(String input) {
        int result = -1;
        if (!input.isEmpty()) {
            try {
                result = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.printf("%sPlease enter a number.%s\n",
                        Constants.COLOR_RED, Constants.COLOR_RESET);
                return -1;
            }
        } else {
            System.out.printf("%sPlease enter a number.%s\n",
                    Constants.COLOR_RED, Constants.COLOR_RESET);
        }
        return result;
    }
}
