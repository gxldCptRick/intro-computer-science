package models.items;

import enums.items.WeaponAttribute;
import enums.items.WeaponType;

/**
 * <h1>Weapon Object</h1>
 * 
 * @author Andres Hermilo Carrera
 * @since November 25, 2017
 * @version v1.0
 *
 */
public class Weapon {

	/**
	 * the name of the weapon
	 */
	public final String NAME;

	/**
	 * the type of weapon.
	 */
	public final WeaponType TYPE;

	/**
	 * the special attribute for the object.
	 */
	public final WeaponAttribute ATTR;

	/**
	 * Constructor for the weapon object.
	 * 
	 * @param type
	 *            the type of weapon being created.
	 * @param attr
	 *            the special attribute for the weapon to be created.
	 */
	public Weapon(WeaponType type, WeaponAttribute attr) {
		this.TYPE = type;
		this.ATTR = attr;

		if (attr != WeaponAttribute.NONE) {
			this.NAME = attr + " " + type;
		} else {
			this.NAME = type.toString();
		}
	}

	/**
	 * Constructor for the weapon object. this creates a weapon of a specified type
	 * with no special attribute.
	 * 
	 * @param type
	 *            the type of weapon being created
	 */
	public Weapon(WeaponType type) {
		this.TYPE = type;
		this.ATTR = WeaponAttribute.NONE;
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
