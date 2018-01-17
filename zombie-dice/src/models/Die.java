package models;

import java.util.Random;

import enums.DieColor;
import enums.DieFace;

public class Die {

	private static int maxID = 0;

	public final int ID;
	public final int dieSize = 6;
	public final DieColor color;
	private final DieFace[] faces = {null,null, DieFace.BRAIN, DieFace.SHOT, DieFace.RUN, DieFace.RUN};
	
	private DieFace currentFace;
	private boolean active;
	
	public Die(DieColor color) {
		
		this.color = color;
		
		faces[0] = this.color == DieColor.GREEN? DieFace.BRAIN : DieFace.SHOT;
		
		faces[1] = this.color == DieColor.RED? DieFace.SHOT : DieFace.BRAIN;
		
		ID = maxID;
		
		maxID++;
	
	}
	
	public DieFace roll() {
		
		Random randoCalressian = new Random();
		
		int num = randoCalressian.nextInt(faces.length);
		
		currentFace = faces[num];
		
		return currentFace;
		
	}
	
	public DieFace getCurrentFace() {
		
		return currentFace;
		
	}
	
	public boolean isActive() {
		
		return active;
		
	}
	
	public void setActive(boolean active) {
		
		this.active = active;
		
	}
	
	@Override
	
	public String toString() {
		
		return String.format("ID: %d The %s Die Rolled %s", this.ID, this.color, this.getCurrentFace());
	}
	
	
}
