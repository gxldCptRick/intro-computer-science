package models.monsters;

import enums.items.ArmorType;
import enums.items.WeaponType;
import models.items.Armor;
import models.items.Weapon;
import models.monsters.base.Monster;
import models.monsters.interfaces.Droppable;
import models.potions.HealthPotion;
import models.potions.ManaPotion;
import models.potions.base.Potion;

/**
 * <h1>BugBear Model Object</h1>
 * <p>
 * extends Monster object and Implements the Droppable interface.
 * </p>
 * <p>
 * it is a Bug Bear with a decently high physical attack but a very low special
 * attack.
 * </p>
 * 
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class BugBear extends Monster implements Droppable {

	{
		this.setName("BugBear");
		int thingy = rnJesus.nextInt(aAttrs.length);
		this.equipArmor(new Armor(ArmorType.CHAINMAIL, aAttrs[thingy]));

		thingy = rnJesus.nextInt(wAttrs.length);
		this.storeWeapon(new Weapon(WeaponType.SHORT_SWORD, wAttrs[thingy]));

		thingy = rnJesus.nextInt(wAttrs.length);
		this.storeWeapon(new Weapon(WeaponType.FLAIL, wAttrs[thingy]));

	}

	/**
	 * Constructor for BugBear Object.
	 * 
	 * @param str
	 *            the strength of the BugBear.
	 * @param dex
	 *            the dexterity of the BugBear.
	 * @param intel
	 *            the intelligence of the BugBear.
	 */
	public BugBear(int str, int dex, int intel) {
		super(str, dex, intel, 10, 5, 2, 5,"BugBear");

	}

	/**
	 * Constructor for BugBear this creates a BugBear with the most 20 strength, 15
	 * dexterity, and 10 intelligence.
	 */
	public BugBear() {
		super(20, 15, 10, 10, 5, 2, 5,"BugBear");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.Droppable#generatePotionsDrops()
	 */
	@Override
	public Potion[] generatePotionsDrop() {
		Potion[] potionsDrop = null;
		int potions = rnJesus.nextInt(5), chance;
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
				throw new IllegalArgumentException("There is no applicable potion.");
			}
		}

		return potionsDrop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.Droppable#generateArmorDrop()
	 */
	@Override
	public Armor generateArmorDrop() {
		Armor newArmor = null;

		int chance = rnJesus.nextInt(100);

		if (chance < 50) {
			newArmor = this.getArmor();
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
		int chance = rnJesus.nextInt(100);

		if (chance < 10) {
			newWeapon = this.getSpecificWeapon(0);
		} else if (chance > 89) {
			newWeapon = this.getSpecificWeapon(1);
		}

		return newWeapon;
	}
}
