package models;

public class Player {
	public final String name;
	private int totalBrainCount;
	private boolean winner;

	public Player(String name) {

		this.name = name;
		this.totalBrainCount = 0;
		this.winner = false;

	}

	public void setWinner(boolean winner) {

		this.winner = winner;

	}

	public boolean isWinner() {

		return this.winner;

	}

	public void addBrains(int amountOfBrains) {

		totalBrainCount += amountOfBrains;

	}

	public int getBrainCount() {

		return totalBrainCount;

	}

	@Override

	public String toString() {
		String toString;

		if (isWinner()) {

			toString = String.format("%s has won with %d brains eaten.", this.name, this.getBrainCount());
			
		} 
		else {

			toString = String.format("%s has eaten ", this.name);

			if (totalBrainCount == 1) {

				toString += String.format("%d brain ", this.getBrainCount());

			} else {

				toString += String.format("%d brains ", this.getBrainCount());

			}

			toString += "so far.";
		}
		
		return toString;

	}

}
