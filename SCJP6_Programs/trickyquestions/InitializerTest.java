package trickyquestions;

class Middle extends Top {
	static {
		System.out.print("D");
	}

	public Middle() {
		System.out.print("E");
	}

	public Middle(String s) {
		System.out.print("F");
	}
}

class Top {
	static {
		System.out.print("A");
	}

	public Top() {
		System.out.print("B");
	}

	public Top(String s) {
		System.out.print("C");
	}
}

class Bottom extends Middle {
	static {
		System.out.print("G");
	}

	public Bottom() {
		System.out.print("H");
	}

	public Bottom(String s) {
		System.out.print("I");
	}
}

public class InitializerTest {
	static {
		System.out.println("Static Init");
	}

	public static void main(String[] args) {
		new Bottom();
	}
}
