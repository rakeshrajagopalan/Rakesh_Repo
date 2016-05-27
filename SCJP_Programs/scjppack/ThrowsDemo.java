package scjppack;

public class ThrowsDemo {

	static void throwMethod() throws IllegalAccessException {
		throw new IllegalAccessException();
	}

	public static void main(String[] args) {
		try {
			throwMethod();
		} catch (IllegalAccessException ex) {
			System.out.println("Oh oh");
		}
	}

}
