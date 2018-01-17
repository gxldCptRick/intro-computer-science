package birthdayCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;

public class Program {

	private static final int myAge = 19;
	private static BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

	public static boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean validInput(String input) {

		boolean dateValid = true;

		String[] inputs = null;

		inputs = input.split(", ");

		for (String val : inputs) {

			if (!isInt(val)) {

				return false;

			} else {

				dateValid = true;

			}
		}

		return dateValid;
	}

	public static void waitForEnter() throws IOException {
		System.out.println("Press Enter to Continue?");
		consoleInput.readLine();
	}

	public static Date processInput(String prompt, String format) throws IOException {

		boolean dateInvalid = true;

		Date newDate = new Date();

		int month = 0, day = 0, year = 0;

		String input = null;

		String[] inputs = new String[3];

		do {

			System.out.println(prompt);

			System.out.println(format);

			input = consoleInput.readLine();

			dateInvalid = !validInput(input);

			if (!dateInvalid) {

				inputs = input.split(", ");

				System.out.println(inputs[2]);

				for (int i = 0; i < 3; i++) {

					switch (i) {
					case 0:
						year = Integer.parseInt(inputs[i]);
						break;
					case 1:
						month = Integer.parseInt(inputs[i]);
						break;
					case 2:
						day = Integer.parseInt(inputs[i]);
						break;
					}

				}

				try {
					newDate = new Date(day, month, year);
				} catch (DateTimeException e) {
					dateInvalid = true;
				}
			} else {

				System.out.println("please Enter an actual date");

			}

		} while (dateInvalid);

		return newDate;
	}

	public static int findAge(Date laterDate, Date earlierDate) {

		if (laterDate.getYear() < earlierDate.getYear()) {

			return -1;

		} else {

			if (laterDate.getMonth() >= earlierDate.getMonth()) {

				if (laterDate.getDay() > earlierDate.getDay()) {

					return laterDate.getYear() - earlierDate.getYear() -1;
				}
				else {
					return laterDate.getYear() - earlierDate.getYear();
				}
			} else {
				return laterDate.getYear() - earlierDate.getYear();
			}
		}

	}

	public static boolean willContinue() throws IOException {
		int value = 0;
		boolean isNotNumber = true;
		do {
			System.out.println("Would You Like to Continue...");
			System.out.println("Enter 1 for Yes or any other number for no");

			String input = consoleInput.readLine();

			if (isInt(input)) {

				isNotNumber = false;

				value = Integer.parseInt(input);

			} else {
				System.out.println("please give me a number any number should do");
				isNotNumber = true;

			}
		} while (isNotNumber);

		if (value > 1 || value < 1) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean output(Date currentDate, Date birthDate, int age) throws IOException {
		if (age > 0) {

			System.out.println("Today is " + currentDate.toString() + "\nYour birthday is " + birthDate.toString());

			waitForEnter();

			System.out.println("You are " + age + " years old");

			waitForEnter();

			if (age > myAge) {
				System.out.println("You are older than me old man.");
			} else if (age < myAge) {
				System.out.println("Ohh look it is a little pupper you are so young compared to me.");
			} else {
				System.out.println("Wow twinsies... we have the same age.");
			}
			waitForEnter();

			return false;
		} else {
			System.out.println("Woah you can't be born in the future that is not how time works okay,\n "
					+ "Are you Chris Travers because you dont look like Chris Travers");
			return true;
		}
	}

	public static void start() throws IOException {
		Date currentDate, birthDate;
		boolean keepGoing = true;
		int age = 0;

		do {
			currentDate = processInput("Please input the current day", "Year, month, day");
			birthDate = processInput("Please input your birthday", "Year, month, day");

			age = findAge(currentDate, birthDate);

			keepGoing = output(currentDate, birthDate, age);

			keepGoing = willContinue();

		} while (keepGoing);
	}

	public static void main(String[] args) {
		try {
			start();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Im sorry but we encountered an unexpected problem.");
		}
	}

}
