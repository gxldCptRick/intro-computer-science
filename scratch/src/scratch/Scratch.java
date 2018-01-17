package scratch;

import java.util.Random;

import enums.Rank;
import enums.Suit;
import models.Card;

public class Scratch {
	
	private static int rankPointer = 0;
	
	private static 	Rank[] listOfRanks = Rank.values();

	public static void run() {

		Card[] deck = new Card[52];

		Rank currentRank;

	

		Suit currentSuit;

		for (int i = 0; i < deck.length; i++) {

			if (i < deck.length / 4) {
				currentSuit = Suit.CLUBS;

			} else if (i < deck.length / 2) {

				currentSuit = Suit.DIAMOND;

			} else if (i < deck.length * 3 / 4) {

				currentSuit = Suit.HEARTS;

			} else {

				currentSuit = Suit.SPADES;

			}
			
			currentRank = findRank();

			deck[i] = new Card(currentSuit, currentRank);
		}
		
		
		Object randoCalressian = (Object)(new Random());
		
		
		Random randySavage = (Random)randoCalressian;
		
		int place  = randySavage.nextInt(deck.length);
		
		System.out.println("I've Given You " + deck[place].toString());
		
	}
	
	private static Rank findRank() {
		Rank newRank = listOfRanks[rankPointer];
		
		rankPointer = rankPointer < listOfRanks.length - 1? rankPointer + 1: 0;
		
		return newRank;
		
	}

}