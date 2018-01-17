package controllers;

import enums.Branch;
import enums.MonsterDifficulty;
import models.monsters.BugBear;
import models.monsters.Goblin;
import models.monsters.Oni;
import models.monsters.base.Monster;

public class PrisonerOfAlkaTraz extends Quest {

	public PrisonerOfAlkaTraz(GameMaster gm) {
		super(gm, "Prisoner Of AlkaTraz");

	}

	public boolean run() {
		boolean win = phaseOne();

		return win;
	}

	private boolean phaseOne() {
		Monster[] currentEnemies = setEnemies(MonsterDifficulty.MEDIUM, 3);
		String[] openningScene = { "You Walk across the bridge and Enter the castle...",
				"As you enter you see yourself surrounded by cages and traps that all seem to be empty.",
				"At the end of it all you see a sealed metal door covered in blood.", "What would you like to do?" };

		String[] choices = { "Try to open the door.", "Look around to see more.", "Run Away" };

		String[][] resultOpening = {
				{ "In trying to open the door you find an old rusty crowbar.",
						"Attempting to use the crowbar you hear an enormous crack and the bar is destroyed." },
				{ "As you look around you see a wall that looks peculiar.",
						"you begin to move towards the wall for futher examination.",
						"Upon further examination you see that the wall was fake." },
				{ "Running Away you begin to flee in terror of what lies ahead." } },
				results = {
						{ "The sound awakened a slumbering " + currentEnemies[0].getName() + " that was nearby.",
								"The " + currentEnemies[0].getName() + " wakes up to attack you" },
						{ "The sound shakes the door loose...", "Apparently it was a very poorly made door." },
						{ "Before you can react you are attacked by a mysterious monster." },
						{ "You Decide to go through the fake wall." },
						{ "As you are running you get stopped by a " + currentEnemies[2].getName()
								+ " blocking your path." },
						{ "As you run you feel a great pressence desending upon you..." } };

		int[] chances = { 30, 40, 60 };

		Branch currentDecision = findBranch3(openningScene, choices, resultOpening, results, chances, currentEnemies);

		boolean win = true;

		if (currentDecision != null) {
			switch (currentDecision) {

			case SIDE_QUEST:

				System.out.println("You find the remains of a dead Goblin....");

				lootRoomGood();

				win = phaseTwo();

				break;

			case NEXT_PHASE:

				System.out.println("You find the remains of a dead BugBear....");

				lootRoomGreat();

				win = phaseTwo();

				break;

			case FINAL_PHASE:

				break;

			default:
				throw new IllegalArgumentException("Unsupported Enum.");
			}
		} else {

			System.out.println("You lose");
			win = false;
		}

		return win;
	}

	private boolean phaseTwo() {
		Monster[] currentEnemies = setEnemies(MonsterDifficulty.KAMI, 2);

		String[] openningScene = { "After finding some sweet loot you keep going deeper and deeper into the castle.",
				"You eventually find what looks like an abandoned library within the castle walls.",
				"You see books burned and scattered throught the room as if it had been looted or destroyed.",
				"You also see broken windows and a few slivers of light still seeping into the room.",
				"You then notice an enormous statue of a gryffindor in the center of the room.",
				"At the base of this great statue you see faded text coverd with blood marks as if someone had been attacked recently.",
				"What would you like to do?" };

		String[] choices = { "Look for a useful book around", "Try to read the text at the base of the statue." };

		String[][] resultOpening = { {
				"As you look around you see that a large pile ash is still pulsating with the embers of the flames that once was.",
				"You approach the pile and although hot you try to smother the light flames.",
				"Sorting through the now dead ash you see a sealed black book that seems to be written in a text you don't understand",
				"Upon further examination of the book you see images of great beasts that once roamed throught the land." },
				{ "Trying to read the text on the base of the statue you begin to recognize a name.",
						"It is the name of your father a great warrior from the past.",
						"This statue may have been honoring his heroic deeds of his past life.",
						"Teary-eyed you begin to get up." } },
				results = {
						{ "You begin to attempt to read this great book and out of no where you are attacked by a "
								+ currentEnemies[0].getName() + "." },
						{ "You begin trying to read this book but as you sound the letters out the book burns itself and turns to ash." },
						{ "As you rise you are attacked out of no where by a " + currentEnemies[1].getName() + "." },
						{ "As you read You begin to feel a sense of terror and dread as if something powerful is heading your way." } };

		int[] chances = { 30, 40, 60 };

		Branch currentDecision = findBranchUltimatum(openningScene, choices, resultOpening, results, chances,
				currentEnemies);

		boolean win = true;

		if (currentDecision != null) {
			switch (currentDecision) {

			case NEXT_PHASE:

				win = finalPhase();

				break;

			case FINAL_PHASE:
				System.out.println("You are filled with determination.");
				break;

			default:
				throw new IllegalArgumentException("Unsupported Enum.");
			}
		}else {
			win = false;
			System.out.println("You lose...");
			
		}

		return win;
	}

	private boolean finalPhase() {
		Oni BossFight = new Oni(50, 50, 50);
		BossFight.setName("Gerald");
		System.out.println("From the Ashes a grand portal opens and an ONI APPEARS!?!?!?");
		System.out.println("He screams \"I AM " + BossFight.getName() + " HOW DARE YOU DISTURBE MY SANCTUARY!!!!\"");
		System.out.println("Gerald leaps forward and fights you");

		boolean win = sarah.runEncounter(BossFight, false);

		if (win)
			System.out.println("After the fight you feel a great pressence quickly aproaching.");
		else
			System.out.println("You lost");

		return win;

	}

	private void lootRoomGreat() {

		this.sarah.dropItems(new BugBear());

	}

	private void lootRoomGood() {

		this.sarah.dropItems(new Goblin());

	}

}
