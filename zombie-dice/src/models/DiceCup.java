package models;

import java.util.Random;

import enums.DieColor;
import enums.DieFace;

public class DiceCup {


	private final static int GREEN_DICE = 6;

	private final static int YELLOW_DICE = 4;

	private final static int RED_DICE = 3;

	private static Die[] listOfDice;

	public static int amountOfDiceLeft() {
		
		int deku = 0;

		for (Die die : listOfDice) {
			deku = die.isActive() ? deku : deku + 1;
		}

		return deku;

	}
	
	public static int[] amountsOfSpecificDiceLeft() {
		int[] midoriya = new int[3];
		int green = 0,  yellow = 0 , red = 0;
		
		for(Die die : listOfDice) {
			
			if(!die.isActive()) {
				
				switch(die.color) {
				
				case GREEN:
					green++;
					break;
				
				case YELLOW:
					yellow++;
					break;
				
				case RED:
					red++;
					break;
				
				default:
					break;
				}
			}
			
		}
		
		midoriya[0] = green;
		midoriya[1] = yellow;
		midoriya[2] = red;
		
		return midoriya;
		
	}

	public static void resetCup() {

		for (Die die : listOfDice) {

			die.setActive(false);

		}

	}

	public static Die grabDice() {

		Random randySavage = new Random();

		Die dieGrabbed;

		if (amountOfDiceLeft() < 1) {

			for (Die die : listOfDice) {

				if (die.isActive() && die.getCurrentFace() == DieFace.BRAIN) {

					die.setActive(false);

				}

			}

		}

		int spot = randySavage.nextInt(listOfDice.length);

		dieGrabbed = checkIfAlreadyUsed(listOfDice[spot]);

		return dieGrabbed;

	}

	public static void initializeCup() {

		initializeCup(GREEN_DICE, YELLOW_DICE, RED_DICE);
	}

	public static void initializeCup(int greenDice, int yellowDice, int redDice) {

		int totalDice = greenDice + yellowDice + redDice;

		int midRange = greenDice + yellowDice;

		listOfDice = new Die[totalDice];

		for (int i = 0; i < listOfDice.length; i++) {

			if (i < greenDice) {

				listOfDice[i] = new Die(DieColor.GREEN);

			} else if (i < midRange) {

				listOfDice[i] = new Die(DieColor.YELLOW);

			} else {

				listOfDice[i] = new Die(DieColor.RED);

			}
		}

	}

	private static Die checkIfAlreadyUsed(Die currentDie) {
		Die testedDie = null;

		if (currentDie.isActive()) {

			testedDie = grabDice();

		} else {

			currentDie.setActive(true);

			testedDie = currentDie;

		}

		return testedDie;

	}

}
