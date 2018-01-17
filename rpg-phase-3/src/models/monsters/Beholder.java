package models.monsters;

import enums.Spells.Spell;
import enums.Spells.SpellType;
import enums.items.ArmorAttribute;
import enums.items.ArmorType;
import enums.items.WeaponAttribute;
import enums.items.WeaponType;
import models.items.Armor;
import models.items.Weapon;
import models.monsters.base.Monster;
import models.monsters.interfaces.Droppable;
import models.monsters.interfaces.MagicUser;
import models.potions.HealthPotion;
import models.potions.ManaPotion;
import models.potions.base.Potion;

/**
 * <h1>Beholder Model Object</h1>
 * <p>
 * Extends the Monster Object and Implements the Droppable interface.
 * </p>
 * <p>
 * it is a Beholder Monster with a low normal attack but,
 * </p>
 * <p>
 * a very high Special attack.
 * </p>
 * 
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public final class Beholder extends Monster implements Droppable, MagicUser {

	/**
	 * Constructor for the Beholder Model Object.
	 * 
	 * @param str
	 *            the strength of the beholder.
	 * @param dex
	 *            the dexterity of the beholder.
	 * @param intel
	 *            the intelligence of the beholder.
	 */
	public Beholder(int str, int dex, int intel) {
		super(str, dex, intel, 50, 6, 0, 6,"Beholder");

	}

	private void heal(int healingAmount) {
		int currentHealth = this.currentHP;
		currentHealth += healingAmount;

		if (currentHealth > this.MAX_HP) {
			this.currentHP = this.MAX_HP;
		} else {
			this.currentHP = currentHealth;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.Droppable#generatePotionsDrops()
	 */
	@Override
	public Potion[] generatePotionsDrop() {
		Potion[] potionsDrop = null;
		int potions = rnJesus.nextInt(7), chance;

		potionsDrop = new Potion[potions];

		for (int i = 0; i < potionsDrop.length; i++) {
			chance = rnJesus.nextInt(2);

			switch (chance) {
			case 0:
				potionsDrop[i] = new HealthPotion();
				break;
			case 1:
				potionsDrop[i] = new ManaPotion();
				break;
			default:
				throw new IllegalArgumentException("No such potion exists...");
			}
		}

		return potionsDrop;
	}

	@Override
	public int[] attackNormal() {
		return new int[] { 20, 5, 10 };
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.Droppable#generateArmorDrop()
	 */
	@Override
	public Armor generateArmorDrop() {
		Armor newArmor = null;

		ArmorType[] types = ArmorType.values();
		int armorType = rnJesus.nextInt(types.length);
		ArmorType type = types[armorType];

		int armorAttr = rnJesus.nextInt(aAttrs.length - 1) + 1;
		ArmorAttribute attr = aAttrs[armorAttr];

		int chance = rnJesus.nextInt(100);

		if (chance > 30) {
			newArmor = new Armor(type, attr);
		}

		return newArmor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.Droppable#generateWeaponDrop()
	 */
	@Override
	public Weapon generateWeaponDrop() {
		Weapon newWeapon = null;

		WeaponType[] types = WeaponType.values();
		int weaponType = rnJesus.nextInt(types.length);
		WeaponType type = types[weaponType];

		int weaponAttr = rnJesus.nextInt(wAttrs.length - 1) + 1;
		WeaponAttribute attr = wAttrs[weaponAttr];

		int chance = rnJesus.nextInt(100);

		if (chance > 35) {
			newWeapon = new Weapon(type, attr);
		}

		return newWeapon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.MagicUser#castLevelOneSpell()
	 */
	@Override
	public int castLevelOneSpell() {
		int damage = 0;
		int getSpell = rnJesus.nextInt(spells.length);
		Spell spellGotten = spells[getSpell];
		
		
		
		if (spellGotten.TYPE == SpellType.DAMAGE) {
			
			damage = spellGotten.EFFECT_VALUE;
			
		} else {
			damage = 0;
			this.heal(spellGotten.EFFECT_VALUE);
		}
		currentMP -= this.MANA_USE;
		return damage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.MagicUser#castLevelTwoSpell()
	 */
	@Override
	public int castLevelTwoSpell() {
		int damage = 0;
		
		
		int getSpell = rnJesus.nextInt(spells.length);
		Spell spellGotten = spells[getSpell];


		if (spellGotten.TYPE == SpellType.DAMAGE) {
			damage = spellGotten.EFFECT_VALUE;
			
		} else {
			damage = 0;
			this.heal(spellGotten.EFFECT_VALUE * 2);
		}
		currentMP -= this.MANA_USE * 4;
		


		return damage * 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.MagicUser#castLevelThreeSpell()
	 */
	@Override
	public int castLevelThreeSpell() {
		int damage = 0;
		int getSpell = rnJesus.nextInt(spells.length);
		Spell spellGotten = spells[getSpell];
		
	


		if (spellGotten.TYPE == SpellType.DAMAGE) {
			damage = spellGotten.EFFECT_VALUE;
			

		} else {
			damage = 0;
			this.heal(spellGotten.EFFECT_VALUE * 3);
		}
		currentMP -= this.MANA_USE * 6;

		
		
		return damage * 3;
	}
}
