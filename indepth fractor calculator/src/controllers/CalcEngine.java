package controllers;

import java.util.ArrayList;
import java.util.List;

import lib.ConsoleIO;
import models.Fraction;

public class CalcEngine {

	public static void run() {

		boolean appActive = true;
		do {

			int values = ConsoleIO.promptForInt("Enter max power", -200, 200) + 1, temp;
			Fraction[] valuesToWorkWith = new Fraction[values];

			for (int i = 0; i < valuesToWorkWith.length; i++) {

				temp = ConsoleIO.promptForInt("Enter the coefficient for x^" + (values - i - 1), -200, 200);

				valuesToWorkWith[i] = new Fraction(temp);
			}

			Fraction[] factorsFrac = proccessValues(valuesToWorkWith);

			printArray(factorsFrac);

			appActive = ConsoleIO.promptForBool("continue", "Y", "N");

		} while (appActive);
	}

	private static <T> void printArray(T[] items) {
		for (T item : items) {
			System.out.println(item);
		}
	}

	private static Fraction[] proccessValues(Fraction[] values) {

		boolean foundEnd = false;

		List<Fraction> things = new ArrayList<>();

		Fraction first = values[0], second = null;

		for (int i = values.length - 1; i > 0 && !foundEnd; i++) {

			if (values[i].getDecimalValue() != 0) {

				second = values[i];
				foundEnd = true;

			}

		}

		Fraction[] firstFactors = factor(first);

		printArray(firstFactors);

		Fraction[] secondFactors = factor(second);

		printArray(secondFactors);

		for (Fraction fFactor : firstFactors) {

			for (Fraction sFactor : secondFactors) {

				if (fFactor.getDecimalValue() != 0) {

					things.add(sFactor.divide(fFactor));

				}

				System.out.println(fFactor + "First Factor");
			}

		}

		things = testFactors(things, values);

		Fraction[] factors = new Fraction[things.size()];

		factors = things.toArray(factors);

		return factors;

	}

	private static List<Fraction> testFactors(List<Fraction> possibleFactors, Fraction[] values) {

		Fraction remainder, temp;

		int exp;
		for (int i = 0; i < possibleFactors.size(); i++) {

			remainder = new Fraction(0);

			for (int j = 0; j < values.length; j++) {
				temp = possibleFactors.get(i);

				exp = values.length - 1 - j;

				remainder = remainder.add(temp.pow(exp));
			}

			if (remainder.getDecimalValue() != 0) {
				possibleFactors.set(i, null);
			}

		}

		possibleFactors = proccessList(possibleFactors);

		return possibleFactors;

	}

	private static List<Fraction> proccessList(List<Fraction> factors) {

		List<Fraction> proccessedFactors = new ArrayList<>();

		for (Fraction frac : factors) {
			if (frac != null && !proccessedFactors.contains(frac)) {

				proccessedFactors.add(frac);

			}
		}

		return proccessedFactors;

	}

	private static Fraction[] factor(Fraction value) {

		List<Fraction> factors = new ArrayList<>();
		
		if (value.getDecimalValue() > 0) {

			for (int i = value.getWholeNum() * -1; i < value.getWholeNum(); i++) {

				if (value.mod(i) == 0) {

					factors.add(new Fraction(i));

				}
			}

		} else {

			for (int i = value.getWholeNum() * -1; i < value.getWholeNum(); i++) {

				if (value.mod(i) == 0) {

					factors.add(new Fraction(i));

				}
			}
		}

		Fraction[] factorsListed = new Fraction[factors.size()];

		factorsListed = factors.toArray(factorsListed);

		return factorsListed;

	}

}
