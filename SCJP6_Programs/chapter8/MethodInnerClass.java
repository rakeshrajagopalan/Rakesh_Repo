package chapter8;

public class MethodInnerClass {
	private int x = 10;

	public void test() {
		final int y = 15;
		class InnerClass {
			private void test() {
				System.out.println(x);
				System.out.println(y);
			}
		}
		new InnerClass().test();
	}
}
