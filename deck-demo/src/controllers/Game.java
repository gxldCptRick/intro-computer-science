package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lib.ConsoleIO;
import models.Card;
import models.Dealer;

public class Game {

	private Dealer dealer = new Dealer();

	private List<Card> cardsDealt = new ArrayList<Card>();

	private Card[] cardsCurrentlyDrawn;
	
	public void run() {

		startGame();

	}

	private void startGame() {

		boolean gameActive = true;

		boolean shuffle = false;

		boolean deckEmpty = true;
		
		System.out.println("Deck is Being Shuffled");
		
		ConsoleIO.waitForInput();
		
		setup();

		ConsoleIO.waitForInput();
		System.out.println();
		
		do {
			drawCards();

			printCardsDrawn();
			
			deckEmpty = cardsDealt.contains(null);

			if (deckEmpty) {

				System.out.println("The deck is empty...");
				
				
			}

			
			
			if (deckEmpty) {
			
				shuffle = ConsoleIO.promptForBool("Would You like to reshuffle the Deck and draw five more cards? (Y/N)", "Y", "N");
				
				gameActive = shuffle;
			
			}else {
				shuffle = ConsoleIO.promptForBool("Would you like to shuffle again? (Y/N)\n(This will also refill the deck)", "Y","N");
				gameActive = ConsoleIO.promptForBool("Would you like to draw five more Cards? (Y/N)", "Y", "N");
				
			}

			if (shuffle) {
			
				dealer.shuffleDeck();
				cardsDealt.clear();
			
			}

		} while (gameActive);

	}

	private void setup() {
		dealer.shuffleDeck();

		String message = dealer.stringOfDeck();

		System.out.println(message);
	}

	private void drawCards() {

		cardsCurrentlyDrawn = dealer.dealCards(5);
		
		List<Card> tempList = Arrays.asList(cardsCurrentlyDrawn); 
	
		cardsDealt.addAll(tempList);

	}
	
	private void printCardsDrawn() {
		
		for(Card card : cardsCurrentlyDrawn) {
			
			if(card != null) {
				System.out.println(card);
			}
		}
	}
	
}
