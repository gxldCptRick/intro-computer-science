package models.potions;

import enums.potions.PotionType;
import models.potions.base.Potion;

/** <h1> HealthPotion Model Object.</h1>
 * <p> extends Potion Object.</p>
 * <p> this defines all health potions RPG.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 *
 */
public class HealthPotion extends Potion {

	/** Default Health Potion Constructor
	 *  <p> generates a health potion that is always the same.  
	 */
	public HealthPotion(){
		
		super(10, PotionType.HEALTH);
	
	}
	
}
