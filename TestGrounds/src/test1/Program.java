package test1;

import java.util.Random;

public class Program {
	
	public static void run() {
	
		Random rnJesus = new Random();
		
		int[] nums = new int[201];
		
		for(int i = 0; i < nums.length; i++) {
			
			nums[i] = rnJesus.nextInt(201) - 100;
			
		}
		
		for(int num : nums) {
		
			System.out.println(num);
		
		}
		
		System.out.println("printing even indices");
		
		printArrayEvenIndices(nums);
		
	}
	
	
	private static void printArrayEvenIndices(int[] nums) {
		
		for(int i = 0; i < nums.length; i += 2) {
			
			System.out.println(nums[i] + " - " + i);
			
		}
		
	}


}
