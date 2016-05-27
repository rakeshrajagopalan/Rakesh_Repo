package dsapplications;

/**
 * How do you convert a decimal number to its hexa-decimal equivalent?.
 * 
 * @author Rakesh
 * 
 */
public class Program2 {
	public static void main(String[] args) {
		int x = 336;
		int counter = 0;
		int value = 0;
		while (x > 0) {
			int remainder = x % 10;
			value = (int) (value + remainder * (Math.pow(16, counter)));
			counter++;
			x /= 10;
		}
		System.out.println(value);
	}
}
