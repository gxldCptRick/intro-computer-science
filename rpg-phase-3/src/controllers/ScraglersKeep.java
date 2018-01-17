package controllers;

import models.monsters.Oni;

public class ScraglersKeep extends Quest {

	public ScraglersKeep(GameMaster gm) {
		super(gm, "Scraglers Keep");
	}
	
	public void run() {
		
		firstPhase();
		
	}

	private void firstPhase() {
		System.out.println("Here is a cookie");
		sarah.dropItems(new Oni(0,0,0));
		
	}

}
