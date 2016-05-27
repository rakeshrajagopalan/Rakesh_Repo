package chapter2;

class Horse {

	public Horse() {
		System.out.println("Inside Horse");
	}

}

public class Foal extends Horse {
	
	public Foal() {
		this(test());
	}

	public Foal(String string) {
		System.out.println(string);
	}

	public static String test() {
		return "Test";
	}

	public static void main(String[] args) {
		new Foal();
	}

}
