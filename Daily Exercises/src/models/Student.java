package models;

public class Student {
	private static int internalId = 0;
	public final int studentID;
	private String firstName;
	private String lastName;
	
	
	public Student(String firstName, String lastName) {
		
		studentID = ++internalId;
	
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public String getFirstName() {
		
		return this.firstName;
		
	}
	
	public String getLastName() {
		
		return this.lastName;
	}
	
	public String getName() {
		return getFirstName() + " " + getLastName();
	}
	
	@Override
	
	public String toString() {
		
		return getName() + " - Id: " + this.studentID;
		
	}
	

}
