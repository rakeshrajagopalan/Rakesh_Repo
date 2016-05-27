package scjppack;

public class InstanceOf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = null;
		if (s instanceof String) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}

}
