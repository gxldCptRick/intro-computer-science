package lib;

public class SimpleMath {

	public static long factorial(byte num) {

		long factorial = 1;
		if (num == 0) {
			
			factorial = 1;
		
		} else if(num > 1 && num < 21) {
			
			for (; num > 0; num--) {

				factorial *= num;
			}
		
		} else {
			
			throw new IllegalArgumentException("Num cannot be less than 0 nor greater than 20");
		}

		return factorial;
	}

}
