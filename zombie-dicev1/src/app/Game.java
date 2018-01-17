package app;

import models.*;
import enums.*;
import lib.ConsoleIO;

public class Game {

	private Player[] players = new Player[2];
	private Die[] dice = new Die[3];
	private int shotsFired = 0;
	private int survivorsCornered = 0;
	private boolean turnIsOver = true;

	public void start() {
		
		String[] options = {"Play Zombie Dice"};
		
		int choice = 0;
		
		do {
			
			choice = ConsoleIO.promptForMenuSelection(options, true);
			if(choice == 1) {
				playGame();
				declareWinner();
			}
		}while(choice != 0);

	}

	private void initializePlayers() {
		
		for(int i = 0; i < players.length; i++) {
			
			String name = promptForPlayerName(i+1);
			Player p = new Player(name);
			players[i] = p;
			
		}
		
	}

	private String promptForPlayerName(int playerNum) {

		String playerName = ConsoleIO.promptForInput("Enter the name for Player " + playerNum + ": ", true);
		
		if(playerName == null || playerName.trim().isEmpty()) {
			
			playerName = "Player " + playerNum;
			
		}
		
		return playerName.trim();
	}

	private void playGame() {
		boolean gameIsActive = true;
		
		initializePlayers();
		
		for(int i = 0; gameIsActive ;i++) {
			
			if(i > players.length - 1) {
				i = 0;
			}
			
			takeTurn(players[i]);
			
		}
		
	}

	private void takeTurn(Player player) {
		
		shotsFired = 0;
		survivorsCornered = 0;
		boolean isDead = false;
		
		do {
			
			displayTurnInfo(player);
			drawDice();
			rollDice();
			displayDice();
			displayTurnInfo(player);
			
			isDead = shotsFired >= 3;
			
			if(isDead) {
				System.out.println("You've been killed.. again or something... cause you're a ZAHMBEE!");
				turnIsOver = true;
			}
			else {
				turnIsOver = ConsoleIO.promptForBool("Would you like to roll again? (Y/N)", "N", "Y");
			}
			
		}while(!turnIsOver);
		
		
		if(!isDead) {
			player.eatBrains(survivorsCornered);
		}
		
	}

	private void displayDice() {
		
		for(Die die : dice) {
			
			System.out.println(die);
			
		}
		
	}
	
	private void displayTurnInfo(Player player) {
		
		System.out.println("Current Player: " + player);
		
		System.out.println("Shots Fired: " + shotsFired);
		
		System.out.println("Survivors Cornered: " + survivorsCornered);
		
	}
	
	private void drawDice() {
		
		for(int i = 0; i < dice.length; i++) {
			
			if(turnIsOver || dice[i].getCurrentFace() != DieFace.RUN) {
				
				dice[i] = Cup.drawDie();
				
			}
			
		}
		
	}

	private void rollDice() {
		
		for(Die die : dice) {
			
			DieFace rolled = die.roll();
			
			
			if(rolled == DieFace.BRAIN) {
				survivorsCornered++;
			}
			else if(rolled == DieFace.SHOT) {
				shotsFired++;
			}
			
		}
		
	}

	private void declareWinner() {
		
		Player winner = players[0];
		
		for(Player player : players) {
		
		 if(winner.getBrainsEaten() < player.getBrainsEaten()) {
				
				winner = player;
				
			}
			
		}
		
		System.out.println(winner.name + " wins with a total of " + winner.getBrainsEaten());
	}

}
