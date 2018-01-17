package models;
import java.util.Random;

import enums.DieColor;
import enums.DieFace;
public class Die {

	private static Random rnJesus = new Random();
	public final DieColor color;
	private DieFace[] dieFaces = {DieFace.BRAIN, DieFace.SHOT, DieFace.RUN, DieFace.RUN, null, null};
	private DieFace currentFace;
	
	public Die(DieColor color) {
		
		this.color = color;
		
		dieFaces[4] = color == DieColor.GREEN ? DieFace.BRAIN : DieFace.SHOT;
		dieFaces[5] = color == DieColor.RED ? DieFace.SHOT : DieFace.BRAIN;
				
	
	}
	
	public DieFace roll() {
		
		int numRolled = rnJesus.nextInt(dieFaces.length);	
		
		currentFace = dieFaces[numRolled];
		
		return currentFace;
	
	}
	
	public DieFace getCurrentFace() {
		
		return this.currentFace;
	}
	
	@Override
	public String toString() {
		return color + " " + getCurrentFace();
	}
	
	
	
}
