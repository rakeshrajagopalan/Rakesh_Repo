package testpack;

public class One implements Runnable {
	public void run() {
		System.out.println("World");
		throw new RuntimeException();
	}
	public static void main(String[] args) {
		new Thread(new One()).start();
		System.out.println("Hello");
	}
}
