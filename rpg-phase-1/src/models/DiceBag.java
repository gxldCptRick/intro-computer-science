package models;

public class DiceBag {

	public int rollDice(int sides, int amountOfDice) {
	
		int value = 0;
		
		Die[] allTheDice = new Die[amountOfDice];
		
		for(int i = 0; i < allTheDice.length; i++) {
			
			allTheDice[i] = new Die(sides);
			
		}
		
		for(Die die : allTheDice) {
			value += die.roll();
		}
		
		return value;
	}
	
	public int rollDice(int sides, int amountOfDice, int modifier) {
		
		int value = rollDice(sides, amountOfDice) + modifier; 
		
		return value;
	
	}
	
	public int rollDie(int sides) {
		
		Die currentDie = new Die(sides);
		
		int value  = currentDie.roll();
		
		return value;
		
	}
	
	public int rollDie(int sides, int modifier) {
		
		int value = rollDie(sides) + modifier;
		
		return value;
		
	}
	
	
	
}
