package models.potions.base;

import java.util.Random;
import enums.potions.*;
/** <h1> Potion Model Object </h1>
 * <p> this is the base class for all potion types.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class Potion {

	/** the Random object that generates the random number for all instances of potion. 
	 */
	private static final Random rnJesus = new Random();

	/** the Type of potion based on the Type enum.
	 */
	public final Type type;
	
	/** the numerical value of the potions effect.
	 */
	protected int effectValue;
	
	/** Constructor for Potion
	 * it takes in a modifier and multiplies it with a random number between 1 - 10 inclusive and sets the type of potion with the type past in.
	 * @param modifier modifier for the potion being generated.
	 * @param type the type of the potion.
	 */
	public Potion (int modifier , Type type) {
		
		effectValue = modifier * (rnJesus.nextInt(10) + 1);
		
		this.type = type;
	
	}
	
	/** this method stores the effect value of the potion and returns it to the caller while zeroing out the potion effect value.
	 * @return returns the effect value of the potion.
	 */
	public int usePotion() {
		
		int value = effectValue;
		
		effectValue -= effectValue;
		
		return value;
	
	}
	
	/** this method checks if the effect value is 0 and returns the result.
	 * @return returns whether or not the potion is used.
	 */
	public boolean isUsed() {
		
		return effectValue == 0;
	
	}
	
	/** getter for the effect value property.
	 * @return returns the effect value of the potion.
	 */
	public int getEffectValue() {
		return effectValue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	
	public String toString() {
	
		return type +" potion restores " + this.effectValue + " " +type;
	
	}
	
}
