package models.monsters;

import enums.monster.Bonus;
import models.monsters.base.Monster;
import models.monsters.interfaces.Droppable;
import models.potions.base.Potion;

/** <h1> Beholder Model Object</h1>
 * <p> Extends the Monster Object and Implements the Droppable interface.</p>
 * <p> it is a Beholder Monster with a low normal attack but,</p>
 * <p> a very high Special attack.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public final class Beholder extends Monster implements Droppable{
	
	{
		this.setName("Beholder");
	}
	
	/** Constructor for the Beholder Model Object.
	 * @param str the strength of the beholder.
	 * @param dex the dexterity of the beholder.
	 * @param intel the intelligence of the beholder.
	 */
	public Beholder(int str, int dex, int intel) {
		super(str,dex,intel, 50);
		
	}

	/* (non-Javadoc)
	 * @see models.monsters.base.Monster#attackNormal()
	 */
	@Override
	public int attackNormal() {
		int damage = 15 + this.getBonus(Bonus.DAMAGE);
	
		if(damage <= 0) {
			
			damage = 7;
		}
		
		return damage;
	}

	/* (non-Javadoc)
	 * @see models.monsters.base.Monster#attackSpecial()
	 */
	@Override
	public int attackSpecial() {
		int damage = 0;
		
		if (currentMP / MANA_USE > 0) {
			damage = 30 + getBonus(Bonus.SPELL_STRENGTH);
			
			if(damage <= 0) {
				damage = 15;
			}
			currentMP -= MANA_USE;
		}
	
		return damage;
		
	}

	/* (non-Javadoc)
	 * @see models.monsters.interfaces.Droppable#generatePotionsDrops()
	 */
	@Override
	public Potion[] generatePotionsDrop() {
		
		return null;
	}
}
