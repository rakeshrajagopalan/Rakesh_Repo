package chapter6.regex;

import java.util.regex.*;

public class GreedyTest {
	public static void main(String[] args) {
		GreedyTest gt = new GreedyTest();
		gt.one();
		gt.two();
		gt.three();
		gt.four();
		gt.five();
		gt.six();
		gt.seven();
		gt.eight();
		gt.nine();
		gt.ten();
	}

	private void one() {
		System.out.println(".*xx");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile(".*xx");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void two() {
		System.out.println(".?xx");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile(".?xx");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void three() {
		System.out.println("y*xx");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("y*xx");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void four() {
		System.out.println("y?xx");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("y?xx");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void five() {
		System.out.println("y*xx*");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("y*xx*");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void six() {
		System.out.println("y?xx?");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("y?xx?");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void seven() {
		System.out.println("xx?");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("xx?");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void eight() {
		System.out.println("xx*");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("xx*");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void nine() {
		System.out.println("y?xx*");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("y?xx*");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}

	private void ten() {
		System.out.println("y*xx?");
		String s = "yyxxxyxx";
		Pattern pattern = Pattern.compile("y*xx?");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.start() + " - " + matcher.group());
		}
		System.out.println("------------------------------------");
	}
}
