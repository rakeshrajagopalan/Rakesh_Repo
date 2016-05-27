package scjppack;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int counter = 0;
		int j;
		l1: for (int i = 0; i < 10; i++) {
			l2: j = 0;
			while (j++ < 10) {
				if (j > i) {
					break l1;
				}
				if (j == i) {
					counter++;
					continue l1;
				}
			}
		}
		System.out.println(counter);
		System.out.hashCode();
	}

}
