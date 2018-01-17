package controllers;

import enums.Branch;
import enums.MonsterDifficulty;
import lib.ConsoleIO;
import models.monsters.Beholder;
import models.monsters.base.Monster;

public class MainCampaign extends Quest {

	public MainCampaign(GameMaster gm) {
		super(gm, "Main Campaign");

	}

	public void runCampaign() {
		String[] openingScene = { "You Have Finally Made it...", "Castle Black... the Forbidden Keep...",
				"You were tasked investigate a disturbance in the region and all your leads brought you here" };

		displayTextBlock(openingScene);

		boolean ready = ConsoleIO.promptForBool("Are You Ready For What Lies Ahead", "Y", "N");

		if (ready) {

			phaseOne();

		} else {

			System.out.println("Come back when you are...");

		}
	}

	private void phaseOne() {
		System.out.println();
		Monster[] currentEnemies = setEnemies(MonsterDifficulty.EASY, 5);

		String[] openingScene = {
				"As you approach Castle Black, you see a giant moat and a " + currentEnemies[0].getName()
						+ " on top of the castle walls manning a drawbridge.",
				"The " + currentEnemies[0].getName() + " yells out \"What Business do you have in this place.\"",
				"What would you like To do?" },
				choices = { "Respond to the " + currentEnemies[0].getName() + ".", "Look down the moat.",
						"Find another way into the castle.", "Do Nothing.", "Run Away to fight another day." };

		String[][] resultOpening = {
				{ "You attempt to tell the " + currentEnemies[0].getName()
						+ " that you seek the owner of this castle.", },
				{ "As you are looking down you hear the drawbridge lower and see a " + currentEnemies[1].getName()
						+ " running towards you." },
				{ "You try to find another way...",
						"You find another path into the castle but it is guarded by a " + currentEnemies[2].getName()
								+ ".",
						"You attempt to Sneak Pass the " + currentEnemies[2].getName() + "." },
				{ "As you wait and contemplate life you feel a strong presence drawing you to the back of the castle.",
						"Following the siren like call you see a " + currentEnemies[3].getName()
								+ " calmly sitting down gazing upon the world", "You attempt to walk past the "
								+ currentEnemies[3].getName() + "." },
				{ "As you begin to run..." } },

				results = {
						{ "The " + currentEnemies[0].getName()
								+ " responds with an angry grunt and starts to attack you." },
						{ "The " + currentEnemies[0].getName() + " allows you to come in and speak with him." },
						{ "The " + currentEnemies[1].getName() + " runs up and attacks you." },
						{ "The " + currentEnemies[1].getName() + " attempts to attack you but,",
								" they get one look at the determination in your eyes and run away" },
						{ "As you attempt to sneak the " + currentEnemies[2].getName()
								+ " spots you and immediately attacks you." },
						{ "you go into the castle unnoticied." },
						{ "As you try to walk past the " + currentEnemies[3].getName() + " it attacks you." },
						{ "You walk past with no problems. " },

						{ "You hear the drawbridge lower and see a " + currentEnemies[4].getName()
								+ " come rushing down and attacks you" },
						{ "You feel a strange powerful force coming towards you" } };

		int[] chances = { 70, 60, 60, 10, 10 };

		Branch currentDecision = findBranch5(openingScene, choices, resultOpening, results, chances, currentEnemies);

		if (currentDecision != null) {
			switch (currentDecision) {

			case SIDE_QUEST:

				runPrisonersOfAlkaTraz();

				break;

			case NEXT_PHASE:

				phaseTwo();

				break;

			case FINAL_PHASE:

				finalPhase();

				break;

			default:
				throw new IllegalArgumentException("Not a valid Enum.");
			}
		} else {
			System.out.println("You lose.");

		}
	}

