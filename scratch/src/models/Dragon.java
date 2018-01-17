package models;

public class Dragon {
	public static String name = "Drogon", color = "Blacker then sinS";
	public static double lengthInMeters;
	public static boolean hasWings;

	public static String breatheFire(int intensity) {

		String message = name + " breathes fire at intensity level " + intensity + "!";

		if (intensity > 9000)
			message += "\nIts Over 9000!?!?!?!?!?!?!";
		return message;

	}

	public static String eatPeasents(String[] peasentNames) {
		String message = name + " ate ";
		if(peasentNames != null) {
			
		if (peasentNames.length != 0) {

			for (int i = 0; i < peasentNames.length; i++) {
				if (i < peasentNames.length - 1)
					message += peasentNames[i] + " and ";
				else
					message += peasentNames[i] + ".";

				if (i % 5 == 0)
					message += "\n";

			}
		}
		
		else {
			message += "nothing";
		}
		
		}
		else {
			message += "Nobody Hunger++";
		}
		
		
		return message;
		}
		
}
