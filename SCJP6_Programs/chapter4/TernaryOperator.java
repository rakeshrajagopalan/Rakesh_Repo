package chapter4;

public class TernaryOperator {
	public static void main(String[] args) {
		int i = 15;
		int j = (i < 10) ? 7 : (i < 17) ? 5 : 6;
		System.out.println(j);
	}
}
