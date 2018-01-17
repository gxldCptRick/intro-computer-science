package controllers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import enums.Branch;
import enums.MonsterDifficulty;
import lib.ConsoleIO;
import models.dice.DiceBag;
import models.monsters.*;
import models.monsters.base.*;

public class Quest {

	protected static final DiceBag myVeryOwnBag = new DiceBag();

	protected GameMaster sarah;

	private String name;

	public Quest(GameMaster gm, String name) {

		sarah = gm;

		this.name = name;

	}

	public String getName() {
		return this.name.trim();
	}

	protected Branch findBranchUltimatum(String[] openingScene, String[] choices, String[][] resultOpening,
			String[][] results, int[] successChance, Monster[] enemies) {

		Branch decision = null;
		displayTextBlock(openingScene);

		int choice = ConsoleIO.promptForMenuSelection(choices, false);

		switch (choice) {

		case 1:
			decision = displayOutcome(Branch.NEXT_PHASE, resultOpening[0], results[1], results[0],  successChance[0], enemies[0]);
			break;

		case 2:
			decision = displayOutcome(Branch.FINAL_PHASE, resultOpening[1], results[3], results[2],  successChance[1], enemies[1]);
			break;

		default:
			throw new IllegalArgumentException(
					"Hey guess what you did it.. you broke this beautiful game... by adding a new choice and breaking it");

		}

		return decision;

	}

	protected Branch findBranch3(String[] openingScene, String[] choices, String[][] resultOpening, String[][] results,
			int[] successChance, Monster[] enemies) {

		Branch decision = null;
		displayTextBlock(openingScene);

		int choice = ConsoleIO.promptForMenuSelection(choices, false);

		switch (choice) {

		case 1:
			decision = displayOutcome(Branch.SIDE_QUEST, resultOpening[0], results[1], results[0], successChance[0], enemies[0]);
			break;

		case 2:
			decision = displayOutcome(Branch.NEXT_PHASE, resultOpening[1], results[3], results[2],  successChance[1], enemies[1]);
			break;

		case 3:
			decision = displayOutcome(Branch.FINAL_PHASE, resultOpening[2], results[5], results[4],  successChance[2], enemies[2]);
			break; 
		default:
			throw new IllegalArgumentException(
					"Hey guess what you did it.. you broke this beautiful game... by adding a new choice and breaking it");

		}

		return decision;

	}

	protected Branch findBranch4(String[] openingScene, String[] choices, String[][] resultOpening, String[][] results,
			int[] successChance, Monster[] enemies) {

		Branch decision = null;
		displayTextBlock(openingScene);

		int choice = ConsoleIO.promptForMenuSelection(choices, false);

		switch (choice) {

		case 1:
			decision = displayOutcome(Branch.SIDE_QUEST, resultOpening[0], results[1], results[0],  successChance[0], enemies[0]);
			break;

		case 2:
			decision = displayOutcome(Branch.SIDE_QUEST, resultOpening[1], results[3], results[2],  successChance[1], enemies[1]);
			break;

		case 3:
			decision = displayOutcome(Branch.NEXT_PHASE, resultOpening[2], results[5], results[4],  successChance[2], enemies[2]);
			break;

		case 4:
			decision = displayOutcome(Branch.FINAL_PHASE, resultOpening[3], results[7], results[6],  successChance[3], enemies[3]);
			break;

		default:
			throw new IllegalArgumentException(
					"Hey guess what you did it.. you broke this beautiful game... by adding a new choice and breaking it");

		}

		return decision;

	}

	protected Branch findBranch5(String[] openingScene, String[] choices, String[][] resultOpening, String[][] results,
			int[] successChance, Monster[] enemies) {

		Branch decision = null;
		displayTextBlock(openingScene);

		int choice = ConsoleIO.promptForMenuSelection(choices, false);

		switch (choice) {

		case 1:
			decision = displayOutcome(Branch.SIDE_QUEST, resultOpening[0], results[1], results[0], successChance[0], enemies[0]);
			break;

		case 2:
			decision = displayOutcome(Branch.SIDE_QUEST, resultOpening[1], results[3], results[2], successChance[1], enemies[1]);
			break;

		case 3:
			decision = displayOutcome(Branch.NEXT_PHASE, resultOpening[2],  results[5], results[4], successChance[2], enemies[2]);
			break;

		case 4:
			decision = displayOutcome(Branch.NEXT_PHASE, resultOpening[3], results[7], results[6],  successChance[3], enemies[3]);
			break;

		case 5:
			decision = displayOutcome(Branch.FINAL_PHASE, resultOpening[4], results[9], results[8],  successChance[4], enemies[4]);
			break;

		default:
			throw new IllegalArgumentException(
					"Hey guess what you did it.. you broke this beautiful game... by adding a new choice and breaking it");

		}

		return decision;

	}

