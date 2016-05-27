package dsapplications;

import java.util.regex.*;

public class RegexApp {
	public static void main(String[] args) {
		String input = "aaaaabbbdddsssst";
		String regex = input.substring(0, 1);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		int temp = 0;
		for (int i = 0; i < input.length(); ) {
			while (matcher.find()) {
				i++;
				temp++;
			}
			System.out.println("The number of " + regex + " is: " + temp);
			temp = 0;
			if (i < input.length()) {
				regex = input.substring(i, i + 1);
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(input);
			}
		}
	}
}
