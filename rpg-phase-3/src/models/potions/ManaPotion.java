package models.potions;

import enums.potions.PotionType;
import models.potions.base.Potion;

/**<h1> ManaPotion Model Object </h1>
 * <p> extends Potion object </p>
 * <p> defines all ManaPotions </p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 *
 */
public class ManaPotion extends Potion{

	/** Default Constructor for Mana Potions
	 * 	<p>generates a mana potion that is always the same. </p>
	 */
	public ManaPotion() {
		
		super(7, PotionType.MANA);
	
	}
	
}
