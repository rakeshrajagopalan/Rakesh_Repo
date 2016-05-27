package chapter6.common;

public class StringBuilderTest {
	public static void main(String[] args) {
		StringBuilder sbd1 = new StringBuilder("Hi");
		StringBuilder sbd2 = new StringBuilder("Hi");
		System.out.println("String Builders: " + sbd1.equals(sbd2));
		StringBuffer sbf1 = new StringBuffer("Hi");
		StringBuffer sbf2 = new StringBuffer("Hi");
		System.out.println("String Buffers: " + sbf1.equals(sbf2));
		sbf1 = new StringBuffer(null);
		sbf2 = new StringBuffer(null);
		System.out.println("Next Test: " + sbf1.equals(sbf2));
		StringBuilder stringBuilder = new StringBuilder("Hello");
		stringBuilder.insert(1, true);
		System.out.println(stringBuilder.toString());
	}
}
