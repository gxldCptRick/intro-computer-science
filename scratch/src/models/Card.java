package models;

import enums.*;

public class Card {
	
	public final Suit suit;
	public final Rank rank;
	
	public Card(Suit suit, Rank rank) {
		
		this.suit = suit;
		
		this.rank = rank;
	
	}
	
	public String toString() {
		
		return rank.toString() + " of " + suit.toString();
	
	}
	
	
}
