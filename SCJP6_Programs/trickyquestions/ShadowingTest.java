package trickyquestions;

import java.util.*;

public class ShadowingTest {
	private Set<Integer> numbers = new TreeSet<Integer>();

	public ShadowingTest(int... nums) {
		for (int i : nums) {
			numbers.add(i);
		}
	}

	public ShadowingTest negate() {
		ShadowingTest negatives = new ShadowingTest();
		for (int i : numbers) {
			negatives.numbers.add(-i);
		}
		return negatives;
	}

	public void show() {
		for (int i : numbers) {
			System.out.print(i + " ");
		}
	}

	public static void main(String[] args) {
		new ShadowingTest(1, 3, -5).negate().show();
	}
}
