package controllers;

import java.util.ArrayList;
import java.util.List;

import enums.TurnAction;
import enums.monster.Bonus;
import enums.potions.PotionType;
import lib.ConsoleIO;
import models.dice.DiceBag;
import models.items.Armor;
import models.items.Weapon;
import models.monsters.Hero;
import models.monsters.base.Monster;
import models.monsters.interfaces.Droppable;
import models.monsters.interfaces.MagicUser;
import models.potions.base.Potion;

/**
 * <h1>GameMaster Controller Object.</h1>
 * <p>
 * this runs a simply RPG.
 * </p>
 * 
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */

public class GameMaster {

	/**
	 * this is the collection of players in the current game session.
	 */
	private Hero[] players = new Hero[1];

	/**
	 * this is the DiceBag object that the Game Master uses.
	 */
	private static final DiceBag steve = new DiceBag();

	/*
	 * this is was the monster that would fight the players.
	 */
	// private Monster jimmy;

	/**
	 * this determines whether or not initiative has been rolled yet for the current
	 * game.
	 */
	private boolean initiativeRolled = false;

	/**
	 * the total amount of turns that have already been run.
	 */
	private int totalTurns = 0;

	/**
	 * this method starts the GameMaster object.
	 */
	public void start() {
		boolean appActive = true;
		do {
			System.out.println("Welcome To Hello Kitty Island Adventure");
			System.out.println();
			int choice = ConsoleIO.promptForMenuSelection(new String[] { "Play Hello Kitty Island Adventure" }, true);

			if (choice == 1) {
				startGame();
			} else if (choice == 0) {
				appActive = false;
			} else {
				throw new IllegalArgumentException("Not a Valid Menu Choice.");
			}
		} while (appActive);
	}

	/**
	 * this method controls the flow of the game.
	 */
	private void startGame() {
		boolean gameActive = false, newHero = true;
		String[] menu = { "Play again with new hero.", "Play again with same hero." };
		int choice;

		do {
			if (newHero) {

				createHeros();

			} else {

				restoreHeros();

			}

			(new MainCampaign(this)).runCampaign();

			choice = ConsoleIO.promptForMenuSelection(menu, true);

			switch (choice) {

			case 1:
				newHero = true;
				gameActive = true;
				break;

			case 2:
				newHero = false;
				gameActive = true;
				break;

			case 0:
				gameActive = false;
				System.out.println("Goodbye Fare Travler.");
				System.out.println();
				break;

			default:
				break;
			}

		} while (gameActive);

	}

	private void restoreHeros() {

		for (Hero player : players) {
			if (player != null)
				player.restoreHero();
		}

	}

	public DiceBag borrowDiceBag() {
		return GameMaster.steve;
	}

	public boolean runEncounter(Monster monster, boolean advantage) {

		boolean fightSuccess = false;

		boolean fightNotFinished = true;

		displayGameInfo(monster);

		chooseWeapon();

		do {

			for (int i = 0; i < players.length && fightNotFinished; i++) {

				if (players[i].isAlive()) {

					turnOfCombat(players[i], monster, advantage);

				} else {

					fightNotFinished = false;

				}

			}

			totalTurns++;

			fightNotFinished = fightNotFinished ? monster.isAlive() : false;

		} while (fightNotFinished);

		fightSuccess = !monster.isAlive() && players[0].isAlive();

		if (fightSuccess) {

			System.out.println("You Killed " + monster.getName());

			dropItems((Droppable) monster);

		} else {
			System.out.println("You have been killed");
		}

		totalTurns = 0;

		initiativeRolled = false;

		return fightSuccess;

	}

	private void chooseWeapon() {
		String[] names = players[0].getArrayOfWeaponNames();

		List<String> weaponList = new ArrayList<>(6);

		for (String name : names) {

			if (name != null) {

				weaponList.add(name);

			}
		}

		if (weaponList.size() > 0) {

			String[] weapons = new String[weaponList.size()];

			weapons = weaponList.toArray(weapons);

			int choice = ConsoleIO.promptForMenuSelection(weapons, false);

			switch (choice) {
			case 1:
				players[0].equipWeapon(0);
				break;
			case 2:
				players[0].equipWeapon(1);
				break;
			case 3:
				players[0].equipWeapon(2);
				break;
			default:
				break;

			}

		}

	}

