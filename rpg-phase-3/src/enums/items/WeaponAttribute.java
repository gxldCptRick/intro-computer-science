package enums.items;

public enum WeaponAttribute {
	NONE(0, 0),
	HOLY(2, 0),
	FLAME(0, 5),
	SHADOW(1, 1),
	MASTERWORK(1,5);
	
	public final int ADDITIONAL_DICE;
	
	public final int BONUS;
	
	private WeaponAttribute(int additionalDice, int bonus){
		
		this.ADDITIONAL_DICE = additionalDice;
		this.BONUS = bonus;
		
	}
}
