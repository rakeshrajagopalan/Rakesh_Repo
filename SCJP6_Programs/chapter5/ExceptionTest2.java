package chapter5;

public class ExceptionTest2 {

	public ExceptionTest2() {
		method1();
	}

	public void method1() {
		try {
			int x = 5 / 0;
			System.out.println("Im still alive");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.out.println("Inside Finally");
		}
	}

	public static void main(String[] args) {
		new ExceptionTest2();
	}

}
