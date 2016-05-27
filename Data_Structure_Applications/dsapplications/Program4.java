package dsapplications;

/**
 * Program to reverse the numbers.
 * 
 * @author Rakesh
 * 
 */

public class Program4 {
	public static void main(String[] args) {
		int num = 1052;
		int rev = 0, rem;
		for (; num > 0; num /= 10) {
			rem = num % 10;
			rev = (rev * 10) + rem;
		}
		System.out.println(rev);
	}
}
