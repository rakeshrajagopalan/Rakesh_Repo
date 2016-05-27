package trickyquestions;

import java.util.Arrays;
import java.util.List;

//Print lines by word count. First lines's length should be max, while last line should have 1 word
public class PatternPrinter1 {

	private static String input = "The while statement verifies the condition before entering into the loop to see whether the next loop iteration should occur or not. The do-while statement executes the first iteration without checking the condition, it verifies the condition after finishing each iteration. The do-while statement will always execute the body of a loop at least once. ";

	public static void main(String[] args) {
		List<String> words = Arrays.asList(reverse(input, " ").split(" "));
		String data = "";
		String previousLineData = "";
		String temp = "";
		for (String nextWord : words) {
			temp += nextWord + " ";
			if (temp.length() > previousLineData.length()) {
				previousLineData = temp;
				data += previousLineData + "\n";
			} else {
				continue;
			}
			temp = "";
		}
		List<String> strings = Arrays.asList(reverse(data, "\n").split("\n"));
		for (String nextLine : strings) {
			System.out.println(reverse(nextLine, " "));
		}
	}

	private static String reverse(String data, String regex) {
		String[] words = data.split(regex);
		String returnWord = "";
		for (int i = words.length - 1; i >= 0; i--) {
			returnWord += words[i] + regex;
		}
		return returnWord;
	}
}
