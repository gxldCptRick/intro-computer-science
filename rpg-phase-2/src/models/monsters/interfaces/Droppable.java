package models.monsters.interfaces;

import models.potions.base.Potion;

/** <h1> Droppable Interface</h1>
 * <p> this interface is used to create behaviors for loot drops.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 *
 */
public interface Droppable {

	/** randomly generates an amount of potions and returns the array.
	 * @return returns an array of potions to be dropped.
	 */
	public Potion[] generatePotionsDrop();
	
}
