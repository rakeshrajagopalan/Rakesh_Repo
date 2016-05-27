package chapter7.utils;

import java.util.Arrays;

public class ArraysTest {
	public static void main(String[] args) {
		int[] ints = { 234, 345, 123, 5, 532, 5634, 25, 6, 23, 625 };
		Arrays.sort(ints);
		System.out.println(Arrays.binarySearch(ints, 25));
	}
}
