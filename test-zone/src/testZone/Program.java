package testZone;

import models.DiceCup;
import models.Die;

public class Program {
	
	public static Die[] fullDieLimit = new Die[13];
	
	public static void start() {
		for(int i = 0; i < fullDieLimit.length; i++)
			fullDieLimit[i] = DiceCup.getDiceFromCup();
		
		System.out.println(DiceCup.getDiceLeftInCup());
		
		for(Die die : fullDieLimit) {
			System.out.println(die.getDifficutly() + " is Currently in use: " + die.getUseState());
		}
		
		
	}
}
