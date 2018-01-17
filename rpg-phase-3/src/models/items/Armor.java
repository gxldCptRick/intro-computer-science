package models.items;

import enums.items.ArmorAttribute;
import enums.items.ArmorType;

/**
 * <h1>Armor Object</h1>
 * 
 * @author Andres Hermilo Carrera
 * @since November 25, 2017
 * @version v1.0
 *
 */
public class Armor {

	/**
	 * the name of the armor.
	 */
	public final String NAME;

	/**
	 * the type of the armor.
	 */
	public final ArmorType TYPE;

	/**
	 * the special attribute of the armor.
	 */
	public final ArmorAttribute ATTR;

	/**
	 * Constructor for Armor Object.
	 * 
	 * @param type
	 *            the type of armor that will be created.
	 * @param attr
	 *            special attribute for the armor.
	 */
	public Armor(ArmorType type, ArmorAttribute attr) {
		this.TYPE = type;
		this.ATTR = attr;

		if (attr != ArmorAttribute.NONE) {
			this.NAME = attr + " " + type;
		} else {
			this.NAME = type.toString();
		}
	}

	/**
	 * Constructor for the Armor Object creates an Armor with no special attribute.
	 * 
	 * @param type
	 *            type of armor that will be created.
	 */
	public Armor(ArmorType type) {
		this.TYPE = type;

		this.ATTR = ArmorAttribute.NONE;

		this.NAME = type.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.NAME;
	}
}
