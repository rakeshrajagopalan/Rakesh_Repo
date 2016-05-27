package trickyquestions;

public class SynchTest {
	private static synchronized void say(String s) {
		System.out.print(s);
	}

	public static void main(String[] args) {
		Thread letters = new Thread() {
			public void run() {
				try {
					say("A");
					Thread.sleep(1000);
					say("B");
					Thread.sleep(1000);
					say("C");
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		};
		Thread numbers = new Thread() {
			public void run() {
				try {
					say("1");
					Thread.sleep(1000);
					say("2");
					Thread.sleep(1000);
					say("3");
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		};
		letters.start();
		numbers.start();
	}
}
