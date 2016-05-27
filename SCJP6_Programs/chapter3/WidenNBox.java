package chapter3;

public class WidenNBox {
	static void go(Long l) {
	}

	public static void main(String[] args) {
		int i = 5;
		// go(i); This wont compile, as 
		// the comiler uses Widen and then Box.
	}
}
