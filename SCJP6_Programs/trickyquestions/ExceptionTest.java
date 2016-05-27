package trickyquestions;

public class ExceptionTest {
	void f() {
		throw new RuntimeException();
	}
	public static void main(String[] args) {
		ExceptionTest et = new ExceptionTest();
		try {
			et.f();
		} catch(Throwable t) {
			try {
				throw (Exception) t;
			} catch(Exception ex) {
				System.out.println("Catch 1");
			} finally {
				System.out.println("Finally 1");
			}
		} finally {
			System.out.println("Finally 2");
		}
	}
}
