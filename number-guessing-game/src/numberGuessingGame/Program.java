package numberGuessingGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Program {

	public final static BufferedReader buffyTheVampireSlayer = new BufferedReader(new InputStreamReader(System.in));

	public static boolean isInt(String num) {
		try {

			Integer.parseInt(num);

			return true;

		} catch (NumberFormatException e) {

			System.out.println("not a number");
			return false;

		}
	}

	public static void waitForEnter() throws IOException {
		System.out.println("Press Enter to Continue...");
		buffyTheVampireSlayer.readLine();
	}

	public static String menu() throws IOException {
		String input = null;
		System.out.println("Welcome to the Number Guessing Game!!!!" + "\n");
		System.out.println("Please Select your difficulty" + "\n(1) Easy \nGuess a number between 1-10"
				+ "\n(2) Medium \nGuess a number between 1-50" + "\n(3) Hard \nGuess a number between 1-100");

		input = buffyTheVampireSlayer.readLine();

		return input;
	}

	public static void game(String setting) throws IOException {
		int randoNum = 0, guess = 0, attempts = 5;
		int[] numGuessed = new int[attempts];
		String message = null, input = null;
		boolean validNum = true, gameActive = true;
		Random randoCalressean = new Random();

		switch (setting) {

		case "easy":
			randoNum = randoCalressean.nextInt(10) + 1;
			message = "Pick a number between 1-10";
			break;

		case "medium":
			randoNum = randoCalressean.nextInt(50) + 1;
			message = "Pick a number between 1-50";
			break;

		case "hard":
			randoNum = randoCalressean.nextInt(100) + 1;
			message = "Pick a number between 1-100";
			break;
		case "ULTRA!!!":
			randoNum = randoCalressean.nextInt(9001) + 1;
			message = "Pick a number between 1 - 9000";
			break;
		default:
			break;
		}

		do {
			System.out.println(message);

			input = buffyTheVampireSlayer.readLine();

			validNum = isInt(input);

			if (validNum) {
				guess = Integer.parseInt(input);

				for (int val : numGuessed) {
					if (val == guess) {
						validNum = false;
						break;
					}

				}

				if (validNum) {

					switch (setting) {
					case "easy":
						validNum = guess < 11 && guess > 0;
						break;
					case "medium":
						validNum = guess < 51 && guess > 0;
						break;
					case "hard":
						validNum = guess < 101 && guess > 0;
						break;
					case "ULTRA!!!":
						validNum = guess < 9002 && guess > 0;
					default:
						break;
					}

					if (validNum) {
						if (guess > randoNum) {

							System.out.println("That was way too high.");

						} else if (guess < randoNum) {

							System.out.println("THAT WAS WAY TOO LOW!!!!");

						} else {

							System.out.println("You win!!!! yay here's a cookie O");
							gameActive = false;

						}

						if (guess == 69) {
							System.out.println("Giggity");
						}
						if (randoNum == 9001) {
							System.out.println("ITS OVER 9000!!!!!");
						} else if (guess == 9001) {
							System.out.println("OVER 9000!?!?!?!?!?!?");
						}
						if (gameActive) {

							attempts--;

							if (attempts != 0) {
								numGuessed[5 - attempts] = guess;
							}

							gameActive = attempts > 0;
							if (gameActive) {
								System.out.println("You only have " + attempts + " remaining so please be careful");
							} else {
								System.out.println("You lost YUGI!!");
								System.out.println("The number was: " + randoNum);
								waitForEnter();
							}

						}
					} else {
						System.out.println("Please input a number in the correct range");
						waitForEnter();
					}

				} else {

					System.out.println("You have already guessed this number try again");
					waitForEnter();

				}

			} else {
				System.out.println("I will need a number if you want to guess it.");
				waitForEnter();
			}
		} while (gameActive);
	}

	public static String setDifficulty(int choice) throws IOException {
		switch (choice) {
		case 1:
			return "easy";
		case 2:
			return "medium";
		case 3:
			return "hard";
		case 69:
			return "ULTRA!!!";
		default:
			System.out.println("please Select a valid menu item");
			waitForEnter();
			return "";
		}
	}

	public static void start() throws IOException {
		int inputNum;
		boolean active = true, validInput = true, invalidChoice = true;
		String input = null, mode = "";
		do {

			input = menu();

			validInput = input != null;

			if (validInput) {

				validInput = isInt(input);

				if (validInput) {

					inputNum = Integer.parseInt(input);

					mode = setDifficulty(inputNum);

					validInput = !(mode.equals(""));

					if (validInput) {
						game(mode);
						do {
							System.out.println("Would you like to play again?");
							System.out.println("Type 1 for yes and Type 0 for no");

							input = buffyTheVampireSlayer.readLine();

							if (isInt(input)) {
								inputNum = Integer.parseInt(input);

								if (inputNum > -1 && inputNum < 2) {

									active = (inputNum != 0);
									invalidChoice = false;

								} else {

									System.out.println("Please Select a valid option");
									waitForEnter();
									invalidChoice = true;

								}

							}
						} while (invalidChoice);

					}

				} else {
					active = true;
					System.out.println("Please input a valid number");
					waitForEnter();
				}
			}

		} while (active);

	}

	public static void main(String[] args) {
		try {
			start();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Im sorry we ran into an unexpected error. \ntry restarting the program.");
		}

	}

}
