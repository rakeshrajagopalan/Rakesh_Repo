package trickyquestions;

class Parent {
	static void get1() {
		System.out.println("Parent get1");
	}
	void get2() {
		System.out.println("Parent get2");
	}
	public void get() {
		get1();
		get2();
	}
}

public class OverridingTest4 extends Parent {
	static void get1() {
		System.out.println("Child get1");
	}
	void get2() {
		System.out.println("Child get2");
	}
	public static void main(String[] args) throws Exception {
		new OverridingTest4().get();
	}

}
