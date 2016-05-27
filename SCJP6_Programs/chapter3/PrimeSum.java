package chapter3;

//Finds sum of first 100 prime numbers
public class PrimeSum {

	public static void main(String[] args) {
		long sum = 0;
		int count = 0;
		int number = 2;
		while (count < 100) {
			if (isPrime(number)) {
				sum += number;
				count++;
			}
			number++;
		}
		System.out.println(sum);
	}

	private static boolean isPrime(final int number) {
		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
