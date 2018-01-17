package models.dice;

/** <h1> DiceBag Model Object</h1>
 * <p> this is a DiceBag in which you call upon it and roll any amount of dice that are any size.</p>
 * <p> you can also add a modifier to the dice roll.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class DiceBag {

	/** this method rolls a specified amount of dice of a specified size and returns the result.
	 * @param sides the amount of sides of the dice being rolled.
	 * @param amountOfDice the amount of dice being rolled.
	 * @return returns the amount rolled by the amount of dice of a certain size.
	 */
	public int rollDice(int sides, int amountOfDice) {
	
		int value = 0;
		
		Die[] allTheDice = new Die[amountOfDice];
		
		for(int i = 0; i < allTheDice.length; i++) {
			
			allTheDice[i] = new Die(sides);
			
		}
		
		for(Die die : allTheDice) {
			value += die.roll();
		}
		
		return value;
	}
	
	/** this method rolls a specified amount of dice of a specified size and then adds a modifier to the result and returns that value.
	 * @param sides the amount of sides on the die to be rolled.
	 * @param amountOfDice the amount of dice that will be rolled.
	 * @param modifier the modifier that will be added to the result.
	 * @return returns the result of value of all the dice you decided to roll plus the modifier.
	 */
	public int rollDice(int sides, int amountOfDice, int modifier) {
		
		int value = rollDice(sides, amountOfDice) + modifier; 
		
		return value;
	
	}
	
	/** this method rolls a single die and returns the result.
	 * @param sides the amount of sides on the dice.
	 * @return returns the result of the dice that was being rolled of the specified amount of sides.
	 */
	public int rollDie(int sides) {
		
		Die currentDie = new Die(sides);
		
		int value  = currentDie.roll();
		
		return value;
		
	}
	
	/** this method rolls a single die and adds a modifier and returns the result.
	 * @param sides the amount of sides on the dice being rolled.
	 * @param modifier modifier to the dice roll.
	 * @return returns the result of a dice roll plus modifier with the dice being rolled one of the sides.
 	 */
	public int rollDie(int sides, int modifier) {
		
		int value = rollDie(sides) + modifier;
		
		return value;
		
	}
	
	
	
}
