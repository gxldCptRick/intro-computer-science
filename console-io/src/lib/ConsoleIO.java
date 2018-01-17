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

	private static boolean checkIfIsFloat(String num) {
		try {
			Float.parseFloat(num);
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

	private static boolean checkIfIsInt(String num) {
		try {

			Integer.parseInt(num);

			return true;

		} catch (NumberFormatException e) {

			return false;

		}
	}

	private static boolean checkIfIsShort(String num) {
		try {

			Short.parseShort(num);

			return true;

		} catch (NumberFormatException e) {

			return false;

		}
	}

	private static boolean checkIfIsByte(String num) {
		try {

			Byte.parseByte(num);

			return true;

		} catch (NumberFormatException e) {

			return false;

		}
	}

	// Receives input and also handles IOException if an System.in Stream is closed
	private static String recieveInput() {
		 BufferedReader buffyTheVampireSlayer = new BufferedReader(new InputStreamReader(System.in));
		
		try {

			return buffyTheVampireSlayer.readLine();

		} catch (IOException e) {

			System.out.println(e);

			System.out.println("an unexpected error occured");

			return null;

		}
	}
/**
 * Allows You To Wait For user input by taking in input from the System.in stream and doing nothing to it.
 * 
 * */
	public static void waitForInput() {
		
		BufferedReader buffyTheVampireSlayer = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Press Enter To Continue...");
		
		try {
			
			buffyTheVampireSlayer.readLine();
			
		}catch(IOException e) {
			
			System.out.println(e);
			
			System.out.println("an unexpected error occured");
			
		}
	}
	

	/**
	 * Generates a console-based menu using the Strings in options as the menu
	 * items. Reserves the number 0 for the "quit" option when withQuit is true.
	 * 
	 * @param options
	 *            - Strings representing the menu options
	 * @param withQuit
	 *            - adds option 0 for "quit" when true
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String[] options, boolean withQuit) {
		int choice = -1;
		boolean invalidInput = true;
		String message = "", input = null, errorMessage = null;
		if (options != null && options.length != 0) {
			for (int i = 0; i < options.length; i++) {

				if(options[i] != null) {
				
					message += (i + 1) + ") " + options[i] + "\n\n";
				
				}
			}
			if (withQuit) {
				message += "0) Quit\n\n";
			}

			message += "Please Select an Option: ";

			do {

				System.out.print(message);

				input = recieveInput();

				invalidInput = !checkIfIsInt(input);

				if (invalidInput) {

					errorMessage = "Please input a valid number";

					System.out.println(errorMessage);


				} else {

					choice = Integer.parseInt(input);

					invalidInput = true;

					errorMessage = "Please input a number between ";

					if (!(choice < 0) && withQuit) {

						if (choice > options.length) {

							errorMessage += "0 and " + options.length;

						} else {

							invalidInput = false;

						}

					} else if (choice < 1 || choice > options.length) {

						errorMessage += "1 and " + options.length;

					} else {

						invalidInput = false;

					}

					if (invalidInput) {

						System.out.println(errorMessage);

					

					}

				}

			} while (invalidInput);
		} else {
			System.out.println("I need some menu items to work with. " + "\nReturning -1 for now.");
		}

		return choice;
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
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param trueString
	 *            - the case insensitive value that will evaluate to true
	 * @param falseString
	 *            - the case insensitive value that will evaluate to false
	 * @return the boolean value
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString) {

		boolean isTrueString = true, invalidInput = true;

		String input = null;

		do {

			System.out.println(prompt);

			input = recieveInput();

			invalidInput = !(input.equalsIgnoreCase(trueString) || input.equalsIgnoreCase(falseString));

			if (invalidInput) {

				System.out.println("Please enter either " + trueString + " or " + falseString);

			} else {

				if (input.equalsIgnoreCase(trueString)) {

					isTrueString = true;

				} else if (input.equalsIgnoreCase(falseString)) {

					isTrueString = false;

				}

			}

		} while (invalidInput);

		return isTrueString;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the byte value
	 */
	public static byte promptForByte(String prompt, byte min, byte max) {
		boolean invalidInput = false;
		String input = null, errorMessage = "returning -1 until you fix the method call";
		byte result = -1;

		if (min > max) {

			System.out.println("the mininum value can't be bigger than the maximum value");

			System.out.println(errorMessage);

		} else {

			invalidInput = true;

		}

		while (invalidInput) {

			System.out.println(prompt);

			input = recieveInput();

			if (checkIfIsByte(input)) {

				result = Byte.parseByte(input);

				invalidInput = result > max || result < min;

				if (invalidInput) {

					System.out.println("Please input a byte that is between " + min + " and " + max);

				}

			} else {

				System.out.println("Please input a valid byte");

				invalidInput = true;

			}

		}

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the short value
	 */
	public static short promptForShort(String prompt, short min, short max) {
		boolean invalidInput = false;
		String input = null;
		short result = -1;

		if (min > max) {

			System.out.println("The maximum Value can't be less than the minimum value");

			System.out.println("Returning -1 until you resolve issue with method decleration");

		} else {
			invalidInput = true;
		}

		while (invalidInput) {

			System.out.println(prompt);

			input = recieveInput();

			if (checkIfIsShort(input)) {

				result = Short.parseShort(input);

				invalidInput = result < min || result > max;

				if (invalidInput) {
					System.out.println("Please input a short value between " + min + " and " + max);
				}

			} else {
				System.out.println("Please input a valid short value");
			}

		}

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the int value
	 */
	public static int promptForInt(String prompt, int min, int max) {
		boolean invalidInput = false;
		int result = -1;
		String input;

		if (min > max) {

			System.out.println("You can't have the minimum value be greater than the maximum value.");

			System.out.println("Returning -1 until you fix the method call.");

		} else {

			invalidInput = true;

		}

		while (invalidInput) {

			System.out.println(prompt);

			input = recieveInput();

			if (checkIfIsInt(input)) {

				result = Integer.parseInt(input);

				invalidInput = result > max || result < min;

				if (invalidInput) {

					System.out.println("Please enter a number between " + min + " and " + max);

				}

			} else {

				System.out.println("Please input a valid integer.");

			}

		}

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the long value
	 */
	public static long promptForLong(String prompt, long min, long max) {
		long result = -1;
		boolean invalidInput = false;
		String input = null;

		if (min > max) {
			System.out.println("The maximum value must be greater than min.");
			System.out.println("returning -1 until you fix the method call.");
		} else {
			invalidInput = true;
		}

		while (invalidInput) {

			System.out.println(prompt);

			input = recieveInput();

			if (checkIfIsLong(input)) {

				result = Long.parseLong(input);

				invalidInput = result < min || result > max;

				if (invalidInput) {

					System.out.println("Please input a number between " + min + " and " + max + ".");

				}

			} else {

				System.out.println("Please input a valid Long.");

			}

		}

		return result;

	}

	/**
	 * Generates a prompt that expects a numeric input representing a float value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the float value
	 */
	public static float promptForFloat(String prompt, float min, float max) {
		float result = -1;
		boolean invalidInput = false;
		String input = null;

		if (min > max) {

			System.out.println("The min cant be greater than the max.");

			System.out.println("Returning -1 until you fix the method call.");

		} else {

			invalidInput = true;

		}

		while (invalidInput) {

			System.out.println(prompt);

			input = recieveInput();

			if (checkIfIsFloat(input)) {

				result = Float.parseFloat(input);

				invalidInput = result > max || result < min;

				if (invalidInput) {

					System.out.println("Please enter a float between " + min + " and " + max);

				}

			} else {
				System.out.println("Please enter a valid float.");
			}

		}

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the double value
	 */
	public static double promptForDouble(String prompt, double min, double max) {
		double result = -1;
		boolean invalidInput = false;
		String input = null;

		if (min > max) {

			System.out.println("");

		} else {

			invalidInput = true;

		}

		while (invalidInput) {

			System.out.println(prompt);

			input = recieveInput();

			if (checkIfIsDouble(input)) {

				result = Double.parseDouble(input);

				invalidInput = result < min || result > max;

				if (invalidInput) {

					System.out.println("Please Enter a double value between " + min + " and " + max);

				}

			} else {

				System.out.println("Please Enter a valid double value.");

			}

		}

		return result;
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the
	 * String. When allowEmpty is true, empty responses are valid. When false,
	 * responses must contain at least one character (including whitespace).
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user.
	 * @param allowEmpty
	 *            - when true, makes empty responses valid
	 * @return the input from the user as a String
	 */
	public static String promptForInput(String prompt, boolean allowEmpty) {
		String input = null;
		boolean invalidInput = true;

		do {
			System.out.println(prompt);

			input = recieveInput();

			if (allowEmpty) {

				invalidInput = input == null;

			} else {

				invalidInput = input == null || input.isEmpty();

			}

			if (invalidInput) {

				System.out.println("Please input a valid String value.");

			}

		} while (invalidInput);

		return input;

	}

	/**
	 * Generates a prompt that expects a single character input representing a char
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the char value
	 */
	public static char promptForChar(String prompt, char min, char max) {
		
		char charmander = '-';
		boolean invalidInput = false;
		String input = null;
		int minChar = (int) min, maxChar = (int) max, inputChar = 0;
		
		if(minChar > maxChar) {
			System.out.println("The Minimum character value can't be larger than the maximum.");
			System.out.println("Returning '-' until you fix the method call.");
		}
		else {
			invalidInput = true;
		}
		
		while(invalidInput){
			
			System.out.println(prompt);
			
			input = recieveInput();
			invalidInput = input.length() != 1;
			
			if(invalidInput) {
			
				System.out.println("Please Enter only one character");
			
			}else {
				
				charmander = input.charAt(0);
				
				inputChar = (int)charmander;
				
				invalidInput = inputChar < minChar || inputChar > maxChar;
				
				if(invalidInput) {
					System.out.println("Please Enter a Chacter that value is between " + min + " and " + max);
				}
				
				
			}
		
		}
		
		return charmander;
	}

}
