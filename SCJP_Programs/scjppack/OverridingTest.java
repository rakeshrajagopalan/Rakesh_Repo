package scjppack;

public class OverridingTest {

	public static void main(String[] args) {
		A a = new B();
		a.aa();
	}
}

class A {
	void aa() {
		System.out.println("A");
	}
}

class B extends A {
	void aa() {
		System.out.println("B");
	}
}
