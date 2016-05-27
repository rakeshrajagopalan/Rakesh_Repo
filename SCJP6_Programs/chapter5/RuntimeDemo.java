package chapter5;

public class RuntimeDemo {
	
	public void one() {
		two();
	}
	
	public void two() {
		throw new OutOfMemoryError();
	}
	
	public static void main(String[] args) {
		new RuntimeDemo().one();
	}
}
