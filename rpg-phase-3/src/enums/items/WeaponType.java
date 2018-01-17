package enums.items;

public enum WeaponType {
DAGGER(4, 1),
SHORT_SWORD(6, 1),
BROAD_SWORD(6, 2),
FLAIL(8, 1),
GLAIVE(10, 1),
GREATAXE(12, 1),
GREATCLUB(8, 2);
	
	public final int DIE_SIZE;
	
	public final int AMOUNT_OF_DICE;
	
	private WeaponType(int dieSize, int amountOfDice) {
		this.DIE_SIZE = dieSize;
		this.AMOUNT_OF_DICE = amountOfDice;
	}
}
