package controllers;

import lib.ConsoleIO;
import models.DiceBag;
import models.Hero;

public class GameMaster {
	
	private Hero[] players;
	private DiceBag steve = new DiceBag();

	public void start() {
		createHeros();
	}

	private void createHeros() {
	
		players = new Hero[1];
		
		for(int i = 0; i < players.length; i++) {
			
			setUpHero(i);
			
		}
	
	}
	
	private void setUpHero(int index) {
		
		boolean notHappy;
		Hero deku = null;
		
		do {
			
		deku = rollHero();
		System.out.println(deku);
		notHappy = ConsoleIO.promptForBool("Are You Happy With This Character? (Y/N)", "N", "Y");
		
		}while(notHappy);
		
		String name = getName();
		
		deku.setName(name);
		
		players[index] = deku;
	}
	
	private Hero rollHero() {
		int str = calculateAttr(),
			dex = calculateAttr(),
			intel = calculateAttr();
		
		Hero bakugo = new Hero(str, dex, intel);
		return bakugo;
	}
	
	private String getName() {
		String name = ConsoleIO.promptForInput("Name Your Character", false);
		
		name = name.trim();
		
		if(name.isEmpty()) {
			
			name = "The Unnamed Hero";
			
		}
		
		return name;
	}
	
	private int calculateAttr() {
		
		int baseValue = steve.rollDice(6, 3);
		
		if(baseValue > 15) {
			
			int bonus = steve.rollDie(6);
			
			baseValue += bonus == 6 ? bonus + steve.rollDie(6): bonus;
			
		}
		return baseValue;
	}
	
}
