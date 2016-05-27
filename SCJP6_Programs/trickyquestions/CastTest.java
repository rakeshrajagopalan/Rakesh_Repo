package trickyquestions;

interface Inter {}

class Base implements Inter{}

class Derived extends Base{}

public class CastTest {
	public static void main(String[] args) {
		Base base = new Base();
		Derived derived = new Derived();
		Inter inter = (Base) base;
		inter = (Base) derived;
		Derived der2 = (Derived) base;
		base = (Base) inter;
	}
}