	/**
	 * this method fills the players array with a new hero.
	 */
	private void createHeros() {

		players = new Hero[1];

		for (int i = 0; i < players.length; i++) {

			players[i] = setUpHero();

		}

	}

	/**
	 * this method sets a heros name of a randomly generated hero that has been
	 * approved by the user.
	 * 
	 * @return returns a hero that has been approved and named by the user.
	 */
	private Hero setUpHero() {

		boolean notHappy;
		Hero deku = null;

		do {

			deku = rollHero();

			System.out.println(deku);

			notHappy = ConsoleIO.promptForBool("Are You Happy With This Character? (Y/N)", "N", "Y");

		} while (notHappy);

		String name = getName();

		if (name.equals("ALL MIGHT")) {

			deku = new Hero();

		} else {

			deku.setName(name);

		}

		return deku;
	}

	/**
	 * generates a hero based on randomly rolled attributes.
	 * 
	 * @return returns a randomly generated hero.
	 */
	private Hero rollHero() {
		int str = calculateAttr(), dex = calculateAttr(), intel = calculateAttr();

		Hero bakugo = new Hero(str, dex, intel);
		return bakugo;
	}

	/**
	 * this method generates monster drops and prompts the user if they would like
	 * the specific drops.
	 * 
	 * @param monster
	 *            the monster that will generate drops
	 * 
	 */
	public void dropItems(Droppable monster) {

		Armor dropA = monster.generateArmorDrop();
		Weapon dropW = monster.generateWeaponDrop();
		Potion[] dropPs = monster.generatePotionsDrop();
		boolean takeDrop = false, inventoryFull = true;

		if (dropA != null) {

			takeDrop = ConsoleIO.promptForBool(
					"Would you like to pick up " + dropA.NAME + "? (Y/N)\n This will replace your current armor.", "Y",
					"N");

			if (takeDrop) {
				players[0].equipArmor(dropA);
			} else {
				System.out.println("You left behind " + dropA.NAME + ".......");
			}

		}
		if (dropW != null) {

			takeDrop = ConsoleIO.promptForBool("Would you like to pick up " + dropW.NAME + "? (Y/N)", "Y", "N");

			if (takeDrop) {
				for (String string : players[0].getArrayOfWeaponNames()) {

					if (string == null) {
						inventoryFull = false;
					}

				}

				if (inventoryFull) {
					System.out.println("Select a weapon to replace");
					int choice = ConsoleIO.promptForMenuSelection(players[0].getArrayOfWeaponNames(), false);
					players[0].storeWeapon(dropW, choice - 1);
				} else {

					players[0].storeWeapon(dropW);

				}
			} else {
				System.out.println("You left behind " + dropW.NAME + ".........");
			}
		}

		if (dropPs != null) {
			for (Potion pots : dropPs) {
				if (pots != null)
					players[0].addPotion(pots);
			}
		}
	}

	/**
	 * this method prompts the user for a name of the hero and if not given one it
	 * returns "The Unnamed Hero"
	 * 
	 * @return returns a name for the hero.
	 */
	private String getName() {
		String name = ConsoleIO.promptForInput("Name Your Character", true);

		name = name.trim();

		if (name.isEmpty()) {

			name = "The Unnamed Hero";

		}

		return name;
	}

	/**
	 * calculates an attributes based on a dice rolling recipe.
	 * 
	 * @return returns the number for an attribute calculated.
	 */
	private int calculateAttr() {

		int baseValue = steve.rollDice(6, 3);

		if (baseValue > 15) {

			int bonus = steve.rollDie(6);

			baseValue += bonus == 6 ? bonus + steve.rollDie(6) : bonus;

		}
		return baseValue;
	}

	/**
	 * this method generates a monster based on a choice made on the user.
	 */

	// deprecated method.
	/*
	 * private void generateMonster() { String[] menu = { "Goblin", "BugBear",
	 * "Beholder" }; int choice = ConsoleIO.promptForMenuSelection(menu, false), str
	 * = 0, dex = 0, intel = 0;
	 * 
	 * switch (choice) { case 1: str = steve.rollDie(2, 5); dex = steve.rollDie(5,
	 * 5); intel = steve.rollDie(5); jimmy = new Goblin(str, dex, intel); break;
	 * case 2: str = steve.rollDie(20, 5); dex = steve.rollDie(15, 5); intel =
	 * steve.rollDie(5, 5); jimmy = new BugBear(str, dex, intel); break; case 3: str
	 * = steve.rollDie(20, 10); dex = steve.rollDie(10, 5); intel =
	 * steve.rollDie(10, 30); jimmy = new Beholder(str, dex, intel); break; default:
	 * throw new IllegalArgumentException(
	 * "You Can't Input an invalid choice but somehow you did steve... i know yourname may not be steve but still... fork you steve."
	 * ); }
	 * 
	 * }
	 */

