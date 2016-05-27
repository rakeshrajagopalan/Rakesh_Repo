package testpack;

public class Two {
	public static void main(String[] args) {
		String s1 = "John";
		String s2 = new String("John");
		String s3 = new String("John");
		System.out.println(s1 == s2);
		System.out.println(s2 == s3);
	}
}
