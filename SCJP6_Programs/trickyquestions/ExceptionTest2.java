package trickyquestions;

class MyException extends Exception {
}

public class ExceptionTest2 {

	void f() throws MyException {
		throw new MyException();
	}

	public static void main(String[] args) throws MyException {
		MyException e1 = null;
		try {
			new ExceptionTest2().f();
		} catch (MyException ex) {
			e1 = ex;
			System.out.println("Catch");
		} finally {
			try {
				System.out.println("Finally");
				throw e1;
			} catch (MyException ex2) {
				System.out.println("Second Catch");
				throw ex2;
			} finally {
				System.out.println("Really finally");
			}
		}
		// System.out.println("End"); //This code is unreachable. No code can
		// come below a block in which exception is rethrown.
	}
}
