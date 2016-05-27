package trickyquestions;

import java.util.*;

public class ComparatorTest {
	public static void main(String[] args) {
		String[] words = { "Good", "Bad", "Ugly" };
		Comparator<String> best = new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o2.charAt(1) - o1.charAt(1);
			}
		};
		Arrays.sort(words, best);
		for(String s : words) {
			System.out.print(s + " ");
		}
	}
}
