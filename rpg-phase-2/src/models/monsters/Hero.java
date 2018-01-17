package models.monsters;


import enums.monster.Attribute;
import enums.monster.Bonus;
import enums.potions.Type;
import models.monsters.base.Monster;
import models.potions.HealthPotion;
import models.potions.ManaPotion;
import models.potions.base.Potion;

/**<h1> Hero Model Object</h1>
 * <p> extends Monster object</p>
 * <p> represents a hero that has a basic attack and special attack that is more or less alright.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version 1.0
 */
public final class Hero extends Monster{

	/** This is the list of health potions in the hero's inventory.
	 */
	private HealthPotion[] hPotions;
	
	/** This is the list of mana potions in the hero's inventory. 
	 */
	private ManaPotion[] mPotions;

	{

		hPotions = new HealthPotion[5];
		mPotions = new ManaPotion[5];
		this.setName("Deku");
	}

	/** Constructs ALL MIGHT - The Number ONE SUPER HERO. 
	 */
	public Hero() {
		
		super(50, 50, 3, 0);
		this.setName("ALL MIGHT");
		
		
	}

	/** Constructor for the hero using the standard name Deku.
	 * @param str the strength stat for the hero.
	 * @param dex the dexterity stat for the hero.
	 * @param intel the intelligence stat for the hero.
	 */
	public Hero(int str, int dex, int intel) {

		super(str, dex, intel, 10);

	}

	/** Constructor for hero object.
	 * @param name the name of the hero.
	 * @param str the strength stat for the hero.
	 * @param dex the dexterity stat for the hero.
	 * @param intel the intelligence stat for the hero.
	 */
	public Hero(String name, int str, int dex, int intel) {

		this(str, dex, intel);
		this.setName(name);

	}
	
	/** adds a potion to the heros inventory and uses it if the inventory is full and returns if the potion was added.
	 * @param potion potion to be added to the heros inventory.
	 * @return returns whether or not the potion has been added to your inventory.
	 */
	public boolean addPotion(Potion potion) {
		boolean added = false;

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

		return added;

	}

	/** allows the hero to use a potion of the specified type and returns the amount restored.
	 * @param type the type of potion that wants to be used.
	 * @return returns the amount restored of the potion used.
	 */
	public int usePotion(Type type) {

		boolean used = false;

		int potionValue = -1;

		if (type == Type.HEALTH) {

			for (int i = 0; i < hPotions.length && !used; i++) {

				if (hPotions[i] != null) {

					potionValue = hPotions[i].getEffectValue();

					usePotion(hPotions[i]);

					hPotions[i] = null;
					
					used = true;

				}

			}

		} else if (type == Type.MANA) {
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

	/** takes in a type to find a corresponding potion array and returns how many potions are left in that array.
	 * @param type the type of potion array you want to check
	 * @return returns an int representation of how many non-used potions left in the array.
	 */
	public int potionsLeft(Type type) {
		int potsLeft = 0;
		
		if(type == Type.HEALTH) {
			for(Potion pot : hPotions) {
				potsLeft = pot != null? pot.isUsed()? potsLeft : potsLeft + 1 :potsLeft; 
			}
		}
		else if(type == Type.MANA) {
			for(Potion pot : mPotions) {
				potsLeft = pot != null? pot.isUsed()? potsLeft : potsLeft + 1 :potsLeft; 
			}
		}
		
		return potsLeft;
	}

	/** takes in a potion object and uses it on the appropriate stat.
	 * @param potion potion to be used.
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
	
	
	/* (non-Javadoc)
	 * @see models.monsters.base.Monster#attackNormal()
	 */
	@Override
	public int attackNormal() {

		int damage = 10 + getBonus(Bonus.DAMAGE);
		
		if(damage <= 0) {
			damage = 10;
		}
		return damage;
	}
	
	/* (non-Javadoc)
	 * @see models.monsters.base.Monster#attackSpecial()
	 */
	@Override
	public int attackSpecial() {

		int damage = 0;
		
		if (currentMP / MANA_USE > 0) {
		
			damage = 15 + getBonus(Bonus.SPELL_STRENGTH);
			
			if(damage <= 0) {
				damage = 15;
			}
			
			currentMP -= MANA_USE;
		
		}
		
		return damage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override

	public String toString() {
		StringBuilder mainString = new StringBuilder();
		if (this.getName() != null) {
			mainString.append(this.getName() + "");
		} else {
			mainString.append("HERO A");
		}
		mainString.append(" HP: " + this.getCurrentHP() + " MP: " + this.getCurrentMP() +"\n");
		mainString.append("Attributes: Strength - (" + getAttr(Attribute.STRENGTH) + "), Dexterity - (" + getAttr(Attribute.DEXTERITY) + "), Intelligence - (" + getAttr(Attribute.INTELLIGENCE) + ")\nBonuses: Damage - ("+getBonus(Bonus.DAMAGE) +"), Strike - (" + getBonus(Bonus.STRIKE) + "), Dodge - (" + getBonus(Bonus.DODGE) + "), Spell Strength - (" + getBonus(Bonus.SPELL_STRENGTH));
		mainString.append(")\nInventory: Health Potions - (" + potionsLeft(Type.HEALTH) + "), Mana Potions - (" + potionsLeft(Type.MANA) + ")");

		return mainString.toString();
	}
 
}
