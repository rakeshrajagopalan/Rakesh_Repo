package chapter7.utils;

import java.util.Arrays;
import java.util.Comparator;

class ReverseSort implements Comparator<String> {
	public int compare(String o1, String o2) {
		return o2.compareTo(o1);
	}

}

public class SearchNSort {

	public static void main(String[] args) {
		String[] strings = { "one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine", "ten" };
		System.out.println("Before Forward Sort: "
				+ Arrays.binarySearch(strings, "six"));
		Arrays.sort(strings);
		System.out.println("After Forward Sort: "
				+ Arrays.binarySearch(strings, "six"));
		ReverseSort reverseSort = new ReverseSort();
		Arrays.sort(strings, reverseSort);
		System.out.println("After Reverse Sort: "
				+ Arrays.binarySearch(strings, "six"));
	}
}
