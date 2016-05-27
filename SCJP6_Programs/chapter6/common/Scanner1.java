package chapter6.common;

import java.util.Scanner;

public class Scanner1 {
	public static void main(String[] args) throws Exception {
		System.out.println("Input: ");
		//1b2c335f456
		System.out.flush();
		Scanner s = new Scanner(System.in);
		String token;
		do {
			token = s.findInLine(args[0]);
			System.out.println("Found: " + token);
		} while (token != null);
	}
}
