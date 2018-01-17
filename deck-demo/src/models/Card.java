package models;

import enums.*;

public class Card {
	public final Rank RANK;
	public final Suit SUIT;
	
	public Card(Rank rank, Suit suit) {
		
		this.RANK = rank;
		this.SUIT = suit;
	
	}
	
	@Override
	public String toString() {
		
		return RANK + " of " + SUIT;
	
	}
}
