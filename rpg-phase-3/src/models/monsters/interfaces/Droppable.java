package models.monsters.interfaces;

import java.util.Random;

import enums.items.ArmorAttribute;
import enums.items.WeaponAttribute;
import models.items.Armor;
import models.items.Weapon;
import models.potions.base.Potion;

/**
 * <h1>Droppable Interface</h1>
 * <p>
 * this interface is used to create behaviors for loot drops.
 * </p>
 * 
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 *
 */
public interface Droppable {

	/**
	 * the random object available to all that implement the Droppable Interface.
	 */
	public static final Random rnJesus = new Random();
	
	public static final ArmorAttribute[] aAttrs = ArmorAttribute.values(); 

	public static final WeaponAttribute[] wAttrs = WeaponAttribute.values();
	/**
	 * randomly generates an amount of potions and returns the array.
	 * 
	 * @return returns an array of potions to be dropped.
	 */
	public Potion[] generatePotionsDrop();

	/**
	 * randomly generates a piece of armor to be dropped.
	 * 
	 * @return returns a piece of armor to be dropped
	 */
	public Armor generateArmorDrop();

	/**
	 * randomly generates a weapon to be dropped.
	 * 
	 * @return returns a weapon to be dropped.
	 */
	public Weapon generateWeaponDrop();

}
