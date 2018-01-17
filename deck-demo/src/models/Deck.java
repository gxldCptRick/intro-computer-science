package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import enums.Rank;
import enums.Suit;

public class Deck {
	private Card[] cardsInDeck = new Card[52];
	private ArrayBlockingQueue<Card> cards = new ArrayBlockingQueue<Card>(52);
	private Random rnJesus = new Random();

	public Deck() {
		Suit mary;
		Rank joey;
		Rank[] listOfRanks = Rank.values();
		for (int i = 0, j = 0; i < cardsInDeck.length; i++) {
			if (i < 13) {
				mary = Suit.CLUBS;
				joey = listOfRanks[j];
			} else if (i < 26) {
				joey = listOfRanks[j - 13];
				mary = Suit.DIAMONDS;
			} else if (i < 39) {
				joey = listOfRanks[j - 26];
				mary = Suit.HEARTS;
			} else {
				joey = listOfRanks[j - 39];
				mary = Suit.SPADES;
			}
			j++;

			cardsInDeck[i] = new Card(joey, mary);

		}

		cards.addAll(Arrays.asList(cardsInDeck));
	}

	public int size() {
		return cardsInDeck.length;
	}

	public Card drawCard() {
		Card cardDrawn = null;

		try {
		
			if (cards.size() > 0) {
			
				cardDrawn = cards.take();
			
			}
		
		} catch (InterruptedException e) {
		
			System.out.println(e.getClass() + " - " + e.getLocalizedMessage());
		
		}
		
		return cardDrawn;
	}

	@Override

	public String toString() {
		String message = "";

		for (Card card : cardsInDeck) {

			message += card + "\n";

		}

		return message;
	}

	public void shuffleDeck() {
		int j;
		Card temp;
		List<Integer> random = new ArrayList<Integer>();
		for (int i = 0; i < cardsInDeck.length; i++) {

			j = rnJesus.nextInt(cardsInDeck.length);

			while (random.contains(j)) {

				j = rnJesus.nextInt(cardsInDeck.length);

			}

			random.add(j);

			temp = cardsInDeck[i];
			
			cardsInDeck[i] = cardsInDeck[j];

			cardsInDeck[j] = temp;
			
		}
		cards.clear();
		cards.addAll(Arrays.asList(cardsInDeck));
	}

}
