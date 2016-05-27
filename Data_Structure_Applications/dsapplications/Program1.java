package dsapplications;

import algorithms.SelectionSort;

/**
 * Given an array of size n. It contains numbers in the range 1 to n. Each
 * number is present at least once except for 1 number. Find the missing number.
 * 
 * @author Rakesh
 * 
 */

public class Program1 {
	public static void main(String[] args) {
		int[] array = { 14, 2, 6, 5, 10, 11, 1, 6, 9, 8, 7, 12, 3, 4, 15 };
		array = SelectionSort.selectionSort(array);
		int missingVal = 0;
		OUTER: for (int i = 0; i < array.length; i++) {
			if (array[i] == (i + 1)) {
				continue;
			} else {
				INNER: for (int j = i; j < array.length; j++) {
					if (array[j] == (i + 1)) {
						continue OUTER;
					} else {
						continue INNER;
					}
				}
				missingVal = i;
				break OUTER;
			}
		}
		System.out.println("The missing val is: " + missingVal);
	}
}
