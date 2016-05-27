package chapter7.collections;

public class HashcodeTest {

	private String var;

	public HashcodeTest(String var) {
		this.var = var;
	}

	public static void main(String[] args) {
		HashcodeTest ht1 = new HashcodeTest("Hi");
		HashcodeTest ht2 = new HashcodeTest("Hello");
		System.out.println(ht1.equals(ht2));
		System.out.println(ht1.hashCode() == ht2.hashCode());
	}

	public boolean equals(Object object) {
		boolean equal;
		if (object instanceof HashcodeTest
				&& ((HashcodeTest) object).var == this.var) {
			equal = true;
		} else {
			equal = false;
		}
		return equal;
	}

	public String toString() {
		return "Sri Rama Jayam";
	}

	public int hashCode() {
		return (var.hashCode() * 5);
	}
}
