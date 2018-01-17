package controllers;

import java.util.ArrayList;
import java.util.List;

import lib.ConsoleIO;

public class Factorizer {

	public static void run() {
		boolean keepGoing = true;
		do {

			int highestPower = ConsoleIO.promptForInt("Enter Highest Power", 0, Integer.MAX_VALUE);

			double[] inputs = new double[highestPower + 1];

			for (int i = 0; i <= highestPower; i++) {

				inputs[i] = ConsoleIO.promptForDouble("Enter Value of Qoeficient for x^" + (highestPower - i), -100000,
						
						100000);

			}

			Double[] factors = findFactors(inputs);

			System.out.println("All Rational Zeros");

			printArray(factors);

			keepGoing = ConsoleIO.promptForBool("Factor Another?", "Y", "N");

			/*
			 * int numberToWorkWith = ConsoleIO.promptForInt("Enter number to be factored",
			 * Integer.MIN_VALUE, Integer.MAX_VALUE);
			 * 
			 * int[] factors = factor(numberToWorkWith);
			 * 
			 * printFactors(factors, numberToWorkWith);
			 * 
			 * keepGoing = ConsoleIO.promptForBool("Another Number?", "Y", "N");
			 */

		} while (keepGoing);
	}

	private static Double[] findFactors(double[] values) {

		List<Double> possibleFactors = new ArrayList<>();

		double first = values[0], second = 0;

		boolean foundEnd = false;

		for (int i = values.length - 1; i > 0 && !foundEnd; i--) {

			if (values[i] != 0) {

				second = values[i];

				foundEnd = true;

			}

		}

		double[] firstFactors = factor(first);

		double[] secondFactors = factor(second);

		for (double fFactor : firstFactors) {

			for (double sFactor : secondFactors) {

				possibleFactors.add((sFactor / fFactor));

			}

		}

		Double[] possFactors = new Double[possibleFactors.size()];

		possFactors = possibleFactors.toArray(possFactors);
		
		printArray(possFactors);

		possFactors = testFactors(possFactors, values);

		return possFactors;

	}

	private static Double[] testFactors(Double[] possibleFactors, double[] values) {

		double remainder = 0, pow, temp, exp;

		for (int i = 0; i < possibleFactors.length; i++) {

			for (int j = 0; j < values.length; j++) {

				temp = possibleFactors[i];

				exp = values.length - j - 1;

				pow = Math.pow(temp, exp);

				remainder += pow * values[j];
			}

			if (remainder != 0) {

				possibleFactors[i] = null;
			}

			remainder = 0;

		}

		possibleFactors = proccessArray(possibleFactors);

		return possibleFactors;

	}

	private static double[] factor(double numToWorkWith) {

		List<Double> factors = new ArrayList<>();

		if (numToWorkWith > 0) {

			for (double i = numToWorkWith * -1; i <= numToWorkWith; i++) {

				if (numToWorkWith % i == 0) {

					factors.add(i);

				}

			}
		} else {
			for (double i = numToWorkWith * -1; i >= numToWorkWith; i--) {

				if (numToWorkWith % i == 0) {

					factors.add(i);

				}

			}
		}

		double[] returnedStuff = new double[factors.size()];

		for (int i = 0; i < returnedStuff.length; i++) {

			returnedStuff[i] = factors.get(i);
		}

		return returnedStuff;

	}

	private static Double[] proccessArray(Double[] raw) {
		List<Double> proccessedStuff = new ArrayList<>();
		
		
		for(Double num : raw) {
			if(num != null && !proccessedStuff.contains(num)) {
				proccessedStuff.add(num);
			}
		}
		
		Double[] proccessedArray = new Double[proccessedStuff.size()];
		
		proccessedArray = proccessedStuff.toArray(proccessedArray);

		return proccessedArray;
	}

	private static <T> void printArray(T[] thingToPrint) {

		for (T thing : thingToPrint) {

			if (thing != null) {
				System.out.print(thing + ", ");
			}
		}
		System.out.println();

	}
}
