package models;

import java.util.Hashtable;

import enums.Attribute;
import enums.Bonus;

public class Hero {

	public final int BASE_HP;
	public final int BASE_MP;

	private Hashtable<Attribute, Integer> attributes;
	private Hashtable<Bonus, Integer> bonuses;
	private int currentHP;
	private int currentMP;
	private String name;

	{
		attributes = new Hashtable<Attribute, Integer>();
		bonuses = new Hashtable<Bonus, Integer>();
	}

	public Hero() {
		BASE_HP = 1000;
		BASE_MP = 1000;
		name = "ALL MIGHT";
		int str = 30, dex = 30, intel = 5;

		currentHP = BASE_HP;
		currentMP = BASE_MP;

		attributes.put(Attribute.STRENGTH, str);
		attributes.put(Attribute.DEXTERITY, dex);
		attributes.put(Attribute.INTELLIGENCE, intel);

		int damage = calculateBonus(str, 15, 10, 1, 1), strike = calculateBonus(dex, 14, 10, 2, 1),
				dodge = calculateBonus(dex, 15, 11, 2, 1), spellStrength = calculateBonus(intel, 15, 10, 1, 2);

		bonuses.put(Bonus.DAMAGE, damage);
		bonuses.put(Bonus.STRIKE, strike);
		bonuses.put(Bonus.DODGE, dodge);
		bonuses.put(Bonus.SPELL_STRENGTH, spellStrength);

	}

	public Hero(int str, int dex, int intel) {

		BASE_HP = calculateBase(str, 10);
		BASE_MP = calculateBase(intel, 5);

		currentHP = BASE_HP;
		currentMP = BASE_MP;

		attributes.put(Attribute.STRENGTH, str);
		attributes.put(Attribute.DEXTERITY, dex);
		attributes.put(Attribute.INTELLIGENCE, intel);

		int damage = calculateBonus(str, 15, 10, 1, 1), strike = calculateBonus(dex, 14, 10, 2, 1),
				dodge = calculateBonus(dex, 15, 11, 2, 1), spellStrength = calculateBonus(intel, 15, 10, 1, 2);

		bonuses.put(Bonus.DAMAGE, damage);
		bonuses.put(Bonus.STRIKE, strike);
		bonuses.put(Bonus.DODGE, dodge);
		bonuses.put(Bonus.SPELL_STRENGTH, spellStrength);

	}

	public Hero(String name, int str, int dex, int intel) {

		this(str, dex, intel);

		this.name = name;

	}

	public int getCurrentHP() {
		return currentHP;
	}

	public int getCurrentMP() {
		return currentMP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttr(Attribute key) {
		return attributes.get(key);
	}

	public int getBonus(Bonus key) {
		return bonuses.get(key);
	}

	private int calculateBase(int attr, int multi) {
		return attr * multi;
	}

	private int calculateBonus(int attr, int basePoint, int negativeBase, int frequency, int gains) {
		int bonus = 0;

		if (basePoint == 0)
			throw new ArithmeticException("You can't have a negative value for frequency");
		if (attr >= basePoint) {
			bonus = ((attr - basePoint) / frequency) * gains;
		} else if (attr <= negativeBase) {
			bonus = ((attr - negativeBase) / frequency) * gains;
		}
		return bonus;
	}

	@Override

	public String toString() {
		StringBuilder mainString = new StringBuilder(" Strength: " + getAttr(Attribute.STRENGTH) + ", Dexterity: "
				+ getAttr(Attribute.DEXTERITY) + ", Intelligence: " + getAttr(Attribute.INTELLIGENCE) + "\n"
				+ "Bonuses, Damage: " + getBonus(Bonus.DAMAGE) + ", Strike: " + getBonus(Bonus.STRIKE) + ", Dodge: "
				+ getBonus(Bonus.DODGE) + ", Spell Strength: " + getBonus(Bonus.SPELL_STRENGTH));
		if (name != null) {
			mainString.insert(0, name); 
		}
		else {
			mainString.insert(0, "HERO A");
		} 
		
		return mainString.toString();
	}

}
