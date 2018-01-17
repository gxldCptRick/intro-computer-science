package models;

public class Movie {
	
	private String title;
	private int durationInMinutes;
	private String director;
	private String mpaaRating;
	
	

	
	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public int getDurationInMinutes() {
		return durationInMinutes;
	}




	public void setDurationInMinutes(int durationInMinutes) {
		
		if(durationInMinutes < 0) {
	
			throw new IllegalArgumentException("duration cannot be negative");
		
		}
		
		this.durationInMinutes = durationInMinutes;
	
	}




	public String getDirector() {
		return director;
	}




	public void setDirector(String director) {
		this.director = director;
	}




	public String getMpaaRating() {
		return mpaaRating;
	}




	public void setMpaaRating(String mpaaRating) {
		this.mpaaRating = mpaaRating;
	}




	public Movie(String title, int duration, String director, String mpaaRating) {
		
		this.title = title;
		this.durationInMinutes = duration;
		this.director = director;
		this.mpaaRating = mpaaRating;
	
		
	}

}
