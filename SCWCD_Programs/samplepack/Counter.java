package samplepack;

public class Counter {

	private static int count;

	public static int getCount() {
		count += 1;
		return count;
	}
}
