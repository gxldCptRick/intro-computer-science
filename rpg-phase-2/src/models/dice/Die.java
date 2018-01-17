package models.dice;

import java.util.Random;

/** <h1> Die Model Object</h1>
 * <p> this is a representation of a die.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class Die {
	
	/** the number of sides on the die.
	 */
	public final int NUM_OF_SIDES;
	
	/** the random generator for all die instances.
	 */
	private final static Random rnJesus = new Random();
	
	/** the current value of the die.
	 */
	private int currentValue = 1;
	
	/** Constructor for the Die Object.
	 * @param sides the amount of sides on the die.
	 */
	public Die(int sides) {
		NUM_OF_SIDES = sides;
	}
	
	/** this rolls a die and sets the current value to what was rolled and returns the current value.
	 * @return returns the result of rolled.
	 */
	public int roll() {

		this.currentValue = rnJesus.nextInt(NUM_OF_SIDES) + 1;
	
		return this.currentValue;

	}
	
}
