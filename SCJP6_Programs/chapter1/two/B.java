package chapter1.two;

import chapter1.one.A;

public class B extends A {
	private int i = 10;
	public static void main(String[] args) {
		A a = new A();
//		System.out.println(a.i);
		B b = new B();
		System.out.println(b.i);
		b.method();
		System.out.println(j);
	}
	public void method(int... ints) {
		
	}
}
