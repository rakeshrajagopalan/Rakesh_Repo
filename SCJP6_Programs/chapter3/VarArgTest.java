package chapter3;

public class VarArgTest {
	
	static void boxNVararg(Integer... integers) {
		System.out.println("Integer...");
	}
	
	static void widenNVararg(long... longs) {
		System.out.println("long...");
	}

	public static void main(String[] args) {
		int i = 5;
		boxNVararg(i);
		widenNVararg(i);
	}

}
