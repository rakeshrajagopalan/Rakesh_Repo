package trickyquestions;

import java.util.Arrays;

public class SuperClass {
	public static void main(String[] args) {
		new SuperClass().f1(null);
	}
	
	public void f1(Object o) {
		System.out.println("Inside object");
	}
	
	public void f1(String s) {
		System.out.println("Inside string");
	}
}
