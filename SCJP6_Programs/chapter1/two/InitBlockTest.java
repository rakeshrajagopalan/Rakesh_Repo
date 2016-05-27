package chapter1.two;

class SomeClass {
	public SomeClass() {
		System.out.println("Some Class");
	}
}

public class InitBlockTest extends SomeClass {
	{
		System.out.println("Init Block");
	}

	public InitBlockTest() {
		System.out.println("Inside Constructor");
	}

	public static void main(String[] args) {
		new InitBlockTest();
	}
}
