package trickyquestions;

public class NumberGenerator {
	public int getNumber() {
		return 2;
	}

	public static void main(String[] args) {
		NumberGenerator n1 = new NumberGenerator();
		System.out.println(n1.getNumber());
		NumberGenerator2 n2 = new NumberGenerator2();
		System.out.println(n2.getNumber());
		NumberGenerator n3 = (NumberGenerator) n2;
		System.out.println(n3.getNumber());
	}
}

class NumberGenerator2 extends NumberGenerator {
	@Override
	public int getNumber() {
		return 4;
	}
}
