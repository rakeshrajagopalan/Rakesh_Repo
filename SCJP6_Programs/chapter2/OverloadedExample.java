package chapter2;

public class OverloadedExample {
	public void eat(Object o) {
		System.out.println("Inside Object method()");
	}
	public void eat(String s) {
		System.out.println("Inside String method()");
	}
	public void eat (int i) {
		System.out.println("Inside int method()");
	}
	public void eat (char i) {
		System.out.println("Inside char method()");
	}
	public void eat (byte i) {
		System.out.println("Inside byte method()");
	}
	public void eat (short i) {
		System.out.println("Inside short method()");
	}
	public void eat (long i) {
		System.out.println("Inside long method()");
	}
	public void eat (float i) {
		System.out.println("Inside float method()");
	}
	public void eat (double i) {
		System.out.println("Inside double method()");
	}
	public static void main(String[] args) {
		OverloadedExample ex = new OverloadedExample();
		Object o = new Object();
		String s = new String();
		byte b = 10;
		short sh = 10;
		ex.eat(o);//Inside Object method()
		ex.eat(s);//Inside String method()
		ex.eat(null);//Inside String method()
		ex.eat('c');//Inside char method()
		ex.eat(b);//Inside byte method()
		ex.eat(sh);//Inside short method()
		ex.eat(10l);//Inside long method()
		ex.eat(10.5f);//Inside float method()
		ex.eat(10);//Inside int method()
		ex.eat(10.5);//Inside double method()
	}
}
