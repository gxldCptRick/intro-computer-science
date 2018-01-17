package models.monsters;


import enums.items.WeaponType;
import enums.monster.Attribute;
import enums.monster.Bonus;
import enums.potions.PotionType;
import models.items.Weapon;
import models.monsters.base.Monster;

/**<h1> Hero Model Object</h1>
 * <p> extends Monster object</p>
 * <p> represents a hero that has a basic attack and special attack that is more or less alright.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version 1.0
 */
public final class Hero extends Monster{

	
	/** Constructs ALL MIGHT - The Number ONE SUPER HERO. 
	 */
	public Hero() {
		
		super(50, 50, 3, 0, 100, 0 , 100,"ALL MIGHT");
		this.setName("ALL MIGHT");
		
		
	}

	/** Constructor for the hero using the standard name Deku.
	 * @param str the strength stat for the hero.
	 * @param dex the dexterity stat for the hero.
	 * @param intel the intelligence stat for the hero.
	 */
	public Hero(int str, int dex, int intel) {

		super(str, dex, intel, 10, 5 , 3, 2, "Deku");
		this.storeWeapon(new Weapon(WeaponType.DAGGER));
		
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
	
	public Hero(String name, int str, int dex, int intel, String[] weaponNames, String armor, int[] potionAmounts) {
		
		this(str, dex, intel);
		
		this.setName(name);
		
		processWeaponNames(weaponNames);
		
		processArmor(armor);
		
		regeneratePotions(potionAmounts);
	}
	
	public void restoreHero() {
		
		this.currentHP = this.MAX_HP;
		
		this.currentMP = this.MAX_MP;
	
	}
	
	private void processWeaponNames(String[] weapons) {
	

	}
	
	private void processArmor(String armor) {
		
	}
	
	private void regeneratePotions(int[] amountOfPots) {

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
		mainString.append(")\nInventory: Health Potions - (" + potionsLeft(PotionType.HEALTH) + "), Mana Potions - (" + potionsLeft(PotionType.MANA) + ")");

		return mainString.toString();
	}
 
}
