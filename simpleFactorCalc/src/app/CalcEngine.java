package app;

import java.util.ArrayList;
import java.util.List;

import lib.ConsoleIO;

public class CalcEngine {

	public static void run() {
		boolean keepGoing = true;
		do {
			int numToFactor = ConsoleIO.promptForInt("Enter a Number to factor", Integer.MIN_VALUE, Integer.MAX_VALUE);
			
			Integer[] factors = findFactors(numToFactor);
			
			System.out.println("Factors");
			System.out.println("_______");
			printArray(factors);
			
			keepGoing = ConsoleIO.promptForBool("Yes?", "Y", "N");

		} while (keepGoing);
	}

	private static <T> void printArray(T[] array) {
		for(T item : array) {
			System.out.println(item);
		}
	}
	
	private static Integer[] findFactors(int input) {
		List<Integer> listOfFactors = new ArrayList<>();

		int i = input * -1;

		if (input > 0) {
			for (; i <= input; i++) {
				
				if(i != 0 && input % i == 0) {
				
					listOfFactors.add(i);
				
				}
			}
			
		} else {
		
			for (; i <= input; i--) {

				if(i != 0 && input % i == 0) {
				
					listOfFactors.add(i);
				
				}
				
			}
		}

		Integer[] factors = new Integer[listOfFactors.size()];
		factors = listOfFactors.toArray(factors);
		return factors;
	}

}