	private void phaseTwo() {

		Monster[] currentEnemies = setEnemies(MonsterDifficulty.MEDIUM, 3);

		String[] openningScene = { "Finally inside, you see a " + currentEnemies[0].getName() + " patrolling the area.",
				"What would you like to do?" },
				choices = { "Attempt to Sneak Past the " + currentEnemies[0] + ".",
						"Rush forward and fight the " + currentEnemies[0] + ".", "Turn tail and run." };

		String[][] resultOpening = {
				{ "You begin to look around and sneak past the " + currentEnemies[0].getName()
						+ " you see a small hatch door on the ground.", "You attempt to go for the door." },
				{ "You begin to rush towards the enemy." }, { "You begin to run away... wow..." } },
				results = {
						{ "As you go for the hatch you are attack by the " + currentEnemies[0].getName()
								+ " who finally spots you." },
						{ "you are able to open the door quietly and go through." },
						{ "As you rush towards the " + currentEnemies[0].getName() + " you are quickly stopped by a "
								+ currentEnemies[1].getName() + ".", currentEnemies[1].getName() + " attacks you " },
						{ "You rush the " + currentEnemies[1].getName() + " and gain advantage" },
						{ "As you attempt to run you are quickly stopped by a " + currentEnemies[2].getName() + ".",
								currentEnemies[2].getName() + " attacks you." },
						{ "As you run you feel a strong presence near by." } };

		int[] chances = { 30, 20, 5 };

		Branch currentDecision = findBranch3(openningScene, choices, resultOpening, results, chances, currentEnemies);

		if (currentDecision != null) {
			switch (currentDecision) {

			case SIDE_QUEST:

				this.runScraglersKeep();

				break;

			case NEXT_PHASE:

				sarah.runEncounter(currentEnemies[0], true);

				this.phaseThree();

				break;

			case FINAL_PHASE:

				this.finalPhase();

				break;
			default:
				throw new IllegalArgumentException("Unsupported Enum");

			}
		} else {

			System.out.println("You lost....");
		}

	}

	private void phaseThree() {

		Monster[] currentEnemies = setEnemies(MonsterDifficulty.EXPERT, 2);
		String[] openningScene = { "Still reeling from your last fight you attempt to keep moving forward.",
				"Finally catching your breathe you keep on moving foward slowly.",
				"This leads you to a giant chamber with four stone columns in the center of the room.",
				"You see on old book in the middle and a giant metal door that seems to be sealed tight.",
				"What would you like to do?" }, choices = { "Go and Examine the book", "Try and open the door" };

		String[][] resultOpenings = { { "As you examine the book its pages begin flying out soaring through the room.",
				"You begin to read some incantations off the pages that you see reading aloud, \"Ers Septum Aludas\"" },
				{ "Trying to open the door you hear a giant creek and see the door slowly open.",
						"Emerging from the door is a " + currentEnemies[1].getName() + " and it is piping mad.",
						"The " + currentEnemies[1].getName() + " Screams\"WHY DID YOU WAKE ME FROM MY SLUMBER?\"" } },
				results = {
						{ "You accidently open a portal to another dimension and a " + currentEnemies[0].getName()
								+ " apears.", "The " + currentEnemies[0].getName() + " attacks you." },
						{ "Nothing happens and you feel a faint sense of relief." },
						{ "Before you respond the " + currentEnemies[1].getName() + " rushes you and attacks." },
						{ "You quickly Tell the" + currentEnemies[1].getName()
								+ " \"Im just here to find your boss I'm so sorry that I woke you up\".",
								"they reply \"Well okay his room is right there on the left go bother him\"",
								"You feel a very Powerful Presence emenating from the door." } };

		int[] successChance = { 50, 30 };

		Branch choice = this.findBranchUltimatum(openningScene, choices, resultOpenings, results, successChance,
				currentEnemies);

		if (choice != null) {
			switch (choice) {

			case NEXT_PHASE:
				finalPhase();
				break;
			case FINAL_PHASE:
				finalPhase();
				break;
			default:
				throw new IllegalArgumentException("Unsupported Enum");
			}
		} else {

			System.out.println("You lost....");

		}

	}

	private void finalPhase() {

		System.out.println("The Great Pressence You feel shakes you to your very core.");
		System.out.println("You see The Strongest Being in Existence a BEHOLDER!!!");
		System.out.println("Daz Boned...");

		boolean winner = sarah.runEncounter(new Beholder(60, 60, 60), false);

		if (winner) {
			System.out.println(
					"You finally did, it all the crazy things happening around have calmed down and now you can relax.");
		} else {
			System.out.println("you lost....wow....git..gud...");
		}

	}

	private void runPrisonersOfAlkaTraz() {

		boolean win = (new PrisonerOfAlkaTraz(this.sarah)).run();
		if (win) {
			finalPhase();
		}
	}

	private void runScraglersKeep() {
		(new ScraglersKeep(this.sarah)).run();

		System.out.println("You  feel a great pressence rushing towards you.");

		finalPhase();
	}

}
