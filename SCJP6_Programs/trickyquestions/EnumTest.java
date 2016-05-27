package trickyquestions;

enum Roman {
	I {
		public String toString() {
			return "1000";
		}
	}, X,M
}

public class EnumTest {
	public static void main(String[] args) {
		System.out.println(Roman.I);
	}
}
