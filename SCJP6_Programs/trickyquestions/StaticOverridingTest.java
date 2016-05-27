package trickyquestions;

class High {
	public static String go(int... x) {
		return "hi";
	}
}

public class StaticOverridingTest extends High {
	public static String go(Integer x, Integer y) {
		return "Test";
	}
	public static void main(String... args) {
		System.out.println(go(9,27));
		System.out.println(go(81));
	}
}
