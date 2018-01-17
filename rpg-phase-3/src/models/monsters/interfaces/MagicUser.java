package models.monsters.interfaces;

import java.util.Random;

import enums.Spells.Spell;

public interface MagicUser {
	public static final Spell[] spells = Spell.values();
	public static final Random Gandor = new Random();
	int castLevelOneSpell();
	int castLevelTwoSpell();
	int castLevelThreeSpell();
}
