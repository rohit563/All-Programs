import java.util.Arrays;
import java.util.Random;
/**
 * This class sorts the randomly generated array of numbers by using the merge sort algorithm
 * @author rohit
 *
 */
public class MergeSort {
	/**
	 * holds the randomly generated numbers
	 */
	private int[] arr;

	/**
	 * This method sets the size the array and instantiates it
	 * @param size
	 * 			size of array
	 */
	public MergeSort(int size) {
		// TODO Auto-generated constructor stub
		arr = new int[size];

	}
	/**
	 * generates random numbers from 0 to 100 and
	 * adds them to the array and then calls the sort method 
	 */
	public void startSort(){
		Random randomNum = new Random();
		for (int i = 0; i < arr.length; i++) {
			int ranNum = randomNum.nextInt(1001);
			arr[i] = ranNum;
		}
		System.out.println("Unsorted Array: ");
		System.out.print(arr[0]);
		for(int i = 1; i < arr.length; i++)
		{
			System.out.print(", " + arr[i]);
		}
		arr = sort(arr);
		System.out.println("\nSorted Array: ");
		System.out.print(arr[0]);
		for(int i = 1; i < arr.length; i++)
		{
			System.out.print(", " + arr[i]);
		}
		
	}
	/**
	 * This method takes in the array and recursively calls itself and keeps spiting the array and then
	 * calls the combine function to put the elements back into the array 
	 * @param arr
	 * 			takes in the randomly generated number array
	 * @return array
	 * 			this array is a sorted integer array
	 */
	private int[] sort(int arr[]) {
		int arrLen = arr.length;
		if (arrLen <= 1) {
			return arr;
		} else {
			int middle = (arrLen) / 2;
			int oneLen = middle;
			int[] oneHalf = new int[oneLen];
			int secondLen = arrLen - oneLen;
			int[] secondHalf = new int[secondLen];
			for (int i = 0; i < middle; i++) {
				oneHalf[i] = arr[i];
			}
			for (int j = oneLen; j < arrLen; j++) {
				secondHalf[j - oneLen] = arr[j];
			}
			oneHalf = sort(oneHalf);
			System.out.println("oneHalf: " + Arrays.toString(oneHalf));
			secondHalf = sort(secondHalf);
			System.out.println("secondhalf: " +Arrays.toString(secondHalf));
			return combine(oneHalf, secondHalf);
		}

	}
	/**
	 * This method combines both arrays into one array and sorts
	 * @param oneHalf
	 * 			the first half of the array to be sorted
	 * @param secondHalf
	 * 			the second half of the array to be sorted
	 * @return	array
	 * 			this array is a sorted integer array
	 */
	private int[] combine(int oneHalf[], int secondHalf[]) {
		int combinedLen = oneHalf.length + secondHalf.length;
		int oneCounter = 0;
		int secondCounter = 0;
		int combCounter = 0;
		int[] combinedArr = new int[combinedLen];
		while (oneCounter < oneHalf.length && secondCounter < secondHalf.length) {
			if (oneHalf[oneCounter] < secondHalf[secondCounter]) {
				combinedArr[combCounter] = oneHalf[oneCounter];
				oneCounter++;
			} else {
				combinedArr[combCounter] = secondHalf[secondCounter];
				secondCounter++;
			}
			combCounter++;
		}
		if (oneCounter < oneHalf.length) {
			while (oneCounter < oneHalf.length) {
				combinedArr[combCounter] = oneHalf[oneCounter];
				oneCounter++;
				combCounter++;
			}
		} else if (secondCounter < secondHalf.length) {
			while (secondCounter < secondHalf.length) {
				combinedArr[combCounter] = secondHalf[secondCounter];
				secondCounter++;
				combCounter++;
			}
		}
		return combinedArr;

	}

}