	/**
	 * this method runs a turn of combat based on a single player.
	 * 
	 * @param player
	 *            the current player.
	 */
	private void turnOfCombat(Hero player, Monster monster, boolean advantage) {
		String message = "";
		if (!initiativeRolled && totalTurns % 5 == 0) {

			rollInitiative(player, monster, advantage);
		}

		displayTurnInfo(player, monster);

		if (player.getInitiative() >= monster.getInitiative()) {

			if (player.isAlive()) {

				message = playerTurn(player, monster);

				message += "\n";
			}

			if (monster.isAlive()) {

				message += monsterTurn(monster, player);

			}

		} else {

			if (monster.isAlive()) {

				message = monsterTurn(monster, player);

				message += "\n";
			}

			if (player.isAlive()) {

				message += playerTurn(player, monster);

			}

		}

		System.out.println(message);
		ConsoleIO.waitForInput();

	}

	/**
	 * this takes in a target player and performs an attack for the monster based on
	 * certain conditions.
	 * 
	 * @param player
	 *            the target player.
	 */
	private String monsterTurn(Monster monster, Hero player) {
		String message;

		int choice = steve.rollDie(100), damage;

		if (isMagicUser(monster)) {

			int magicChoice = steve.rollDie(5);

			damage = findMagicDamage(magicChoice, monster);

			message = attackEnemy(monster, player, damage);

		} else {

			if (choice < 10) {
				int drink = monster.usePotion(PotionType.MANA);
				if (drink < 0) {
					message = monster.getName() + " has wasted their turn";
				} else {
					message = monster.getName() + " restored " + drink + " mana.";
				}
			}
			if (choice < 50) {

				int drink = monster.usePotion(PotionType.HEALTH);
				if (drink < 0) {
					message = monster.getName() + " has wasted their turn";
				} else {
					message = monster.getName() + " healed for " + drink;
				}

			} else if (choice < 71) {

				damage = rollForDamage(monster.attackNormal());

				message = attackEnemy(monster, player, damage);

			} else {

				damage = rollForDamage(monster.attackSpecial());

				message = attackEnemy(monster, player, damage);

			}
		}
		return message;
	}

	private int findMagicDamage(int choice, Monster monster) {
		MagicUser gandolf = (MagicUser) monster;

		int magicAttack;

		switch (choice) {

		case 1:

			magicAttack = gandolf.castLevelOneSpell();

			break;

		case 2:

			magicAttack = gandolf.castLevelTwoSpell();

			break;

		case 3:

			magicAttack = gandolf.castLevelThreeSpell();

			break;

		case 4:

			magicAttack = rollForDamage(monster.attackNormal());

			break;

		default:

			magicAttack = 0;

			break;

		}

		return magicAttack;
	}

	/**
	 * this method performs a turn for the player passed in.
	 * 
	 * @param player
	 *            the current player.
	 * @return
	 */
	private String playerTurn(Hero player, Monster monster) {
		boolean haveHealthPots = player.potionsLeft(PotionType.HEALTH) > 0,
				haveManaPots = player.potionsLeft(PotionType.MANA) > 0;

		TurnAction action = displayMenuChoice(haveHealthPots, haveManaPots);

		String message = performAction(player, monster, action);

		return message;
	}

	/**
	 * this method take in a player and action and does the requested action.
	 * 
	 * @param player
	 *            the current player who is directly affected by the action.
	 * @param action
	 *            the action that was chosen.
	 */
	private String performAction(Hero player, Monster monster, TurnAction action) {
		String message = "I DONT KNOW YOU";

		int attack;

		int[] damageDice;

		switch (action) {
		case NORMAL_ATTACK:

			damageDice = player.attackNormal();

			attack = rollForDamage(damageDice);

			message = attackEnemy(player, monster, attack);

			break;

		case SPECIAL_ATTACK:

			damageDice = player.attackSpecial();

			attack = rollForDamage(damageDice);

			message = attackEnemy(player, monster, attack);

			break;

		case RESTORE_HEALTH:

			message = playerUsePotion(player, PotionType.HEALTH);

			break;

		case RESTORE_MANA:

			message = playerUsePotion(player, PotionType.MANA);

			break;

		default:

			throw new IllegalArgumentException("You added a new non-supported enum.");

		}

		return message;
	}

