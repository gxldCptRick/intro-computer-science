package enums.items;

public enum ArmorAttribute {
	NONE(0, 0),
	FINE(0, 2),
	MASTERWORK(1, 5),
	LIGHT_WEIGHT(3, 0),
	MYSTIC(2, 3),
	HOLY(0, 5);
	
	public final int DEFENSE_MODIFIER;
	
	public final int ARMOR_RATING;
	
	private ArmorAttribute(int defenseMod, int ar) {
		this.DEFENSE_MODIFIER = defenseMod;
		this.ARMOR_RATING = ar;
	}
}
