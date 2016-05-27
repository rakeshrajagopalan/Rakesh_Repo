package chapter2;

public class Lion extends Animal {
	public void eat() {
		System.out.println("Lion");
	}

	public void shout() {
		System.out.println("Roarrrrr");
	}

	/*
	 * Final methods cannot be overriden! public int legs() { return 4; }
	 */

	// Overriding methods can return a sub-type of the return type thats
	// returned in the method that is being overridden. It can also throw new
	// Unchecked Exception or error, but cannot throw new or broader Checked Exception!
	public String shape() throws RuntimeException,Error {
		return new String();
	}

	public static void main(String[] args) {
		Animal a = new Lion();
		a.eat();
		// a.shout(); //ILLEGAL!
		// a.sleep(); //ILLEGAL! Private methods cannot be accessed outside the
		// file of that class!
		a.legs();
	}
}
