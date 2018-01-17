package models.monsters.base;

import java.util.Hashtable;

import enums.monster.Attribute;
import enums.monster.Bonus;

/** <h1> Monster Model Object</h1>
 * <p> this object serves as the base for all monsters in the RPG.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public abstract class Monster {

	/** this is the total mana used for each special attack; 
	 */
	public final int MANA_USE;
	
	/** this is the maximum health points that the monster has. 
	 */
	public final int MAX_HP;
	
	/** this is the maximum mana points that the monster has.
	 */
	public final int MAX_MP;
	
	/** this is the collection of attributes that a monster has. 
	 */
	private Hashtable<Attribute, Integer> attributes;
	
	/** this is the collection of bonuses that a monster has. 
	 */
	private Hashtable<Bonus, Integer> bonuses;
	
	
	/** this is the current Health Points of the monster. 
	 */
	protected int currentHP;
	
	/** this is the the current Mana Points of the monster.
	 */
	protected int currentMP;
	
	/** this is the name of the monster.
	 */
	private String name;

	/** this is the current initiative that monster has.
	 */
	private int initiative;
	
	/** this represent whether or not the monster is alive.
	 */
	private boolean alive;
	
	{
		
		attributes = new Hashtable<>();
		bonuses = new Hashtable<>();
		name = "gary";
		alive = true;
		
	}
	
	/** this is the base constructor for all monsters.
	 * @param str the strength stat for the monster being created.
	 * @param dex the dexterity stat for the monster being created.
	 * @param intel the intelligence stat for the monster being created.
	 * @param manaUse the amount of mana used per each special attack.
	 */
	protected Monster(int str, int dex, int intel, int manaUse) {
		MAX_HP = calculateBase(str, 10);
		MAX_MP = calculateBase(intel, 5);
		MANA_USE = manaUse;
		currentHP = MAX_HP;
		currentMP = MAX_MP;
		
		int damage = calculateBonus(str,15,10, 1,1), dodge = calculateBonus(dex, 14, 10, 2, 1), 
			strike = calculateBonus(dex, 15, 11, 2, 1), spellStrength = calculateBonus(intel, 15, 10, 1, 2);
		
		attributes.put(Attribute.STRENGTH, str);
		attributes.put(Attribute.DEXTERITY, dex);
		attributes.put(Attribute.INTELLIGENCE, intel);
		
		bonuses.put(Bonus.DAMAGE, damage);
		bonuses.put(Bonus.DODGE, dodge);
		bonuses.put(Bonus.STRIKE, strike);
		bonuses.put(Bonus.SPELL_STRENGTH, spellStrength);
	}	
	
	/** this is the getter for the name of the monster.
	 * @return returns the name of the monster.
	 */
	public String getName() {
		return name;
	}

	/** this sets the name of the monster to the new name passed in.
	 * @param name the new name of the monster.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** this is the getter for the monsters initiative.
	 * @return returns the monsters current initiative.
	 */
	public int getInitiative() {
		return this.initiative;
	}
	
	/** this sets the monsters initiative to a new value passed in.
	 * @param initiative the new initiative value to be set.
	 */
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}
	
	/** this is the getter for the current Health Points.
	 * @return returns the monsters current Health Points.
	 */
	public int getCurrentHP() {
		return currentHP;
	}
	
	/** this is the getter for current Mana Points.
	 * @return returns the monsters current Mana Points.
	 */
	public int getCurrentMP() {
		return currentMP;
	}
	
	/** this takes in a Attribute enum key to find the appropriate value associated with it.
	 * @param key the key value that will find the attribute in the collection.
	 * @return returns the attribute value based on the key passed in.
	 */
	public int getAttr(Attribute key) {
		return attributes.get(key);
	}
	
	/** this takes in a Bonus enum key to find the appropriate value associated with it.
	 * @param key the key value that will find the bonus in the collection.
	 * @return returns the bonus value based on the key passed in.
	 */
	public int getBonus(Bonus key) {
		return bonuses.get(key);
	}
	
	/** this method takes in an int damage and subtracts it from current HP.
	 * @param damage the amount of damage that the monster receives.
	 */
	public void recieveDamage(int damage) {
		this.currentHP -= damage;
		
		alive = currentHP > 0;
	}
	
	/** this is a method to find if the monster is still alive.
	 * @return returns if monster is alive.
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/** this represents the normal attack of a monster.
	 * @return returns the damage value of an attack.
	 */
	public abstract int attackNormal();
	
	/** this represents the special attack of a monster.
	 * @return returns the damage value of a special attack.
	 */
	public abstract int attackSpecial();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		
		toString.append(this.getName() + ", HP: (" + this.getCurrentHP() + "), MP: (" + this.getCurrentMP()+")");
		
		if(this.getCurrentHP() < (this.MAX_HP/4)) {
			toString.append("\nThe " + this.getName() +" looks mortaly wounded");
		}
		
		
		return toString.toString();
	}
	
	/** calculates a base value using an attribute and multiply it by the given value.
	 * @param attr attribute value passed in.
	 * @param multi multiplier value passed in.
	 * @return returns the base value calculated.
	 */
	private int calculateBase(int attr,int multi) {
		
		return attr * multi;
	}
	
	/** calculates a bonus value based on an attribute and certain restraints on that value.
	 * @param attr attribute value passed in.
	 * @param basePoint base value passed in to determine positive gain.
	 * @param negativeBase base value passed in to determine the negative effects.
	 * @param frequency value passed in to determine the frequency of the gains.
	 * @param gains value passed in to determine the gains for the bonuses.
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
