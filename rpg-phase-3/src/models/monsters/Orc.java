package models.monsters;

import enums.items.ArmorType;
import enums.items.WeaponAttribute;
import enums.items.WeaponType;
import enums.monster.Attribute;
import models.items.Armor;
import models.items.Weapon;
import models.monsters.base.Monster;
import models.monsters.interfaces.Droppable;
import models.potions.HealthPotion;
import models.potions.ManaPotion;
import models.potions.base.Potion;

public class Orc extends Monster implements Droppable {

	
	
	public Orc(int str, int dex, int intel) {
		super(str, dex, intel, 20, 6, 1, 6, "Orc");
		
		int randomAttr = rnJesus.nextInt(wAttrs.length - 1) + 1;
		WeaponAttribute newAttr = wAttrs[randomAttr];
		this.storeWeapon(new Weapon(WeaponType.GREATAXE, newAttr));
	}

	@Override
	public Potion[] generatePotionsDrop() {
		Potion[] potionsDrop = null;
		int amountOfPotions = rnJesus.nextInt(this.getAttr(Attribute.STRENGTH)) + 1, chance = rnJesus.nextInt(100) + 1;

		if (chance > 70) {
			potionsDrop = new Potion[amountOfPotions];
		} else if (chance > 50) {
			potionsDrop = new Potion[amountOfPotions / 2];
		} else {
			potionsDrop = new Potion[amountOfPotions / 4];
		}

		for (int i = 0; i < potionsDrop.length; i++) {

			if (i % 2 == 0) {

				potionsDrop[i] = new HealthPotion();

			} else {

				potionsDrop[i] = new ManaPotion();
			}

		}

		return potionsDrop;
	}

	@Override
	public Armor generateArmorDrop() {
		Armor newArmor = null;
		int chance = rnJesus.nextInt(100) + 1;

		if (chance > 74) {
			int randomAttr = rnJesus.nextInt(aAttrs.length - 1) + 1;
			newArmor = new Armor(ArmorType.STUDDED_LEATHER, aAttrs[randomAttr]);

		}

		return newArmor;
	}

	@Override
	public Weapon generateWeaponDrop() {
		int chance = rnJesus.nextInt(100) + 1;
		Weapon weaponToReturn = null;
		if (chance < 35) {

			weaponToReturn = this.getCurrentlyEquppiedWeapon();
		}
		return weaponToReturn;
	}

}
