import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class has one public method and two private methods and one constructor.
 * 
 * @author rbanda
 *
 */
public class BucketSort {

	/**
	 * Number of integers or size of an array that will be created with sort method
	 */
	private int size;

	/**
	 * the constructor, which sets the size of the array 
	 * 
	 * @param size
	 *            Number of integers or size of an array that will be created with sort method
	 */
	public BucketSort(int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
	}
	
	/**
	 * Creates an array of length size and places size number of randomly generated integers in an array that is then
	 * sorted by calling the gathering and distribution method
	 */
	public void Sort() {

		int largestNum = 0;
		int count = 0;
		int[] arr = new int[size];
		int[][] arr2 = new int[10][size];
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				arr2[i][j] = -1;
			}
		}
		Random randomNum = new Random();
		for (int i = 0; i < arr.length; i++) {
			int ranNum = randomNum.nextInt(101);
			arr[i] = ranNum;
			if (largestNum < arr[i]) {
				largestNum = arr[i];

			}

		}
		System.out.println("Unsorted Array: ");
		System.out.print(arr[0]);
		for(int i = 1; i < arr.length; i++)
		{
			System.out.print(", " + arr[i]);
		}
		count = (int) (Math.log10(largestNum) + 1); //gets number of places the largest number has and then sets count equal to the 
		for (int i = 0; i < (count); i++) {

			distribution(arr, arr2, i);
			gathering(arr, arr2);

		}
		System.out.println("\nSorted Array: ");
		System.out.print(arr[0]);
		for(int i = 1; i < arr.length; i++)
		{
			System.out.print(", " + arr[i]);
		}

	}
	/**
	 * @param arr,, placeCounter
	 * 			takes integer from arr and places in arr2 based on 
	 * @param arr2
	 * 			two dimensional array that holds arr integers
	 * @param placeCounter
	 * 			place counter counts the number of places
	 */
	private void distribution(int[] arr, int[][] arr2, int placeCounter) {
		int num = 0;
		int numPlaceHold = 0;
		int multiplier = 0;
		for (int i = 0; i < arr.length; i++) {
			if (placeCounter > 0) {
				multiplier = (int) Math.pow(10, placeCounter);
				numPlaceHold = arr[i] / multiplier;
			}
			else {
				numPlaceHold = arr[i];

			}
			num = numPlaceHold % 10;

			arr2[num][i] = arr[i];
		}
	}
	
	/**
	 * @param arr
	 * 		gets from arraylist and puts into arr
	 * @param arr2
	 * 		loops through arr2 and adds to and arraylist
	 * 
	 */
	private void gathering(int[] arr, int[][] arr2) {
		ArrayList<Integer> tempNumHolder = new ArrayList<Integer>();

		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				if (arr2[i][j] > -1) {

					tempNumHolder.add(arr2[i][j]);
					arr2[i][j] = -1;

				}
			}
		}
		for (int i = 0; i < tempNumHolder.size(); i++) {
			arr[i] = tempNumHolder.get(i);

		}
		tempNumHolder.clear();

	}

}
