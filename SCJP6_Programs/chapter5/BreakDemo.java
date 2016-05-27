package chapter5;

public class BreakDemo {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			if (i == 5) {
				break;
			}
			System.out.print(i);
		}
		System.out.println("\nOutside the loop");
	}
}