	/**
	 * this method rolls and sets the initiative of every monster in the game.
	 */
	private void rollInitiative(Hero player, Monster monster, boolean advantage) {

		int initiative = steve.rollDie(20), secondRoll;
		monster.setInitiative(initiative);

		if (advantage) {
			initiative = steve.rollDie(20);
			secondRoll = steve.rollDie(20);

			initiative = initiative < secondRoll ? secondRoll : initiative;

		} else {
			initiative = steve.rollDie(20);
		}

		player.setInitiative(initiative);

	}

	/**
	 * a method to display a menu with specific actions depending on if there are
	 * any health potions or mana potions.
	 * 
	 * @param healthPots
	 *            a boolean that determines whether or not there was any health
	 *            potions.
	 * @param manaPots
	 *            a boolean that determines whether or not there were any mana
	 *            potions.
	 * @return returns a TurnAction enum.
	 */
	private TurnAction displayMenuChoice(boolean healthPots, boolean manaPots) {

		String[] menu;

		TurnAction action = null;

		if (healthPots && manaPots) {

			menu = new String[] { "Perform Normal Attack", "Perform Special Attack", "Use Health Potion",
					"Use Mana Potion" };

			int choice = ConsoleIO.promptForMenuSelection(menu, false);

			switch (choice) {
			case 1:
				action = TurnAction.NORMAL_ATTACK;
				break;
			case 2:
				action = TurnAction.SPECIAL_ATTACK;
				break;
			case 3:
				action = TurnAction.RESTORE_HEALTH;
				break;
			case 4:
				action = TurnAction.RESTORE_MANA;
				break;
			default:
				throw new IllegalArgumentException("You have Somehow Broke my menu selection.");
			}

		} else if (healthPots) {

			menu = new String[] { "Perform Normal Attack", "Perform Special Attack", "Use Health Potion" };

			int choice = ConsoleIO.promptForMenuSelection(menu, false);

			switch (choice) {
			case 1:
				action = TurnAction.NORMAL_ATTACK;
				break;
			case 2:
				action = TurnAction.SPECIAL_ATTACK;
				break;
			case 3:
				action = TurnAction.RESTORE_HEALTH;
				break;
			default:
				throw new IllegalArgumentException("You have Somehow Broke my menu selection.");
			}

		} else if (manaPots) {

			menu = new String[] { "Perform Normal Attack", "Perform Special Attack", "Use Mana Potion" };

			int choice = ConsoleIO.promptForMenuSelection(menu, false);

			switch (choice) {
			case 1:
				action = TurnAction.NORMAL_ATTACK;
				break;
			case 2:
				action = TurnAction.SPECIAL_ATTACK;
				break;
			case 3:
				action = TurnAction.RESTORE_MANA;
				break;
			default:
				throw new IllegalArgumentException("You have Somehow Broke my menu selection.");
			}

		} else {

			menu = new String[] { "Perform Normal Attack", "Perform Special Attack" };

			int choice = ConsoleIO.promptForMenuSelection(menu, false);

			switch (choice) {
			case 1:
				action = TurnAction.NORMAL_ATTACK;
				break;
			case 2:
				action = TurnAction.SPECIAL_ATTACK;
				break;
			default:
				throw new IllegalArgumentException("You have Somehow Broke my menu selection.");
			}

		}

		return action;
	}

	/**
	 * this method uses a potion and creates an output string based on how much it
	 * restored the specified stat.
	 * 
	 * @param player
	 *            the player that will use the potion.
	 * @param type
	 *            the type of potion that will be used
	 * @return returns a string detailing a player using a potion of the specified
	 *         type and how much that restored the corresponding stat
	 */
	private String playerUsePotion(Hero player, PotionType type) {
		String message = "Restored ";

		int statPoints = player.usePotion(type);

		if (type == PotionType.HEALTH) {

			message += statPoints + " Heatlh.  ";

		} else if (type == PotionType.MANA) {

			message += statPoints + " Mana.";

		} else {

			throw new IllegalArgumentException(
					"You... you monster... you added a new type without letting me know!?!?!!?");

		}

		return message;
	}

