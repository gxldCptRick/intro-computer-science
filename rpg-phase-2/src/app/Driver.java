package app;

import controllers.GameMaster;

/** <h1> Driver Controller Object</h1>
 * <p>this is the host for the main method and the actual start of the program as a whole.</p>
 * @author Andres Hermilo Carrera
 * @since November 24, 2017
 * @version v1.0
 */
public class Driver {

	/** this main method creates a new GameMaster object and immediately invoke its start function.
	 * @param args arguments passed in from the command line
	 */
	public static void main(String[] args) {
		
		(new GameMaster()).start();
	
	}
	
}
