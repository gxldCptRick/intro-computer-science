package models.monsters.base;

import java.util.Hashtable;
import java.util.Random;

import enums.monster.Attribute;
import enums.monster.Bonus;
import enums.potions.PotionType;
import models.items.Armor;
import models.items.Weapon;
import models.potions.HealthPotion;
import models.potions.ManaPotion;
import models.potions.base.Potion;

/**
 * <h1>Monster Model Object</h1>
 * <p>
 * this object serves as the base for all monsters in the RPG.
 * </p>
 * 
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class Monster {

	/**
	 * this is the total mana used for each special attack;
	 */
	public final int MANA_USE;

	/**
	 * this is the maximum health points that the monster has.
	 */
	public final int MAX_HP;

	/**
	 * this is the maximum mana points that the monster has.
	 */
	public final int MAX_MP;

	/**
	 * this is the collection of attributes that a monster has.
	 */
	private Hashtable<Attribute, Integer> attributes;

	/**
	 * this is the collection of bonuses that a monster has.
	 */
	private Hashtable<Bonus, Integer> bonuses;

	/**
	 * this is the collection of health potions that will be stored on the monster
	 */
	private HealthPotion[] hPotions;

	/**
	 * this is the collection of health potions that will be stored on the monster.
	 */
	private ManaPotion[] mPotions;
	/**
	 * this is the current Health Points of the monster.
	 */
	protected int currentHP;

	/**
	 * this is the the current Mana Points of the monster.
	 */
	protected int currentMP;

	/**
	 * this is the weapon list of weapons available for the current monster
	 */
	protected Weapon[] weapons;

	/**
	 * this is the name of the monster.
	 */
	protected String name;

	/**
	 * this is the current initiative that monster has.
	 */
	private int initiative;

	/**
	 * this represent whether or not the monster is alive.
	 */
	private boolean alive;

	/**
	 * the current armor of the monster.
	 */
	private Armor armor;

	/**
	 * the current armor of the monster.
	 */

	{
		attributes = new Hashtable<>();
		bonuses = new Hashtable<>();
		name = "Monster";
		alive = true;

	}

	/**
	 * this is the base constructor for all monsters.
	 * 
	 * @param str
	 *            the strength stat for the monster being created.
	 * @param dex
	 *            the dexterity stat for the monster being created.
	 * @param intel
	 *            the intelligence stat for the monster being created.
	 * @param manaUse
	 *            the amount of mana used per each special attack.
	 */
	protected Monster(int str, int dex, int intel, int manaUse, int potionsToStore, int weaponLimit,
			int potionsSpawnedWith, String name) {

		this.name = name;

		hPotions = new HealthPotion[potionsToStore];
		mPotions = new ManaPotion[potionsToStore];

		for (int i = 0; i < potionsSpawnedWith * 2; i++) {

			if (i % 2 == 0) {
				this.addPotion(new HealthPotion());
			} else {
				this.addPotion(new ManaPotion());
			}

		}

		weapons = new Weapon[weaponLimit];

		MAX_HP = calculateBase(str, 10);
		MAX_MP = calculateBase(intel, 5);
		MANA_USE = manaUse;
		currentHP = MAX_HP;
		currentMP = MAX_MP;

		int damage = calculateBonus(str, 15, 10, 1, 1), dodge = calculateBonus(dex, 14, 10, 2, 1),
				strike = calculateBonus(dex, 15, 11, 2, 1), spellStrength = calculateBonus(intel, 15, 10, 1, 2);

		attributes.put(Attribute.STRENGTH, str);
		attributes.put(Attribute.DEXTERITY, dex);
		attributes.put(Attribute.INTELLIGENCE, intel);

		bonuses.put(Bonus.DAMAGE, damage);
		bonuses.put(Bonus.DODGE, dodge);
		bonuses.put(Bonus.STRIKE, strike);
		bonuses.put(Bonus.SPELL_STRENGTH, spellStrength);
	}

	/**
	 * this is the getter for the name of the monster.
	 * 
	 * @return returns the name of the monster.
	 */
	public String getName() {
		return name;
	}

	/**
	 * this sets the name of the monster to the new name passed in.
	 * 
	 * @param name
	 *            the new name of the monster.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * this is the getter for the monsters initiative.
	 * 
	 * @return returns the monsters current initiative.
	 */
	public int getInitiative() {
		return this.initiative;
	}

	/**
	 * this sets the monsters initiative to a new value passed in.
	 * 
	 * @param initiative
	 *            the new initiative value to be set.
	 */
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	/**
	 * this is the getter for the current Health Points.
	 * 
	 * @return returns the monsters current Health Points.
	 */
	public int getCurrentHP() {
		return currentHP;
	}

	/**
	 * this is the getter for current Mana Points.
	 * 
	 * @return returns the monsters current Mana Points.
	 */
	public int getCurrentMP() {
		return currentMP;
	}

	/**
	 * this takes in a Attribute enum key to find the appropriate value associated
	 * with it.
	 * 
	 * @param key
	 *            the key value that will find the attribute in the collection.
	 * @return returns the attribute value based on the key passed in.
	 */
	public int getAttr(Attribute key) {
		return attributes.get(key);
	}

	/**
	 * this takes in a Bonus enum key to find the appropriate value associated with
	 * it.
	 * 
	 * @param key
	 *            the key value that will find the bonus in the collection.
	 * @return returns the bonus value based on the key passed in.
	 */
	public int getBonus(Bonus key) {
		return bonuses.get(key);
	}

	/**
	 * this method takes in an int damage and subtracts it from current HP.
	 * 
	 * @param damage
	 *            the amount of damage that the monster receives.
	 */
	public void recieveDamage(int damage) {
		this.currentHP -= damage;

		alive = currentHP > 0;
	}

	/**
	 * this is a method to find if the monster is still alive.
	 * 
	 * @return returns if monster is alive.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * adds a potion to the Monsters inventory and uses it if the inventory is full
	 * and returns if the potion was added.
	 * 
	 * @param potion
	 *            potion to be added to the Monsters inventory.
	 * @return returns whether or not the potion has been added to your inventory.
	 */
	public boolean addPotion(Potion potion) {
		boolean added = false;
		if (potion != null) {
			if (potion.getClass() == HealthPotion.class) {

				for (int i = 0; i < hPotions.length && !added; i++) {

					if (hPotions[i] == null || hPotions[i].isUsed()) {
						hPotions[i] = (HealthPotion) potion;
						added = true;
					}

				}

				if (!added) {
					this.usePotion(potion);
				}

			} else if (potion.getClass() == ManaPotion.class) {

				for (int i = 0; i < mPotions.length && !added; i++) {

					if (mPotions[i] == null || mPotions[i].isUsed()) {

						mPotions[i] = (ManaPotion) potion;
						added = true;

					}

				}

				if (!added) {
					this.usePotion(potion);
				}

			}
		}

		return added;

	}

	/**
	 * allows the Monster to use a potion of the specified type and returns the
	 * amount restored.
	 * 
	 * @param type
	 *            the type of potion that wants to be used.
	 * @return returns the amount restored of the potion used.
	 */
	public int usePotion(PotionType type) {

		boolean used = false;

		int potionValue = -1;

		if (type == PotionType.HEALTH) {

			for (int i = 0; i < hPotions.length && !used; i++) {

				if (hPotions[i] != null) {

					potionValue = hPotions[i].getEffectValue();

					usePotion(hPotions[i]);

					hPotions[i] = null;

					used = true;

				}

			}

		} else if (type == PotionType.MANA) {
			for (int i = 0; i < mPotions.length && !used; i++) {

				if (mPotions[i] != null) {

					potionValue = mPotions[i].getEffectValue();

					usePotion(mPotions[i]);

					mPotions[i] = null;

					used = true;

				}

			}
		}

		return potionValue;
	}

	/**
	 * takes in a type to find a corresponding potion array and returns how many
	 * potions are left in that array.
	 * 
	 * @param type
	 *            the type of potion array you want to check
	 * @return returns an int representation of how many non-used potions left in
	 *         the array.
	 */
	public int potionsLeft(PotionType type) {
		int potsLeft = 0;

		if (type == PotionType.HEALTH) {
			for (Potion pot : hPotions) {
				potsLeft = pot != null ? pot.isUsed() ? potsLeft : potsLeft + 1 : potsLeft;
			}
		} else if (type == PotionType.MANA) {
			for (Potion pot : mPotions) {
				potsLeft = pot != null ? pot.isUsed() ? potsLeft : potsLeft + 1 : potsLeft;
			}
		}

		return potsLeft;
	}

	/**
	 * takes in a potion object and uses it on the appropriate stat.
	 * 
	 * @param potion
	 *            potion to be used.
	 */
	private void usePotion(Potion potion) {

		if (potion.getClass() == HealthPotion.class) {

			this.currentHP += potion.usePotion();

			if (this.currentHP > this.MAX_HP) {

				this.currentHP = this.MAX_HP;

			}

		} else if (potion.getClass() == ManaPotion.class) {

			this.currentMP += potion.usePotion();

			if (this.currentMP > this.MAX_MP) {

				this.currentMP = this.MAX_MP;

			}

		}
	}

	/**
	 * this method gets the currently equipped weapon.
	 * 
	 * @return returns the a reference to the currently equipped weapon.
	 */
	public Weapon getCurrentlyEquppiedWeapon() {
		Weapon currentlyEquppiedWeapon = null;

		if (weapons.length > 0) {

			currentlyEquppiedWeapon = weapons[0];

		}

		return currentlyEquppiedWeapon;
	}

	/**
	 * this method retrieves a the weapon at the specified index.
	 * 
	 * @param index
	 *            the index of the weapon you are looking for.
	 * @return returns the weapon at the specified index.
	 */
	public Weapon getSpecificWeapon(int index) {
		if (index >= weapons.length)
			throw new IllegalArgumentException("There is no such weapon that exists");
		return weapons[index];
	}

	/**
	 * this method take in an index for a weapon about to be equipped.
	 * 
	 * @param weaponIndex
	 *            the index of the weapon to be equipped.
	 */
	public void equipWeapon(int weaponIndex) {
		Weapon temp = weapons[0];
		if (weaponIndex >= weapons.length) {
			throw new ArrayIndexOutOfBoundsException("You are trying to find something that doesnt exist.");
		}

		weapons[0] = weapons[weaponIndex];

		weapons[weaponIndex] = temp;
	}

	/**
	 * this method stores a weapon in the monsters inventory at the first empty
	 * slot.
	 * 
	 * @param newWeapon
	 *            new weapon to be stored.
	 */
	public void storeWeapon(Weapon newWeapon) {
		boolean stored = false;
		for (int i = 0; i < weapons.length && !stored; i++) {

			if (weapons[i] == null) {
				weapons[i] = newWeapon;
				stored = true;
			}
		}

	}

	/**
	 * this method stores a weapon in the monsters inventory at the specified index.
	 * 
	 * @param newWeapon
	 *            the weapon to be inserted.
	 * @param index
	 *            the specified index in which to insert the weapon.
	 */
	public void storeWeapon(Weapon newWeapon, int index) {

		if (index >= weapons.length) {

			throw new ArrayIndexOutOfBoundsException("you are trying to add the weapon to place that doesn't exist.");

		}

		weapons[index] = newWeapon;
	}

	/**
	 * this method creates a string array of weapon names and returns it to the
	 * caller. the array is an exact copy of the array of weapons matched index to
	 * index.
	 * 
	 * @return returns an array of the names of each weapon in the monsters
	 *         inventory.
	 */
	public String[] getArrayOfWeaponNames() {
		String[] listOfNames = new String[weapons.length];

		int index = 0;

		for (Weapon weapon : weapons) {

			listOfNames[index++] = weapon != null ? weapon.toString() : null;

		}

		return listOfNames;
	}

	/**
	 * this method equips a new armor for the armor property.
	 * 
	 * @param newArmor
	 *            the new armor to be equipped.
	 */
	public void equipArmor(Armor newArmor) {

		this.armor = newArmor;

	}

	/**
	 * this method is the getter for the armor property
	 * 
	 * @return returns the current armor
	 */
	public Armor getArmor() {
		return this.armor;
	}

	/**
	 * this represents the normal attack of a monster.
	 * 
	 * @return returns the damage value of an attack.
	 */
	public int[] attackNormal() {
		int[] rolls = new int[3];
		if (this.name.equals("ALL MIGHT")) {

			rolls[0] = 100;
			rolls[1] = 3;

			Random rnJesus = new Random();
			int smash = rnJesus.nextInt(100) + 1;
			if (smash > 90) {

				rolls[2] = 100;

			} else if (smash > 70) {

				rolls[2] = 50;

			} else if (smash > 50) {

				rolls[2] = 25;

			} else if (smash > 30) {

				rolls[2] = 0;

			} else if (smash > 10) {

				rolls[2] = -25;

			} else {

				rolls[2] = -50;

			}

		} else {

			Weapon currentWeapon = this.getCurrentlyEquppiedWeapon();

			rolls[0] = currentWeapon.TYPE.DIE_SIZE;

			rolls[1] = currentWeapon.ATTR.ADDITIONAL_DICE + currentWeapon.TYPE.AMOUNT_OF_DICE;

			rolls[2] = currentWeapon.ATTR.BONUS + getBonus(Bonus.DAMAGE);

		}

		return rolls;
	}

	/**
	 * this represents the special attack of a monster.
	 * 
	 * @return returns the damage value of a special attack.
	 */
	public int[] attackSpecial() {
		int[] rolls = new int[3];

		if (this.name.equals("ALL MIGHT")) {

			rolls[0] = 100;

			rolls[1] = 3 * 2;

			Random rnJesus = new Random();

			int smashPlusUltra = rnJesus.nextInt(100) + 1;

			if (smashPlusUltra > 90) {

				rolls[2] = 100 * 2;

			} else if (smashPlusUltra > 70) {

				rolls[2] = 50 * 2;

			} else if (smashPlusUltra > 50) {

				rolls[2] = 25 * 2;

			} else if (smashPlusUltra > 30) {

				rolls[2] = 0 * 2;

			} else if (smashPlusUltra > 10) {

				rolls[2] = -25 * 2;

			} else {

				rolls[2] = -50 * 2;

			}

			this.currentMP -= this.currentMP;

		} else {

			if (currentMP / MANA_USE > 0) {

				Weapon currentWeapon = this.getCurrentlyEquppiedWeapon();

				if (currentWeapon != null) {

					rolls[0] = currentWeapon.TYPE.DIE_SIZE;

					rolls[1] = currentWeapon.ATTR.ADDITIONAL_DICE + 1 + currentWeapon.TYPE.AMOUNT_OF_DICE;

					rolls[2] = currentWeapon.ATTR.BONUS + getBonus(Bonus.SPELL_STRENGTH);

					currentMP -= MANA_USE;
				}
			}
		}

		return rolls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();

		toString.append(this.getName() + ", HP: (" + this.getCurrentHP() + "), MP: (" + this.getCurrentMP() + ")");

		if (this.getCurrentHP() < (this.MAX_HP / 4)) {
			toString.append("\nThe " + this.getName() + " looks mortaly wounded");
		}

		return toString.toString();
	}

	/**
	 * calculates a base value using an attribute and multiply it by the given
	 * value.
	 * 
	 * @param attr
	 *            attribute value passed in.
	 * @param multi
	 *            multiplier value passed in.
	 * @return returns the base value calculated.
	 */
	private int calculateBase(int attr, int multi) {

		return attr * multi;
	}

	/**
	 * calculates a bonus value based on an attribute and certain restraints on that
	 * value.
	 * 
	 * @param attr
	 *            attribute value passed in.
	 * @param basePoint
	 *            base value passed in to determine positive gain.
	 * @param negativeBase
	 *            base value passed in to determine the negative effects.
	 * @param frequency
	 *            value passed in to determine the frequency of the gains.
	 * @param gains
	 *            value passed in to determine the gains for the bonuses.
	 * @return returns the calculated bonus.
	 */
	private int calculateBonus(int attr, int basePoint, int negativeBase, int frequency, int gains) {
		int bonus = 0;

		if (frequency == 0)
			throw new ArithmeticException("You can't have a negative value for frequency");
		if (attr >= basePoint) {
			bonus = ((attr - basePoint) / frequency) * gains;
		} else if (attr <= negativeBase) {
			bonus = ((attr - negativeBase) / frequency) * gains;
		}
		return bonus;
	}

}
