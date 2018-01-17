package app;

import java.util.Scanner;

public class Program {
	
	private static void doMath(char Operation, int val1, int val2) {
		switch(Operation) {
		
		case 'a':
			System.out.println(val1 + " + " + val2 +" = "  + (val1 + val2));;
			break;
		case 's':
			System.out.println(val1 + " - " + val2 + " = " + (val1 - val2));
			break;
		case 'm':
			System.out.println(val1 + " * " + val2 + " = " + (val1 * val2) );
			break;
		case 'd':
			System.out.println(val1 + " / " + val2 + " = " + (val1 / val2));
			break;
		case 'r':
			System.out.println(val1 + " % " + val2 + " = " + (val1 % val2));
			break;
		
		}
		
		
	}
	
	public static boolean isInt(String input){
		boolean isValidInt = false;
		
		try {
			int n = Integer.parseInt(input);
			isValidInt = true;
			if(n == 2) {
				System.out.println("the cake is a lie");
			}
		}
		
		catch(Exception e){
			
		}
		return isValidInt;
		
	}
	
	public static void main(String[] args) {
		
		int num1 = 0, num2 = 0;
		boolean cont = true;
		String operation = "", test = "";
		
		Scanner input = new Scanner(System.in);
		
		while(cont) {
			System.out.println("Hello User.");
			
			System.out.println("Please give me a number to work with.");
		
			test = input.next();
			
			while(!(isInt(test))) {
				System.out.println("please i need something to i can work with");
				test = input.next();
			}
			
			num1 = Integer.parseInt(test);
			
			System.out.println("Please give me another number to work with.");
			
			test = input.next();
			
			while(!(isInt(test))) {
				System.out.println("please i need something to i can work with");
				test = input.next();
			}
			
			num2 = Integer.parseInt(test);
			
			System.out.println("What kind of math do you want to do");
			
			System.out.println("(a)ddition (s)ubraction (m)ultiplication (d)ivision (r)emainder");
			
			operation = input.next();
			while((operation.charAt(0) != 'a'  && operation.charAt(0) != 's' && operation.charAt(0) != 'm' && operation.charAt(0) != 'd' && operation.charAt(0) != 'r') || ((operation.charAt(0) == 'd' || operation.charAt(0) == 'r') && (num2 == 0))) {
				
				if((operation.charAt(0) == 'd' || operation.charAt(0) == 'r') && (num2 == 0)) {
					System.out.println("im sorry im not chuck norris so we cant divide by zero");
					System.out.println("please select another operation");
				}
				else {
					System.out.println("yoooo input a valid string");
				}
				operation = input.next();	
				
			}
					
			doMath(operation.charAt(0), num1, num2);
			
			System.out.println("Would you like to do another calculation?");
			test = input.next();
			if(test.equals("no")){
				cont = false;
			}
			
		}
		
		input.close();
		 
		 
	}

}
