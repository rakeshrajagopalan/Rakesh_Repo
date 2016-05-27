package chapter6.common;

public class StringCompareTest {
	public static void main(String[] args) {
		String s = "Rakesh";
		String s2 = "rakesh";
		System.out.println(s.compareTo(s2));
		System.out.println(s.compareToIgnoreCase(s2));
	}
}
