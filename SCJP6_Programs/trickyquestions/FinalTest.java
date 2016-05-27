package trickyquestions;

public class FinalTest {

	private final int i;

	public FinalTest(int x) {
		i = x;
	}

	public static void main(String[] args) {
		new FinalTest(10);
		new FinalTest(20);
	}
}
