package enums.items;

public enum ArmorType {
	PADDED(6, 8), 
	LEATHER(4, 9),
	STUDDED_LEATHER(2, 10), 
	CHAINMAIL(-3, 12), 
	PLATEMAIL(-5, 14);
	
	public final int DEFENSE_MODIFIER;
	public final int ARMOR_RATING;

	private ArmorType(int defenseMod, int armorRating) {
		this.ARMOR_RATING = armorRating;
		this.DEFENSE_MODIFIER = defenseMod;
	}
}
