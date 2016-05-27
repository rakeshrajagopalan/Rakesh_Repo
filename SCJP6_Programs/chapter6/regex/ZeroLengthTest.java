package chapter6.regex;

import java.util.regex.*;

public class ZeroLengthTest {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("a?");
		Matcher matcher = pattern.matcher("aba");
		while (matcher.find()) {
			System.out.println(matcher.start() + " " + matcher.end());
		}
		System.out.println("-----------------------------");
		matcher = pattern.matcher("aaba");
		while (matcher.find()) {
			System.out.println(matcher.start() + " " + matcher.end());
		}
		System.out.println("-----------------------------");
		matcher = pattern.matcher("abaa");
		while (matcher.find()) {
			System.out.println(matcher.start() + " " + matcher.end());
		}
		System.out.println("-----------------------------");
		matcher = pattern.matcher("baba");
		while (matcher.find()) {
			System.out.println(matcher.start() + " " + matcher.end());
		}
	}
}
