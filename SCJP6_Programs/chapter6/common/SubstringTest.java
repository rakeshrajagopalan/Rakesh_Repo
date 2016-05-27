package chapter6.common;

public class SubstringTest {
	public static void main(String[] args) {
		String s = "Rakesh";
		System.out.println(s.substring(3));
		System.out.println(s.substring(3, s.length()));
		String s2 = "rakesh";
		System.out.println(s.compareToIgnoreCase(s2));
	}
}
