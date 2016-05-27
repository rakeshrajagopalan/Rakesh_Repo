package chapter3;

public class BoxNWiden {
	static void go(Number n) {
		// This method will be called, as it is easier to
		// widen an Integer to Number,
		// than widening an Integer to a Object
		System.out.println("Inside Number");
		Integer i = (Integer) n;
		System.out.println(i);
	}

	static void go(Object n) {
		System.out.println("Inside Object");
		Integer i = (Integer) n;
		System.out.println(i);
	}

	public static void main(String[] args) {
		int i = 5;
		go(i); // Box and Widen is legal!!!
	}
}
