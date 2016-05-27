package chapter6.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegex {

	public static void main(String[] args) {
		String s = "absdbfbsfbw34rabbwerxfuhvyudsfrys";
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start());
		}
	}
}
