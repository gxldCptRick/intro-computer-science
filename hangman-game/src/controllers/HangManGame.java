package controllers;

import lib.ConsoleIO;

public class HangManGame {

	private static String wordToGuess;
	private static String scrambledWord;
	private static int wrongCount;
	private static boolean correctGuess;
	private static String[] charactersGuessed;

	public static void start() {
		boolean keepPlaying = true;
		do {
			wordToGuess = gatherWordToGuess();

			scrambleWord('_');

			wrongCount = 0;

			correctGuess = false;

			charactersGuessed = new String[6];

			runHangMan();

			keepPlaying = ConsoleIO.promptForBool("Would You like To Play Agian? (Y/N)", "Y", "N");

			if(!keepPlaying) {
			
			
				System.out.println("Thanks For Playing.");
			}
			
		} while (keepPlaying);
	}

	private static void scrambleWord(char mask) {

		char[] scrambler = wordToGuess.toCharArray();

		for (int i = 0; i < scrambler.length; i++) {
			if (Character.isAlphabetic(scrambler[i])) {
				scrambler[i] = mask;
			}
		}

		scrambledWord = new String(scrambler);

	}

	private static void runHangMan() {

		boolean gameActive = true;

		do {
			
			drawHangMan();
			
			displayIncorrectCharsGuessed();
			
			dispalyPuzzle();
			System.out.println();
			gameActive = guess();
			
		} while (gameActive);

		endGame();

	}
	
	private static void dispalyPuzzle() {
		String[] puzzles = scrambledWord.split(" ");
		char[] puzzleCharacters; 
		for(String puzzle : puzzles) {
			puzzleCharacters = puzzle.toCharArray();
			for(char c : puzzleCharacters) {
				
				System.out.print(c + " ");
				
			}
			System.out.print("  ");
			
		}
		
		System.out.println();
		
	}

	private static void endGame() {
		
		drawHangMan();
		
		System.out.println(wordToGuess);

		if (!correctGuess) {
			
			System.out.println("you lost.");
			
			System.out.println("Im sorry youngling...");
		
			String qwerty = "";

			for (String part : charactersGuessed) {
				qwerty += part;
			}

			if (qwerty.equalsIgnoreCase("qwerty")) {
			
				System.out.println("You found me...\nthank you young padiwan....");
			
			}
			
		} else {
			
			System.out.println("I love You....");
			
			System.out.println("Because You WIN!!!!");
		
		}	
	}

	private static boolean guess() {
		String[] options = { "Guess a Character", "Guess The Phrase", "Give Up" };
		int choice = ConsoleIO.promptForMenuSelection(options, false);
		boolean turnOff = false;

		switch (choice) {
		case 1:
			turnOff = guessCharacter();
			correctGuess = isSolved();
			break;
		case 2:
			turnOff = guessPhrase();

			if (!correctGuess) {
				wrongCount = 6;
			}

			break;
		case 3:
			wrongCount = 6;
			turnOff = true;
			break;
		default:
			break;
		}
		return !turnOff;
	}

	private static boolean guessPhrase() {

		String input = ConsoleIO.promptForInput("Input what you think the phrase is.", false);
		correctGuess = wordToGuess.equalsIgnoreCase(input);
		return true;

	}

	private static boolean guessCharacter() {

		String input = gatherValidInput();

		boolean correctGuess = checkGuess(input), addedGuess = correctGuess;

		for (int i = 0; i < charactersGuessed.length && !addedGuess; i++) {

			if (charactersGuessed[i] == null) {

				charactersGuessed[i] = input;

				addedGuess = true;

			}

		}

		if (!correctGuess) {
			wrongCount++;
		}

		return wrongCount > 5 || isSolved();
	}

	private static boolean checkGuess(String input) {

		boolean correctGuess = false;

		String[] words = wordToGuess.split("");

		char[] scrambledWord = HangManGame.scrambledWord.toCharArray();

		for (int i = 0; i < words.length; i++) {

			if (words[i].equalsIgnoreCase(input)) {

				scrambledWord[i] = words[i].charAt(0);

				correctGuess = true;

			}
		}

		HangManGame.scrambledWord = new String(scrambledWord);
		
		return correctGuess;
	}

	private static String gatherValidInput() {
		String input;
		boolean invalidInput = true;
		do {

			input = ConsoleIO.promptForInput("Guess a Character", false);

			invalidInput = input == null || input.length() != 1 || !(Character.isAlphabetic(input.charAt(0)));

			if (invalidInput) {

				System.out.println("Please give me a letter.");

			} else {

				for (int i = 0; i < charactersGuessed.length && !invalidInput; i++) {

					if (charactersGuessed[i] != null && charactersGuessed[i].equalsIgnoreCase(input)) {

						invalidInput = true;

					}

				}

				String[] words = scrambledWord.split("");
				for (int i = 0; i < words.length && !invalidInput; i++) {
					if (words[i].equalsIgnoreCase(input)) {
						invalidInput = true;
					}
				}

				if (invalidInput) {
					System.out.println("give me something you have never guessed before bruhh");
				}
			}

		} while (invalidInput);
		return input;
	}

	private static boolean isSolved() {
		boolean solved = true;
		String[] wordToGuess = HangManGame.wordToGuess.split("");
		String[] scrambledWord = HangManGame.scrambledWord.split("");
		
		for(int i = 0; i < wordToGuess.length && i < scrambledWord.length; i++) {
			
			if(!wordToGuess[i].equals(scrambledWord[i])) {
				solved = false;
			}
			
		}
		
		
		return solved;
	}

	private static void displayIncorrectCharsGuessed() {
		StringBuilder bob = new StringBuilder("Wrong characters Guessed:\n");

		for (String character : charactersGuessed) {
			if (character != null) {
				bob.append(character + " ");
			}
		}
		System.out.println(bob);

	}

	private static String gatherWordToGuess() {
		String input;
		boolean invalidInput = true;

		do {
			input = ConsoleIO.promptForInput("Enter a word or phrase to be guessed.", true);
			invalidInput = input.trim().isEmpty() || amountOfWords(input.trim()) > 8;

			if (invalidInput) {
				System.out.println("That was invalid please give me at least one word.\n" + "and less then 8 words");
			}
		} while (invalidInput);

		return input;

	}

	private static int amountOfWords(String word) {
		return word.split(" ").length;
	}

	private static void drawHangMan() {
		StringBuilder man = new StringBuilder();
		man.append("    ____\n    |  |\n    |\n    |\n    |\n    |\n  __|__");

		for (int i = 0; i < wrongCount; i++) {

			switch (i) {
			case 0:
				man.insert(23, " (0)");
				break;
			case 1:
				man.insert(33, "  | ");
				break;
			case 2:
				man.replace(34, 35, "/");
				break;
			case 3:
				man.replace(36, 37, "\\");
				break;
			case 4:
				man.insert(43, " /");
				break;
			case 5:
				man.insert(45, " \\");
				break;
			default:
				break;
			}
		}
		/* build the man */
		System.out.println(man);
	}

}
