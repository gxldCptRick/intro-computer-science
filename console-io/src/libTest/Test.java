package libTest;

import lib.ConsoleIO;



public class Test {
	public static void main(String[] args) {
		
		boolean active = true;
		do {
		
//		String[] names = {"Joe", "Bert", "Hermoine", "Guero", "Pleb", "Hernan", "Stienke", "Kelvin"};
//		ConsoleIO.promptForMenuSelection(names,false);
//		boolean mitchConnerAlive = ConsoleIO.promptForBool("Yes or No", "Yes", "No");
//		System.out.println(mitchConnerAlive);		
//		ConsoleIO.promptForByte("Enter a byte please",(byte) -100, (byte) 100);	
//		ConsoleIO.promptForShort("Enter a Short please", (short) - 100, (short) 100);
//		ConsoleIO.promptForInt("Give me a number preaze", -100, 100);		
//		ConsoleIO.promptForLong("Long number PREAZE", -9001, 9000);
//		ConsoleIO.promptForFloat("Enter a Float", -99.11111f, 99.1111f);
		ConsoleIO.promptForInput("Stuff", false);
//		ConsoleIO.promptForChar("Do something cuu", 'B', 'b');
		
		active = ConsoleIO.promptForBool("Would you like to continue", "Y", "N");
		}while(active);
		
	}
}
