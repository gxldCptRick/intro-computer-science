package models;


public class Dealer {
	
	private Deck deck = new Deck();
	
	public Card[] dealCards(int amount) {
		
		Card[] cardsDrawn = new Card[amount];
		
		for(int i = 0; i < cardsDrawn.length; i++) {
			
			cardsDrawn[i] = deck.drawCard();
			
		}
		
		return cardsDrawn;
		
		
	}
	
	public int getTotalCardInDeck() {
		return deck.size();
	}
	
	public void shuffleDeck() {
		deck.shuffleDeck();
		
	}
	
	public String stringOfDeck() {
		return deck.toString();
	}
	
}
