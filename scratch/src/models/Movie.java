package models;

public class Movie {

	private String title;
	private String rating;
	public final int _YEAR;
	
	
	
	public String getTitle() {
		return title;
	}
	public String getRating() {
		return rating;
	}
	
	public Movie(String title, String rating, int year) {
		this.title = title;
		
		this.rating = rating;
		
		_YEAR = year;
		
	}
	
	@Override
	
	public String toString() {
		
		String movieString =  getTitle() +  " (" + _YEAR + " - " + getRating() + ")";
		
		return movieString;
		
		
	}
	

	
	//the job of the ctor is to build ob of class in start state
	//Declare a Constructor aka the ctor
	//Formula Almost always but for this class always.
	//public [NOT STATIC!!!!!] [no EXPLICIT return type] ClassName ([parmas to set starting state]) 
	
	
	
	
}


