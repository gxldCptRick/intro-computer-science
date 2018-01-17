package models;

import java.util.Random;

public class Die {
	
	public final int NUM_OF_SIDES;
	private static Random rnJesus = new Random();
	private int currentValue = 1;
	
	public Die(int sides) {
		NUM_OF_SIDES = sides;
	}
	
	public int roll() {

		this.currentValue = rnJesus.nextInt(NUM_OF_SIDES) + 1;
	
		return this.currentValue;

	}
	
}