	/**
	 * this runs an attack and checks whether or not the attack landed and if it was
	 * super-effective or not.
	 * 
	 * @param attacker
	 *            attacking monster.
	 * @param defender
	 *            defending monster.
	 * @param attack
	 *            the damage from the attack.
	 * @return returns a string of detailing an attack on a defender.
	 */

	private String attackEnemy(Monster attacker, Monster defender, int attack) {
		int attackRoll, defendRoll, damage;
		String message = null;
		Armor defendingArmor = defender.getArmor();

		attackRoll = steve.rollDie(20);
		defendRoll = steve.rollDie(20);

		if ((attackRoll == 20 && defendRoll == 1) || (attackRoll == 1) || (defendRoll == 1) || (defendRoll == 20)) {

			if (attackRoll == 20 && defendRoll == 1) {
				damage = attack * 4;

				defender.recieveDamage(damage);

				message = "CRITICAL ADVANTAGE!!!\n";

				message += damageString(attacker.getName(), defender.getName(), damage);

			} else if ((attackRoll == 20 && defendRoll != 1) || (attackRoll != 20 && defendRoll == 1)) {

				damage = attack * 2;
				if (attackRoll == 20) {

					message = "CRITICAL STRIKE!!!\n";

				} else {

					message = "CRITICAL FAILURE TO DEFEND\n";

				}

				defender.recieveDamage(damage);

				message += damageString(attacker.getName(), defender.getName(), damage);

			} else {

				message = attacker.getName() + " missed their attack.";

			}

		} else {

			attackRoll += attacker.getBonus(Bonus.STRIKE);

			defendRoll += defender.getBonus(Bonus.DODGE);

			if (defendingArmor == null) {

				if (attackRoll > defendRoll) {

					damage = attack;

					defender.recieveDamage(damage);

					message = damageString(attacker.getName(), defender.getName(), damage);

				} else {
					message = attacker.getName() + " missed their attack.";
				}

			} else {

				defendRoll += defendingArmor.TYPE.DEFENSE_MODIFIER + defendingArmor.ATTR.DEFENSE_MODIFIER;

				if (attackRoll > defendRoll) {

					if (attackRoll > defendingArmor.TYPE.ARMOR_RATING + defendingArmor.ATTR.ARMOR_RATING) {

						damage = attack;

						defender.recieveDamage(damage);

						message = damageString(attacker.getName(), defender.getName(), damage);
					} else {

						message = attacker.getName() + " Couldn't break through the tough armor";

					}

				} else {

					message = attacker.getName() + " missed their attack.";

				}

			}
		}
		return message;
	}

	private boolean isMagicUser(Monster possibleMU) {
		boolean magicUser = MagicUser.class.isInstance(possibleMU);

		return magicUser;

	}

	private int rollForDamage(int[] attack) {
		int damage = steve.rollDice(attack[0], attack[1], attack[2]);

		if (damage < 0) {
			damage *= -1;
		}

		return damage;
	}

	/**
	 * this creates and returns a string detailing an attack on a defender.
	 * 
	 * @param attacker
	 *            the attackers name.
	 * @param defender
	 *            the defenders name.
	 * @param damage
	 *            the amount of damage done.
	 * @return returns a string of the detailing an attack on a defender with the
	 *         amount of damage.
	 */
	private String damageString(String attacker, String defender, int damage) {
		String damageString = attacker + " has dealt " + damage + " damage to " + defender;

		return damageString;
	}

	/**
	 * displays the current game info by printing out each players full inventory,
	 * attributes, and state. it also displays the current monsters state.
	 */
	private void displayGameInfo(Monster monster) {

		for (Hero player : players) {

			System.out.println(player);

		}

		System.out.println(monster);

		ConsoleIO.waitForInput();

	}

	/**
	 * this method takes in a player and outputs their name and current state and
	 * also prints out the opponent monsters state
	 * 
	 * @param player
	 *            the current turns player
	 */
	private void displayTurnInfo(Hero player, Monster monster) {
		System.out.println(
				player.getName() + ", HP: (" + player.getCurrentHP() + "), MP: (" + player.getCurrentMP() + ")");
		System.out.println("Inventory:  HealthPotions - (" + player.potionsLeft(PotionType.HEALTH)
				+ "), ManaPotions - (" + player.potionsLeft(PotionType.MANA) + ")");
		System.out.println(monster);
	}
}
