package exercises;

import lib.ConsoleIO;

public class Week09Day4 {

	public static void run() {
		boolean keepGoing = true;
		do {
			
			String input = ConsoleIO.promptForInput("Give A string to encrypt", false);
			
			int offset = ConsoleIO.promptForInt("Give Me an Offset", -25, 25);
			
			String encryptedString = encryptString(input, offset);
			
			System.out.println(encryptedString);
			
			keepGoing = ConsoleIO.promptForBool("More? ", "Y", "N");
			
		}while(keepGoing);

	}

	private static String encryptString(String raw, int offset) {
		char[] encryptedMess = raw.toCharArray();

		for (int i = 0; i < encryptedMess.length; i++) {

			if (encryptedMess[i] > 96 && encryptedMess[i] < 123) {

				encryptedMess[i] += offset;

				if (encryptedMess[i] < 97) {

					int newOffset = encryptedMess[i] - 97;

					encryptedMess[i] = (char) (newOffset + 123);
					
				} else if (encryptedMess[i] > 122) {

					int newOffset = encryptedMess[i] - 122;

					encryptedMess[i] = (char) (newOffset + 96);
				
				}

			} else if (encryptedMess[i] > 64 && encryptedMess[i] < 91) {

				encryptedMess[i] += offset;

				if (encryptedMess[i] < 65) {

					int newOffset = encryptedMess[i] - 65;

					encryptedMess[i] = (char) (newOffset + 91);
					
				} else if (encryptedMess[i] > 90) {

					int newOffset = encryptedMess[i] - 90;

					encryptedMess[i] = (char) (newOffset + 64);
				}

			}

		}

		return new String(encryptedMess);
	}
}
