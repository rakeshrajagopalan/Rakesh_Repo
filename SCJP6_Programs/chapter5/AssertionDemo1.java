package chapter5;

public class AssertionDemo1 {

	public void one(int x) {
		assert (x >= 0) : test();
		System.out.println("X is positive! Yippe!!");
	}

	public String test() {
		return "Number cannot be negative";
	}

	public static void main(String[] args) {
		new AssertionDemo1().one(-5);
		int j = 7;
		assert (j == 12) : j = 15; // COMPILES FINE!
		assert (j > 12) : new Object(); // COMPILES FINE!
	}
}
