package dsapplications;

/**
 * You are given some denominations of coins in an array (int denom[])and
 * infinite supply of all of them. Given an amount (int amount), find the
 * minimum number of coins required to get the exact amount
 * 
 * @author Rakesh
 * 
 */

public class Program3 {
	public static void main(String[] args) {
		int[] denominations = { 1, 2, 5, 10, 50, 100 };
		int numOfCoins = 0;
		int amount = 8751;
		int quotient;
		for (int i = (denominations.length - 1); i >= 0; i--) {
			int value = denominations[i];
			quotient = amount / value;
			amount %= value;
			numOfCoins = numOfCoins + quotient;
		}
		System.out.println(numOfCoins);
	}
}
