package chapter5;

public class EnhancedForLoop {
	public static void main(String[] args) {
		int[][] a = { { 12, 3 }, { 213, 34 }, { 5 }, { 3, 5, 8, 3, 6, 7, 8 } };
		for (int[] a1 : a) {
			for (int a2 : a1) {
				System.out.println("The next element is: " + a2);
			}
		}
	}
}
