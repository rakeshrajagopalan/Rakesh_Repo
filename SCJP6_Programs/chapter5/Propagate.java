package chapter5;

public class Propagate {
	public static void main(String[] args) {
		try {
			new Propagate().reverse(new String());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void reverse(String s) throws Exception {
		if (s.length() == 0) {
			throw new Exception();
		}
	}
}
