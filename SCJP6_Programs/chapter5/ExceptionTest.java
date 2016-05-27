package chapter5;

public class ExceptionTest {

	public void method1() {
		try {
			method2();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void method2() throws Exception {
		throw new Exception();
	}

	public static void main(String[] args) {
		new ExceptionTest().method1();
	}
}
