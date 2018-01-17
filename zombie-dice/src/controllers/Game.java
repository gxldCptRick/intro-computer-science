package controllers;

import enums.DieFace;
import lib.ConsoleIO;
import models.DiceCup;
import models.Die;
import models.Player;

public class Game {

	private int brainCount;
	private int shotCount;
	private Player[] players;
	private Die[] hand = new Die[3];
	private boolean settingChange;

	private void playGame() {
		boolean mainGameActive = true;
		boolean appActive = true;

		do {

			this.setup();

			for (int i = 0; mainGameActive; i++) {

				if (i > players.length - 1) {
					i = 0;
				}

				runTurn(players[i]);

				mainGameActive = players[i].getBrainCount() < 13;

				players[i].setWinner(!mainGameActive);

			}

			runFinalTurns();

			announceWinner();

			appActive = ConsoleIO.promptForBool("Would You like to play again? (Y/N)", "Y", "N");

		} while (appActive);

	}

	public void run() {

		boolean inMenu = true;

		String[] menuItems = { "Play Zombie Dice", "Options" };

		int choice;

		do {
			choice = ConsoleIO.promptForMenuSelection(menuItems, true);

			if (choice == 1) {

				playGame();

			} else if (choice == 2) {

				initializeAmountOfDice();
				settingChange = true;

			}

			inMenu = choice != 0;

		} while (inMenu);

	}

	private void setup() {

		int numOfPlayers = ConsoleIO.promptForInt("How Many Players are playing? (between 2 and 4)", 2, 4);

		players = new Player[numOfPlayers];

		initializePlayers();

		if (!settingChange) {
			DiceCup.initializeCup();
		}

	}

	private void initializePlayers() {

		String name;

		for (int i = 0; i < players.length; i++) {

			name = ConsoleIO.promptForInput("Input Name For Player " + (i + 1), true);

			if (name.trim().isEmpty()) {

				players[i] = new Player("Player " + (i + 1));

			} else {

				players[i] = new Player(name);

			}
		}

	}

	private void initializeAmountOfDice() {

		boolean custom = ConsoleIO.promptForBool("Do You want a Custom amount of dice or the Standard amount? (C/S)",
				"C", "S");

		if (custom) {

			int[] amountOfDice = new int[3];

			String message = "";

			for (int i = 0; i < amountOfDice.length; i++) {

				switch (i) {

				case 0:

					message = "Enter amount of green dice";

					break;

				case 1:

					message = "Enter amount of yellow dice";

					break;

				case 2:

					message = "Enter amount of red dice";

					break;

				default:

					break;
				}

				message += " between 1 - 50.";

				amountOfDice[i] = ConsoleIO.promptForInt(message, 1, 50);
			}

			DiceCup.initializeCup(amountOfDice[0], amountOfDice[1], amountOfDice[2]);

		} else {

			DiceCup.initializeCup();

		}

	}

	private void runTurn(Player currentPlayer) {

		startTurn(currentPlayer.name);

		String message;

		boolean shotDead = false, keepRollingG = true;

		do {

			shotDead = shotCount >= 3;

			if (!shotDead) {

				message = brainCountString(currentPlayer, false) + ".\n\n" + shotCountString(currentPlayer)
						+ ".\n\nWould you like to keep Rolling? (Y/N)";

				keepRollingG = ConsoleIO.promptForBool(message, "Y", "N");

				System.out.println("");

				if (keepRollingG) {

					rollDice();

				}

			} else {

				message = shotCountString(currentPlayer) + " and lost all the brains they have gathered.\n\n";

			}

		} while (!shotDead && keepRollingG);

		if (!shotDead) {

			message = brainCountString(currentPlayer, true) + ",\n";

			currentPlayer.addBrains(brainCount);

		}

		message += "and " + currentPlayer.toString();

		endTurn(message);

	}

	private void startTurn(String name) {

		System.out.printf("%s is rolling\n", name);

		ConsoleIO.waitForInput();

		System.out.println("");

		rollDice();

	}

	private void endTurn(String message) {

		System.out.println(message);

		waitForEnter();

		brainCount = 0;

		shotCount = 0;

		hand = new Die[3];

		DiceCup.resetCup();
	}

