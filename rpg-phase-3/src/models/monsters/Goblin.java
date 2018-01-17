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
 * <h1>Goblin Model Object</h1>
 * <p>
 * extends the Monster object and implements the Droppable interface.
 * </p>
 * <p>
 * this represents a goblin that has both a low normal attack and
 * </p>
 * <p>
 * a very low Special attack. it is the weakest of all monsters.
 * </p>
 * 
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class Goblin extends Monster implements Droppable {

	{
		this.setName("Goblin");

		this.equipArmor(new Armor(ArmorType.LEATHER));

		this.storeWeapon(new Weapon(WeaponType.DAGGER));

	}

	/**
	 * Constructor for Goblin.
	 * 
	 * @param str
	 *            strength of Goblin
	 * @param dex
	 *            dexterity of Goblin
	 * @param intel
	 *            intelligence of Goblin
	 */
	public Goblin(int str, int dex, int intel) {
		super(str, dex, intel, 5, 2, 1, 2,"Goblin");

	}

	/**
	 * Constructor for Goblin creates a basic goblin with a strength of 15, a
	 * dexterity of 10, and an intelligence of 5.
	 */
	public Goblin() {
		super(15, 10, 5, 5, 2, 1, 2,"Goblin");
	}

	/**
	 * this is the Goblin drops up to 3 potions that are randomly
	 * distributed between health and mana.
	 * 
	 * @see models.monsters.interfaces.Droppable#generatePotionsDrops()
	 * 
	 */
	@Override
	public Potion[] generatePotionsDrop() {
		Potion[] potionsDropped = null;

		int amountOfPotions = rnJesus.nextInt(3), potionChance;

		potionsDropped = new Potion[amountOfPotions];

		for (int i = 0; i < potionsDropped.length; i++) {

			potionChance = rnJesus.nextInt(2);

			switch (potionChance) {

			case 0:

				potionsDropped[i] = new ManaPotion();

				break;

			case 1:

				potionsDropped[i] = new HealthPotion();

				break;

			default:

				throw new IllegalArgumentException("There is no Other Possible Potion");

			}

		}

		return potionsDropped;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.Droppable#generateArmorDrop()
	 */
	@Override
	public Armor generateArmorDrop() {
		Armor armorDropped = null;
		int chance = rnJesus.nextInt(100);
		if (chance > 70) {
			armorDropped = this.getArmor();
		}
		return armorDropped;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see models.monsters.interfaces.Droppable#generateWeaponDrop()
	 */
	@Override
	public Weapon generateWeaponDrop() {
		Weapon weaponDropped = null;
		int chance = rnJesus.nextInt(100);
		if (chance > 80) {
			weaponDropped = this.getCurrentlyEquppiedWeapon();
		}
		return weaponDropped;
	}

}
