package chapter1.two;
import chapter1.one.One;;
public class Two extends One {
	protected final void one() {
		System.out.println("Two");
	}
	
	public static void main(String[] args) {
		Two t = new Two();
		t.one();
	}
}
