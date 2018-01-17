package week2_exercises;

import java.util.Scanner;

/**
 * @author Andres
 *
 */
public class Program {

	public static boolean isInt(String s) {
		boolean isInteger = true;
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			isInteger = false;
		}

		return isInteger;
	}
	
	public static int validateInput(Scanner consoleInput, String message) {
		
		System.out.println("please give me a number");
		
		String input = consoleInput.nextLine();
		
		while(!isInt(input)) {

			System.out.println(message);
			
			input = consoleInput.nextLine();
		}
		
		return Integer.parseInt(input);
	}
	
	public static double getVolume(int radius){
		return Math.PI * Math.pow(radius, 3) * (4d/3);
	}
	
	public static double getSurfaceArea(int radius) {
		return Math.PI * Math.pow(radius, 2) * 4;
	}
	
	public static void problem1(Scanner consoleInput) {
		int inputInt;

		String  output = "Your number ",
				odd = "is odd ",
				even = "is even ",
				positive = "is positive ",
				negative = "is negative ",
				divisibleBy7 = "is divisible by 7 ",
				zero = " neither positive nor negative and divisible by 7",
				inputMessage =" please input a valid number";


		inputInt = validateInput(consoleInput, inputMessage);

		if ((inputInt % 2) == 0) {
			
			output += even;

			if (inputInt > 0) {
				
				output += positive;

				if ((inputInt % 7) == 0) {
					
					output += divisibleBy7;
					
				}
				
				
			} else if (inputInt < 0) {
				
				output += negative;
				
				if ((inputInt % 7) == 0) {
					
					output += divisibleBy7;
					
				}
				
				
			} else {
				
				output += zero;
				
			}

		} else {
			
			output += odd;

			if (inputInt > 0) {
				
				output += positive;
				
				if ((inputInt % 7) == 0) {

					output += divisibleBy7;
					
				}
				
			} else if (inputInt < 0) {

				output += negative;
				
				if ((inputInt % 7) == 0) {

					output += divisibleBy7;
					
				}
			} else {
				
				output += zero;

			}

		}
		
		System.out.println(output);
		
	}

	public static void problem2(Scanner consoleInput) {
		
		String message = "plese give me a valid year\n or at the very least a number ",
				output = "",
				leapYear = "That is a leap year",
				notLeapYear = "That is not a leap year";
		
		int year = 1776;
		
		year = validateInput(consoleInput, message);
		
		if(year >= 1582 && (((year % 4) == 0 && !((year % 100) == 0)) || ((year % 400) == 0))) {
			
			output += leapYear;
		
		}
		else {
			
			output += notLeapYear;
		
		} 
		
		System.out.println(output);
		
	}
	
	
	public static void problem3(Scanner consoleInput) {
		int radius = 0;
		double volume, surfaceArea;
		String message = "please give me a valid radius";
		radius = validateInput(consoleInput, message);
		
		volume = getVolume(radius);
		surfaceArea = getSurfaceArea(radius);
		
		System.out.printf("the Surface Area is: % .4f \n "
				+ "the Volume is : % .4f" , surfaceArea, volume);
		
	}
	
	public static void main(String[] args) {
		Scanner consoleInput = new Scanner(System.in);
		int input = 1 ;
		while(input != 0) {
			
		problem1(consoleInput);
		
		problem2(consoleInput);
		
		problem3(consoleInput);
		
		}
		
	}

}