	private Branch displayOutcome(Branch outcome, String[] resultOpening, String[] goodResult, String[] badResult,
			int successChance, Monster enemy) {

		Branch foundBranch = null;

		displayTextBlock(resultOpening);

		int chance = myVeryOwnBag.rollDie(100);

		boolean fightSuccessful;

		if (chance < successChance) {

			displayTextBlock(badResult);

			fightSuccessful = sarah.runEncounter(enemy, false);
			
			if (fightSuccessful) {

				foundBranch = outcome;

			}

		} else {

			displayTextBlock(goodResult);

			foundBranch = outcome;

		}

		return foundBranch;
	}

	protected Monster[] setEnemies(MonsterDifficulty difficulty, int amount) {
		Monster[] enemiesSpawned = new Monster[amount];
		int chance;
		int[] min, max;

		int[][] GoblinStats = { { 5, 5, 5 }, { 10, 10, 10 } };
		int[][] BugBearStats = { { 10, 10, 10 }, { 15, 15, 15 } };
		int[][] BeholderStats = { { 20, 20, 20 }, { 50, 50, 50 } };
		int[][] BeastStats = { { 15, 15, 15 }, { 30, 30, 30 } };
		int[][] GodStats = { { 50, 50, 50 }, { 70, 70, 70 } };

		switch (difficulty) {

		case EASY:

			for (int i = 0; i < enemiesSpawned.length; i++) {

				chance = myVeryOwnBag.rollDie(100);

				if (chance < 51) {

					Goblin jimmy = spawnMonster(Goblin.class, GoblinStats[0], GoblinStats[1]);

					if (jimmy != null) {

						enemiesSpawned[i] = jimmy;

					}

				} else {

					BugBear gerald = spawnMonster(BugBear.class, BugBearStats[0], BugBearStats[1]);

					if (gerald != null) {

						enemiesSpawned[i] = gerald;
					}

				}
			}

			break;

		case MEDIUM:

			for (int i = 0; i < enemiesSpawned.length; i++) {
				enemiesSpawned[i] = this.spawnMonster(BugBear.class, BugBearStats[0], BugBearStats[1]);
			}

			break;

		case HARD:

			min = BeastStats[0];
			max = BeastStats[1];

			for (int i = 0; i < enemiesSpawned.length; i++) {

				chance = myVeryOwnBag.rollDie(100);

				if (chance > 80) {

					enemiesSpawned[i] = this.spawnMonster(Oni.class, min, max);

				} else if (chance > 65) {

					enemiesSpawned[i] = this.spawnMonster(Ogre.class, min, max);

				} else if (chance > 30) {

					enemiesSpawned[i] = this.spawnMonster(Orc.class, min, max);

				} else {

					enemiesSpawned[i] = this.spawnMonster(BugBear.class, min, max);
				}

			}

			break;

		case EXPERT:
			min = BeholderStats[0];
			max = BeholderStats[1];

			for (int i = 0; i < enemiesSpawned.length; i++) {

				enemiesSpawned[i] = this.spawnMonster(Beholder.class, min, max);

			}

			break;

		case KAMI:

			min = GodStats[0];
			max = GodStats[1];

			for (int i = 0; i < enemiesSpawned.length; i++) {
				chance = myVeryOwnBag.rollDie(100);

				if (chance > 74) {

					enemiesSpawned[i] = (Beholder)this.spawnMonster(Beholder.class, min, max);

				} else if (chance > 49) {

					enemiesSpawned[i] = this.spawnMonster(Oni.class, min, max);

				} else if (chance > 24) {

					enemiesSpawned[i] = this.spawnMonster(Orc.class, min, max);

				} else {

					enemiesSpawned[i] = this.spawnMonster(Ogre.class, min, max);

				}

			}

			break;
		default:
			throw new IllegalArgumentException("you passed in an unsupported addition to the Monster Difficutly enum.");
		}

		return enemiesSpawned;
	}

	private <T> T spawnMonster(Class<T> monster, int[] minStats, int[] maxStats) {
		T monsterSpawned = null;
		int str = findStats(minStats[0], maxStats[0]), dex = findStats(minStats[1], maxStats[1]),
				intel = findStats(minStats[2], maxStats[2]);
		try {

			Constructor<T> monsterConstructor = monster.getConstructor(int.class, int.class, int.class);

			monsterSpawned = monsterConstructor.newInstance(str, dex, intel);

		} catch (NoSuchMethodException e) {

			e.printStackTrace();

		} catch (SecurityException e) {

			e.printStackTrace();

		} catch (InstantiationException e) {

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();

		} catch (InvocationTargetException e) {

			e.printStackTrace();

		}

		return monsterSpawned;
	}

	private int findStats(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("Cant have a min greater than a respective max.");
		}

		int trueMin = min - 1;

		int stat = myVeryOwnBag.rollDie(max - trueMin, trueMin);

		return stat;
	}

	protected void displayTextBlock(String[] textBlock) {
		for (String textLine : textBlock) {

			System.out.println(textLine);

		}

		ConsoleIO.waitForInput();
		System.out.println();
	}

}
