package chapter6.io;

import java.io.Console;

public class ConsoleTest {
	public static void main(String[] args) {
		Console console = System.console();
		System.out.print("Please enter the username: ");
		String userName = console.readLine();
		System.out.print("Please enter the password: ");
		String password = new String(console.readPassword());
		if (userName.equals(password)) {
			System.out.println("Welcome, " + userName);
		} else {
			System.out.println("Wrong password");
		}
	}
}
