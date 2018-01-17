package app;

import models.Movie;
 
public class Theater {

	public static Movie[] arrayOfMovies = new Movie[56];

	public static void openTheater() {

		initializeMovies();
		displayMarquee();
	}

	public static void initializeMovies() {

		// new Movie() - aka the constructor
		// instantiates a new movie object based on the movie class definition.
		// we are building a new movie object (aka instance)
			
			arrayOfMovies[0] = new Movie("Destructor" , 1202, "JJAbrahms", "PG");
			
			arrayOfMovies[1] = new Movie("Constructor", 22, "MichealBay", "NC17" );
				
			arrayOfMovies[2] = new Movie("FleebBall Heros" , 122, "John Wayne", "PG13");
	}

	public static void displayMarquee() {

		for (Movie film : arrayOfMovies) {

			if (film != null) {

				System.out.println(film.getTitle() + " (" + film.getMpaaRating() + ")");

			}

		}

	}

}
