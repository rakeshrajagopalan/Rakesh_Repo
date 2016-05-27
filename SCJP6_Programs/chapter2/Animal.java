package chapter2;

public class Animal {
	public void eat() {
		System.out.println("Animal");
	}
	private void sleep() {
		System.out.println("Sleeping");
	}
	
	public final int legs() {
		return 4;
	}
	
	public Object shape() {
		return new Object();
	}
}
