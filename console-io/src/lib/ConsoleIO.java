package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO {

    // PRO TIP: Make sure to create a new BufferedReader in each method
    // where a BufferedReader is required. Do NOT close the reader as that will
    // cause
    // other issues. Also, catch ALL IOExceptions and NumberFormatExceptions.
    // Do not simply mark the method with a "throws" statement

    private static boolean checkIfIsDouble(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkIfIsLong(String num) {
        try {
            Long.parseLong(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Receives input and also handles IOException if an System.in Stream is closed
    private static String receiveInput(String message) {
        BufferedReader buffyTheVampireSlayer = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.printf("%s: ", message);
            return buffyTheVampireSlayer.readLine();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("an unexpected error occured");
            return null;
        }
    }

    /**
     * Allows You To Wait For user input by taking in input from the System.in stream and doing nothing to it.
     */
    public static void waitForInput() {
        BufferedReader buffyTheVampireSlayer = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Press Enter To Continue...");
        try {
            buffyTheVampireSlayer.readLine();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("an unexpected error occurred");
        }
    }


    /**
     * Generates a console-based menu using the Strings in options as the menu
     * items. Reserves the number 0 for the "quit" option when withQuit is true.
     *
     * @param options  - Strings representing the menu options
     * @param withQuit - adds option 0 for "quit" when true
     * @return the int of the selection made by the user
     */
    public static int promptForMenuSelection(String[] options, boolean withQuit) {
        int choice  = -1, lowerBound = 1;
        if(withQuit){
            var arr = new String[options.length + 1];
            arr[0] = "Quit";
            System.arraycopy(options, 0, arr, 1, arr.length);
            options = arr;
            lowerBound = 0;
        }
        displayScreen(options);
        return promptForInt("Please Select a Menu Item", lowerBound, options.length);
    }

    private static void displayScreen(String[] options){
        String message = "";
        for (int i = 0; i < options.length; i++) {
            if (options[i] != null) {
                message += (i) + ") " + options[i] + "\n";
            }
        }
        System.out.println(message);
    }

    /**
     * Generates a prompt that expects the user to enter one of two responses that
     * will equate to a boolean value. The trueString represents the case
     * insensitive response that will equate to true. The falseString acts
     * similarly, but for a false boolean value. Example: Assume this method is
     * called with a trueString argument of "yes" and a falseString argument of
     * "no". If the enters "YES", the method returns true. If the user enters "no",
     * the method returns false. All other inputs are considered invalid, the user
     * will be informed, and the prompt will repeat.
     *
     * @param prompt      - the prompt to be displayed to the user
     * @param trueString  - the case insensitive value that will evaluate to true
     * @param falseString - the case insensitive value that will evaluate to false
     * @return the boolean value
     */
    public static boolean promptForBool(String prompt, String trueString, String falseString) {
        if(prompt == null) throw new IllegalArgumentException("prompt cannot be null.");
        if(trueString == null) throw new IllegalArgumentException("trueString cannot be null.");
        if(falseString == null) throw new IllegalArgumentException("falseString cannot be null.");
        boolean isTrueString = true, invalidInput = true;
        String input = null;
        do {
            input = receiveInput(prompt);
            invalidInput = !(input.equalsIgnoreCase(trueString) || input.equalsIgnoreCase(falseString));
            if (invalidInput) {
                System.out.println("Please enter either " + trueString + " or " + falseString);
            } else {
                isTrueString = trueString.equalsIgnoreCase(input);
            }
        } while (invalidInput);
        return isTrueString;
    }

    /**
     * Generates a prompt that expects a numeric input representing a byte value.
     * This method loops until valid input is given.
     *
     * @param prompt - the prompt to be displayed to the user
     * @param min    - the inclusive minimum boundary
     * @param max    - the inclusive maximum boundary
     * @return the byte value
     */
    public static byte promptForByte(String prompt, byte min, byte max) {
        if(min > max) throw new IllegalArgumentException("min cannot be greater than the max");
        if(prompt == null) throw new IllegalArgumentException("prompt cannot be null.");
        byte result = (byte) promptForInt(prompt, min, max);
        return result;
    }

    /**
     * Generates a prompt that expects a numeric input representing a short value.
     * This method loops until valid input is given.
     *
     * @param prompt - the prompt to be displayed to the user
     * @param min    - the inclusive minimum boundary
     * @param max    - the inclusive maximum boundary
     * @return the short value
     */
    public static short promptForShort(String prompt, short min, short max) {
        if(min > max) throw new IllegalArgumentException("min cannot be greater than max.");
        if(prompt == null) throw new IllegalArgumentException("prompt cannot be null");
        short result = (short) promptForInt(prompt, min, max);
        return result;
    }

    /**
     * Generates a prompt that expects a numeric input representing an int value.
     * This method loops until valid input is given.
     *
     * @param prompt - the prompt to be displayed to the user
     * @param min    - the inclusive minimum boundary
     * @param max    - the inclusive maximum boundary
     * @return the int value
     */
    public static int promptForInt(String prompt, int min, int max) {
        if(min > max) throw new IllegalArgumentException("min cannot be greater than max.");
        if(prompt == null) throw new IllegalArgumentException("prompt cannot be null.");
        int result = (int) promptForLong(prompt, min, max);
        return result;
    }

    /**
     * Generates a prompt that expects a numeric input representing a long value.
     * This method loops until valid input is given.
     *
     * @param prompt - the prompt to be displayed to the user
     * @param min    - the inclusive minimum boundary
     * @param max    - the inclusive maximum boundary
     * @return the long value
     */
    public static long promptForLong(String prompt, long min, long max) {
        long result = -1;
        boolean invalidInput = false;
        String input = null;
        if(min > max) throw new IllegalArgumentException("min cannot be greater than max.");
        if(prompt == null) throw new IllegalArgumentException("prompt cannot be null.");
        do {
            input = receiveInput(prompt);
            if (checkIfIsLong(input)) {
                result = Long.parseLong(input);
                invalidInput = result < min || result > max;
                if (invalidInput) {
                    System.out.println("Please input a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Please input a valid number.");
            }
        }while (invalidInput);

        return result;

    }

    /**
     * Generates a prompt that expects a numeric input representing a float value.
     * This method loops until valid input is given.
     *
     * @param prompt - the prompt to be displayed to the user
     * @param min    - the inclusive minimum boundary
     * @param max    - the inclusive maximum boundary
     * @return the float value
     */
    public static float promptForFloat(String prompt, float min, float max) {
        if(min > max) throw new IllegalArgumentException("min cannot be greater than max.");
        float result = (float) promptForDouble(prompt, min, max);
        return result;
    }

    /**
     * Generates a prompt that expects a numeric input representing a double value.
     * This method loops until valid input is given.
     *
     * @param prompt - the prompt to be displayed to the user
     * @param min    - the inclusive minimum boundary
     * @param max    - the inclusive maximum boundary
     * @return the double value
     */
    public static double promptForDouble(String prompt, double min, double max) {
        double result = -1;
        boolean invalidInput = true;
        String input = null;
        if(min > max) throw new IllegalArgumentException("min cannot be greater than max.");
        if(prompt == null) throw new IllegalArgumentException("prompt cannot be null.");

        do{
            input = receiveInput(prompt + String.format("(%f-%f)", min, max));
            if (checkIfIsDouble(input)) {
                result = Double.parseDouble(input);
                invalidInput = result < min || result > max;
                if (invalidInput) {
                    System.out.println("Please Enter a double value between " + min + " and " + max);
                }
            } else {
                System.out.println("Please Enter a valid double value.");
            }
        }while (invalidInput);

        return result;
    }

    /**
     * Generates a prompt that allows the user to enter any response and returns the
     * String. When allowEmpty is true, empty responses are valid. When false,
     * responses must contain at least one character (including whitespace).
     *
     * @param prompt     - the prompt to be displayed to the user.
     * @param allowEmpty - when true, makes empty responses valid
     * @return the input from the user as a String
     */
    public static String promptForInput(String prompt, boolean allowEmpty) {
        String input = null;
        boolean invalidInput = true;
        do {
            input = receiveInput(prompt);
            if (allowEmpty) {
                invalidInput = input == null;
            } else {
                invalidInput = input == null || input.isEmpty();
            }
            if (invalidInput) {
                System.out.println("Please input a valid string.");
            }
        } while (invalidInput);
        return input;

    }

    /**
     * Generates a prompt that expects a single character input representing a char
     * value. This method loops until valid input is given.
     *
     * @param prompt - the prompt to be displayed to the user
     * @param min    - the inclusive minimum boundary
     * @param max    - the inclusive maximum boundary
     * @return the char value
     */
    public static char promptForChar(String prompt, char min, char max) {

        char charmander = '-';
        boolean invalidInput = false;
        String input = null;
        int minChar = (int) min, maxChar = (int) max, inputChar = 0;

        if(minChar > maxChar) throw new IllegalArgumentException("min cannot be greater than max.");
        if(prompt == null) throw new IllegalArgumentException("prompt cannot be null.");
        do{
            input = receiveInput(prompt + String.format("(%s-%s)", min, max));
            invalidInput = input.length() != 1;
            if (invalidInput) {
                System.out.println("Please Enter only one character");
            } else {
                charmander = input.charAt(0);
                inputChar = charmander;
                invalidInput = inputChar < minChar || inputChar > maxChar;
                if (invalidInput) {
                    System.out.println("Please Enter a Character that value is between " + min + " and " + max);
                }
            }
        }while (invalidInput);
        return charmander;
    }

}
