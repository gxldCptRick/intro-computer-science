package models;

import java.util.Random;

import enums.DieColor;

public class Cup {

	private static final int RED_CHANCE = 23;
	private static final int YELLOW_CHANCE = 31 + RED_CHANCE;
	//private static final int GREEN_CHANCE = YELLOW_CHANCE + 46;
	
	private static Random rnJesus = new Random();
	
	
	public static Die drawDie() {
		DieColor color = DieColor.GREEN;
		
		int chance = rnJesus.nextInt(100) + 1;

		
		if(chance <= RED_CHANCE) {
			
			color = DieColor.RED;
		
		}
		else if(chance <= YELLOW_CHANCE) {
			
			color = DieColor.YELLOW;
					
		}
		
		return new Die(color);
		
	}
	
}