	private void rollDice() {

		for (int i = 0; i < hand.length; i++) {

			if (hand[i] == null) {

				hand[i] = DiceCup.grabDice();

				System.out.printf("You have grabbed a %s die ID: %d from the cup.\n", hand[i].color, hand[i].ID);

				waitForEnter();

			}

		}

		int[] allTheDice = DiceCup.amountsOfSpecificDiceLeft();

		String diceLeft = String.format("There are %d dice remaining in the cup, More specifically,\n",
				DiceCup.amountOfDiceLeft());

		for (int i = 0; i < allTheDice.length; i++) {

			switch (i) {
			case 0:
				diceLeft += String.format("There are %d green dice left, ", allTheDice[i]);
				break;
			case 1:
				diceLeft += String.format("%d yellow dice left, ", allTheDice[i]);
				break;
			case 2:
				diceLeft += String.format("and %d red dice left.", allTheDice[i]);
				break;
			default:
				throw new IllegalArgumentException("There can only be 3 kinds of dice.");
			}

		}

		System.out.println(diceLeft);
		waitForEnter();

		for (Die die : hand) {

			DieFace newFace = die.roll();

			if (newFace == die.getCurrentFace()) {

				System.out.println(die);

				switch (newFace) {

				case BRAIN:
					brainCount++;
					break;

				case SHOT:
					shotCount++;
					break;

				default:
					break;
				}

			} else {

				throw new IllegalArgumentException(
						"Something Went Horribly Wrong, The CurrentFace is not equal to what it had just rolled");

			}

		}

		waitForEnter();

		for (int i = 0; i < hand.length; i++) {

			if (hand[i].getCurrentFace() == DieFace.BRAIN || hand[i].getCurrentFace() == DieFace.SHOT) {

				hand[i] = null;

			}

		}
	}

	private void runFinalTurns() {

		for (Player player : players) {

			if (!player.isWinner()) {

				System.out.println("Rolling Final Turns For " + player.name);

				runTurn(player);

			}
		}

	}

	private void announceWinner() {

		Player winner = null;

		int winningTotal = calculateWinningTotal();

		findWinners(winningTotal);

		if (isTie()) {

			runTieBreaker();

		}

		for (int i = 0; i < players.length && winner == null; i++) {

			if (players[i].isWinner()) {

				winner = players[i];

			}

		}

		System.out.println(winner);

	}

	private int calculateWinningTotal() {
		int winningTotal = 0;

		for (Player player : players) {

			if (player.getBrainCount() > winningTotal) {

				winningTotal = player.getBrainCount();

			}
		}

		return winningTotal;
	}

	private void findWinners(int winningTotal) {

		for (Player player : players) {

			if (player.getBrainCount() == winningTotal) {

				player.setWinner(true);

			} else {

				player.setWinner(false);

			}

		}

	}

	private void runTieBreaker() {

		System.out.println("Running Tie Breaking Round");

		for (Player player : players) {

			if (player.isWinner()) {

				runTurn(player);

			}

		}

		int winningTotal = calculateWinningTotal();

		findWinners(winningTotal);

		if (isTie()) {

			runTieBreaker();

		}

	}

	private boolean isTie() {
		int amountOfWinners = 0;

		for (Player player : players) {

			if (player.isWinner()) {

				amountOfWinners++;

			}
		}

		return amountOfWinners > 1;
	}

	private void waitForEnter() {
		ConsoleIO.waitForInput();
		System.out.println("");
	}

	private String shotCountString(Player currentPlayer) {

		String shotCountString = String.format("%s has also been shot ", currentPlayer.name);

		if (shotCount == 1) {

			shotCountString += String.format("%d time", shotCount);

		} else {

			shotCountString += String.format("%d times", shotCount);

		}

		return shotCountString;

	}

	private String brainCountString(Player currentPlayer, boolean endTurn) {
		String brainCountString;

		if (endTurn) {

			brainCountString = String.format("%s has eaten ", currentPlayer.name);

			if (brainCount == 1) {

				brainCountString += String.format("%s brain ", brainCount);

			} else {

				brainCountString += String.format("%s brains ", brainCount);

			}

			brainCountString += "this round";

		} else {
			brainCountString = String.format("%s has collected ", currentPlayer.name);

			if (brainCount == 1) {

				brainCountString += String.format("%d brain ", brainCount);

			} else {

				brainCountString += String.format("%d brains ", brainCount);

			}

			brainCountString += "so far";
		}
		return brainCountString;

	}
}
