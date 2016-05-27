package chapter2;

public class CastingExample extends GameShape implements Animatable {
	public static void main(String[] args) {
		Object o;
		Animatable a;
		GameShape g;
		CastingExample p = new CastingExample();
		// DOWNCASTING EXAMPLES
		o = p;
		a = p;
		g = p;
		// UPCASTING EXAMPLES
		p = (CastingExample) o;
		p = (CastingExample) a;
		p = (CastingExample) g;
		System.out.println(3 + 2 + "Hi" + 3 + 2);// O/P: 5Hi32
		System.out.println("Hi" + (3 + 2));// O/P: Hi5
	}
}
