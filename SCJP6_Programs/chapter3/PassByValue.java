package chapter3;

import java.awt.Dimension;

public class PassByValue {

	public PassByValue() {
		Dimension d = new Dimension(5, 10);
		System.out.println("OLD: " + d.height);
		testMethod(d);
		System.out.println("NEW: " + d.height);
	}

	public void testMethod(Dimension d) {
		d = new Dimension(15, 20);
		System.out.println("INSIDE TEST METHOD");
		System.out.println(d.height);
		System.out.println("OUTSIDE TEST METHOD");
	}

	public static void main(String[] args) {
		new PassByValue();
	}

}
