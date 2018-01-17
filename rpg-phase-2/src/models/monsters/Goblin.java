package models.monsters;

import enums.monster.Bonus;
import models.monsters.base.Monster;
import models.monsters.interfaces.Droppable;
import models.potions.base.Potion;

/** <h1> Goblin Model Object</h1>
 * <p> extends the Monster object and implements the Droppable interface.</p>
 * <p> this represents a goblin that has both a low normal attack and</p>
 * <p> a very low Special attack. it is the weakest of all monsters.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class Goblin extends Monster implements Droppable{
	
	{
		this.setName("Goblin");
	}
	
	/** Constructor for Goblin.
	 * @param str strength of Goblin
	 * @param dex dexterity of Goblin
	 * @param intel intelligence of Goblin
	 */
	public Goblin(int str, int dex, int intel) {
		super(str,dex,intel,5);
		
	}

	/* (non-Javadoc)
	 * @see models.monsters.base.Monster#attackNormal()
	 */
	@Override
	public int attackNormal() {
		int damage = 10 + this.getBonus(Bonus.DAMAGE);
		
			if(damage <= 0) {
				damage = 5;
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
			damage = 10 + getBonus(Bonus.SPELL_STRENGTH);
			
			if(damage <= 0) {
				
				damage = 7;
			
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
