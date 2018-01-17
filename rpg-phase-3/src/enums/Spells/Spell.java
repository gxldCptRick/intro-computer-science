package enums.Spells;

public enum Spell {

	MAGIC_MISSLE(SpellType.DAMAGE,60),
	FIRE_BALL(SpellType.DAMAGE, 50),
	MINOR_HEAL(SpellType.HEAL, 100),
	MAGIC_SWORD(SpellType.DAMAGE, 50);
	
	public final SpellType TYPE;
	
	public final int EFFECT_VALUE;
	
	private Spell(SpellType type, int eValue) {
		this.TYPE = type;
		this.EFFECT_VALUE = eValue;
	}
}
