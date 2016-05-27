package chapter3;

public class Boxing2 {
	static Integer x;
	public static void main(String[] args) {
		doStuff(x);
	}
	static void doStuff(int x) {
		int x2 = 5;
		System.out.println(x + x2);
	}
}
