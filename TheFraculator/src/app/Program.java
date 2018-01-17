package app;

import models.Fraction;

public class Program {

	// checks to see if i can convert string to integer
	public static boolean isInt(String input) {
		boolean isInteger = true;
		try {
			Integer.parseInt(input);
		} catch (Exception e) {
			isInteger = false;
		}

		return isInteger;
	}

	// validates input for me
	public static boolean validateInput(String input) {

		String[] inputs = input.split(" "), vals;

		for (String val : inputs) {

			vals = val.split("/");

			if (vals.length > 1) {
				for (String subInput : vals) {
					if (!isInt(subInput)) {
						return false;
					}
				}
			} else {
				if (!isInt(val)) {
					System.out.println("invalid input");
					return false;
				}
			}
		}

		return true;

	}

	// processes input for me
	public static Fraction processInput(String input) {
		Fraction output;
		int wholeNum = 0, den = 1, num = 0;
		String[] inputs = input.split(" ", 2), values;
		for (String value : inputs) {
			values = value.split("/");
			if (values.length > 1) {

				num = Integer.parseInt(values[0]);
				den = Integer.parseInt(values[1]);

			} else
				wholeNum = Integer.parseInt(value);
		}

		output = new Fraction(num, den, wholeNum);
		return output;
	}

	// process all mathematical output
	public static void processEquation(Fraction val1, Fraction val2, char operation) {
		Fraction outputFrac = new Fraction();
		switch (operation) {
		case '+':
			outputFrac = val1.add(val2);
			break;
		case '-':
			outputFrac = val1.subtract(val2);
			break;
		case '/':
			outputFrac = val1.divide(val2);
			break;
		case '*':
			outputFrac = val1.multiply(val2);
			break;
		}
		
		outputFrac.simplify();
		
		System.out.println(val1.toString() +" "+ operation +" "+val2.toString()
		+" = "+ outputFrac.toString() +" or " + outputFrac.getDecimalValue());

	}

	// main method of my program
	public static void main(String[] args) {
//		// all variables used in the main method
//		boolean active = true, math = true, inputValid = false, mathValidInput = false;
//		String inputString;
//		String[] inputs;
//		char key, operation = ' ';
//		Fraction val1, val2;
//		// main program, active used to see whether or not program still active
//		while (active) {
//			System.out.println("Main Menu");
//			System.out.println("Select a Option");
//			System.out.println("1. (Reduce) Fraction");
//			System.out.println("2. (Math) with Fractions");
//			System.out.println("3. (Exit)");
//			math = true;
//			mathValidInput = false;
//			inputValid = false;
//			// input for main menu
//			inputString = input.nextLine();
//			// check for choice made based on 3 distinct values
//			if (inputString.equals("reduce") || inputString.equals("Reduce") || inputString.equals("1")) {
//				// reducing fractions menu options
//				System.out.println("Reducing Fractions");
//				System.out.println("Input fraction to reduce.");
//				System.out.println("EX. 2134/33");
//				// input for reducing fractions
//
//				inputString = input.nextLine();
//
//				while (!validateInput(inputString)) {
//
//					System.out.println("Please input a valid fraction.");
//					inputString = input.nextLine();
//
//				}
//
//				val1 = processInput(inputString);
//				
//				// simplifies fraction
//				val1.simplify();
//				
//				// outputs both fraction and decimal for the values given
//				System.out.println(val1.toString() + " or " + val1.getDecimalValue());
//
//				// waits for nothing so that user can see the output clearly
//
//				inputString = input.nextLine();
//
//			} else if (inputString.equals("math") || inputString.equals("Math") || inputString.equals("2")) {
//				while (math) {
//					// math with fractions menu
//					System.out.println("Math with Fractions");
//					System.out.println("Select an Option.");
//					System.out.println("1. (A)ddition");
//					System.out.println("2. (S)ubtraction");
//					System.out.println("3. (M)ultiplication");
//					System.out.println("4. (D)ivision");
//					System.out.println("5. (R)eturn to Main Menu");
//					System.out.println("6. (E)xit");
//
//					// validates input for the fractions menu
//
//					while (!inputValid) {
//						inputString = input.nextLine();
//						key = inputString.charAt(0);
//						inputValid = true;
//
//						switch (key) {
//						case 'a':
//						case 'A':
//							operation = '+';
//							break;
//						case 's':
//						case 'S':
//							operation = '-';
//							break;
//						case 'm':
//						case 'M':
//							operation = '*';
//							break;
//						case 'd':
//						case 'D':
//							operation = '/';
//							break;
//						case 'r':
//						case 'R':
//							math = false;
//							mathValidInput = true;
//							operation = ' ';
//							break;
//						case 'e':
//						case 'E':
//							math = false;
//							active = false;
//							operation = ' ';
//							mathValidInput = true;
//							System.out.println("GoodBye");
//							break;
//						default:
//							inputValid = false;
//
//							System.out.println("please select a valid option");
//
//							System.out.println("1. (A)ddition");
//							System.out.println("2. (S)ubtraction");
//							System.out.println("3. (M)ultiplication");
//							System.out.println("4. (D)ivision");
//							System.out.println("5. (R)eturn to Main Menu");
//							System.out.println("6. (E)xit");
//
//						}
//					}
//					// checks to see if you chose to not do any math
//					if (operation != ' ') {
//
//						// checks whether or not the input is both valid and contains two fractions
//
//						while (!mathValidInput) {
//
//							System.out.println("Please input two fractions.");
//							System.out.println("Ex. 2/3, 9 4/5");
//
//							inputString = input.nextLine();
//							inputs = inputString.split(", ");
//
//							if (!(inputs.length < 2)) {
//								while (!validateInput(inputs[0]) && !validateInput(inputs[2])) {
//
//									System.out.println("Please input two valid fractions.");
//									System.out.println("EX. 1/2, 5 1/4");
//
//									inputString = input.nextLine();
//									inputs = inputString.split(", ");
//
//								}
//								// transforms the string into two fractions objects
//								val1 = processInput(inputs[0]);
//								val2 = processInput(inputs[1]);
//
//								// outputs the equation based on operation and two fractional value
//
//								processEquation(val1, val2, operation);
//								mathValidInput = true;
//							} else {
//								System.out.println("Please try to put at least two fractions");
//								mathValidInput = false;
//								inputValid = false;
//							}
//						}
//						// asks for validation to continue
//						System.out.println("Would You like To Continue.");
//
//						System.out.println("Yes or No");
//
//						inputString = input.nextLine();
//
//						// checks for answer and determines which loops to close and open.
//
//						if (inputString.equals("no")) {
//							math = false;
//						} else {
//							inputValid = false;
//							mathValidInput = false;
//						}
//						
//					}
//
//				}
//			} else if (inputString.equals("exit") || inputString.equals("Exit") || inputString.equals("3")) {
//
//				// turns off loops
//				active = false;
//				
//				// output to verify the termination of program.
//				System.out.println("Goodbye");
//
//			}else {
//				System.out.println("Invalid Input Select an option");
//			}
//
//		}
//		// closes stream for no data leak
//		input.close();

	}

}
