package trickyquestions;

class A {
	int x = 5;
}

class B extends A {
	int x = 6;
}

public class OverridingTest2 {
	public A getObject() {
		return new A();
	}

	public static void main(String[] args) {
		OverridingTest2 ot2 = new SubTest();
		System.out.println(ot2.getObject());
		System.out.println(ot2.getObject().x);
	}
}

class SubTest extends OverridingTest2 {
	public B getObject() {
		return new B();
	}
}
