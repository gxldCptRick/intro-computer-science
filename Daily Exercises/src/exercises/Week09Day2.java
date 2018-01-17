package exercises;

import lib.ConsoleIO;

public class Week09Day2 {

	public static void start() {
		String lineToWorkWith = validateInput();
		countWords(lineToWorkWith);
		printString(lineToWorkWith);
		reverseString(lineToWorkWith);
		yellTheString(lineToWorkWith);
		replaceAllVowels(lineToWorkWith);
		giveASCIIValue(lineToWorkWith);

	}

	private static String validateInput() {
		boolean invalidInput = true;
		String input = null;
		do {
			input = ConsoleIO.promptForInput("Give me a non-empty, non-null input", false);
			invalidInput = input == null;
		} while (invalidInput);
		return input;
	}

	private static void countWords(String lineOfWords) {
		int wordCount = lineOfWords.trim().split(" ").length;

		if (wordCount == 1) {
			if (lineOfWords.trim().isEmpty()) {
				System.out.println("You have given me no words");
			} else {
				System.out.println("You have given me " + wordCount + " word.");
			}
		} else {
			System.out.println("You have given me " + wordCount + " words.");
		}
	}

	private static void printString(String input) {
		System.out.println(input);
	}

	private static void reverseString(String input) {
		char[] reversingString = input.toCharArray();
		char temp;
		int endOfArray = input.length() - 1;
		for (int i = 0; i < input.length() / 2; i++) {
			temp = reversingString[i];

			reversingString[i] = reversingString[endOfArray - i];

			reversingString[endOfArray - i] = temp;

		}

		String reversedString = new String(reversingString);

		System.out.println(reversedString);
	}

	private static void yellTheString(String input) {
		System.out.println(input.toUpperCase());
	}

	private static void replaceAllVowels(String input) {
		String replaced = input.replace('a', 'y');
		replaced = replaced.replace('A', 'Y');
		replaced = replaced.replace('e', 'y');
		replaced = replaced.replace('E', 'Y');
		replaced = replaced.replace('i', 'y');
		replaced = replaced.replace('I', 'Y');
		replaced = replaced.replace('o', 'y');
		replaced = replaced.replace('O', 'Y');
		replaced = replaced.replace('u', 'y');
		replaced = replaced.replace('U', 'Y');

		System.out.println(replaced);
	}

	private static void giveASCIIValue(String input) {
		int asciiCount = 0;
		char[] allTheCharacters = input.toCharArray();

		for (char character : allTheCharacters) {
			asciiCount += character;
		}

		System.out.println("The total ASCII Value for all the String you have given me is " + asciiCount);
	}

}
