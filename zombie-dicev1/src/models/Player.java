package models;

public class Player {

	public final String name;
	private int brainsEaten;
	
	public Player(String name) {
		this.name = name;
	}
	
	public int getBrainsEaten() {
		return this.brainsEaten;
	}
	
	public void eatBrains(int brains) {
		
		this.brainsEaten += brains;
		
	}

	@Override
	
	public String toString() {
		return  name + " - Brains Eaten:  " + getBrainsEaten();
	}
	
	
}
