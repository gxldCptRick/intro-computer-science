package models.monsters;

import enums.monster.Bonus;
import models.monsters.base.Monster;
import models.monsters.interfaces.Droppable;
import models.potions.base.Potion;

/** <h1> BugBear Model Object</h1>
 * <p> extends Monster object and Implements the Droppable interface.</p>
 * <p> it is a Bug Bear with a decently high physical attack but a very low special attack.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class BugBear extends Monster implements Droppable{
	
	{
		this.setName("BugBear");
	}
	
	/** Constructor for BugBear Object.
	 * @param str the strength of the BugBear.
	 * @param dex the dexterity of the BugBear.
	 * @param intel the intelligence of the BugBear.
	 */
	public BugBear(int str, int dex, int intel) {
		super(str,dex,intel, 10);
		
	}

	/* (non-Javadoc)
	 * @see models.monsters.base.Monster#attackNormal()
	 */
	@Override
	public int attackNormal() {
		int damage =  15 + this.getBonus(Bonus.DAMAGE);
		
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
			damage = 20 + getBonus(Bonus.SPELL_STRENGTH);
			
			if(damage <= 0) {
				
				damage = 10;
			
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
		// TODO Auto-generated method stub
		return null;
	}
}
